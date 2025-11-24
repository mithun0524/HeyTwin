package com.heytwin.adaptive;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heytwin.client.AiServiceClient;
import com.heytwin.client.dto.AiPredictionRequest;
import com.heytwin.client.dto.AiPredictionResponse;
import com.heytwin.digitaltwin.DigitalTwinService;
import com.heytwin.domain.entity.PracticeSession;
import com.heytwin.domain.entity.Question;
import com.heytwin.domain.entity.Response;
import com.heytwin.domain.entity.StudentTopicProfile;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.model.enums.SessionStrategy;
import com.heytwin.domain.model.enums.SessionType;
import com.heytwin.domain.repository.PracticeSessionRepository;
import com.heytwin.domain.repository.QuestionRepository;
import com.heytwin.domain.repository.ResponseRepository;
import com.heytwin.domain.repository.StudentTopicProfileRepository;
import com.heytwin.dto.PracticeResponseRequest;
import com.heytwin.dto.PracticeSessionStartRequest;
import com.heytwin.dto.PracticeSessionStartResponse;
import com.heytwin.dto.QuestionDto;
import com.heytwin.util.QuestionMapper;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdaptiveEngineService {
    private final QuestionRepository questionRepository;
    private final PracticeSessionRepository practiceSessionRepository;
    private final ResponseRepository responseRepository;
    private final StudentTopicProfileRepository profileRepository;
    private final AiServiceClient aiServiceClient;
    private final QuestionMapper questionMapper;
    private final ObjectMapper objectMapper;
    private final DigitalTwinService digitalTwinService;

    @Transactional
    public PracticeSessionStartResponse startSession(User student, PracticeSessionStartRequest request) {
        List<Question> candidates = pickCandidates(request);
        Map<UUID, StudentTopicProfile> profileMap = profileRepository.findByStudent(student).stream().collect(Collectors.toMap(profile -> profile.getTopic().getId(), profile -> profile));
        AiPredictionRequest aiRequest = buildAiRequest(student, candidates, profileMap);
        AiPredictionResponse predictionResponse = aiServiceClient.predict(aiRequest);
        Map<String, Double> probMap = predictionResponse.getPredictions().stream().collect(Collectors.toMap(AiPredictionResponse.Item::getQuestionId, AiPredictionResponse.Item::getProb));
        List<Question> ordered = balanceSelection(candidates, probMap, request.getCount());
        PracticeSession session = practiceSessionRepository.save(PracticeSession.builder().student(student).sessionType(Optional.ofNullable(request.getSessionType()).orElse(SessionType.PRACTICE)).selectionStrategy(Optional.ofNullable(request.getStrategy()).orElse(SessionStrategy.BALANCED)).questionCount(ordered.size()).aiModelId(predictionResponse.getModelId()).metadata(writeMetadata(ordered, probMap)).build());
        List<QuestionDto> questionDtos = ordered.stream().map(questionMapper::toDto).toList();
        Map<String, Integer> rationale = computeRationale(probMap, ordered);
        return PracticeSessionStartResponse.builder().sessionId(session.getId().toString()).questions(questionDtos).selectionRationale(rationale).build();
    }

    @Transactional
    public void recordResponse(User student, UUID sessionId, PracticeResponseRequest request) {
        PracticeSession session = practiceSessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Session not found"));
        Question question = questionRepository.findById(request.getQuestionId()).orElseThrow(() -> new IllegalArgumentException("Question not found"));
        Response response = Response.builder().session(session).student(student).question(question).selectedOption(request.getSelectedOption()).correct(evaluate(question, request.getSelectedOption())).timeTakenSec(request.getTimeTakenSec()).aiPredictedProbability(extractProb(session, question)).build();
        responseRepository.save(response);
    }

    @Transactional
    public void completeSession(User student, UUID sessionId) {
        PracticeSession session = practiceSessionRepository.findById(sessionId).orElseThrow(() -> new IllegalArgumentException("Session not found"));
        List<Response> responses = responseRepository.findBySession(session);
        digitalTwinService.updateTwinFromResponses(student, responses);
        session.setStatus("COMPLETED");
        session.setCompletedAt(OffsetDateTime.now());
        practiceSessionRepository.save(session);
    }

    private List<Question> pickCandidates(PracticeSessionStartRequest request) {
        List<Question> pool = questionRepository.findAll().stream().filter(Question::getActive).collect(Collectors.toCollection(ArrayList::new));
        Collections.shuffle(pool);
        if (request.getTargetDifficulty() != null && !request.getTargetDifficulty().isEmpty()) {
            pool = pool.stream().filter(q -> request.getTargetDifficulty().contains(q.getDifficulty().name())).collect(Collectors.toCollection(ArrayList::new));
        }
        return pool.stream().limit(Math.max(request.getCount() * 2, 20)).toList();
    }

    private AiPredictionRequest buildAiRequest(User student, List<Question> questions, Map<UUID, StudentTopicProfile> profiles) {
        List<AiPredictionRequest.Instance> instances = questions.stream().map(question -> {
            StudentTopicProfile profile = profiles.getOrDefault(question.getTopic().getId(), StudentTopicProfile.builder().accuracy(0.6).averageTimeSec(45.0).difficultyScore(0.5).consistencyScore(0.5).forgettingScore(0.5).masteryLevel(0.5).build());
            Map<String, Object> features = Map.of("topicMastery", profile.getMasteryLevel(), "recentAccuracy", profile.getAccuracy(), "avgTimeSec", profile.getAverageTimeSec(), "difficulty", question.getDifficulty().ordinal() + 1, "questionType", question.getQuestionType().name());
            return AiPredictionRequest.Instance.builder().studentId(student.getId().toString()).questionId(question.getId().toString()).features(features).build();
        }).toList();
        return AiPredictionRequest.builder().modelId("rf-latest").instances(instances).build();
    }

    private List<Question> balanceSelection(List<Question> candidates, Map<String, Double> probMap, int targetCount) {
        List<Question> high = new ArrayList<>();
        List<Question> medium = new ArrayList<>();
        List<Question> low = new ArrayList<>();
        for (Question question : candidates) {
            double prob = probMap.getOrDefault(question.getId().toString(), 0.5);
            if (prob >= 0.7) {
                high.add(question);
            } else if (prob >= 0.4) {
                medium.add(question);
            } else {
                low.add(question);
            }
        }
        Comparator<Question> comparator = Comparator.comparing(q -> probMap.getOrDefault(q.getId().toString(), 0.5), Comparator.reverseOrder());
        high.sort(comparator);
        medium.sort(comparator);
        low.sort(comparator);
        int lowCount = Math.max(2, targetCount / 3);
        int highCount = Math.max(2, targetCount / 3);
        int mediumCount = targetCount - lowCount - highCount;
        List<Question> result = new ArrayList<>();
        result.addAll(low.subList(0, Math.min(lowCount, low.size())));
        result.addAll(medium.subList(0, Math.min(mediumCount, medium.size())));
        result.addAll(high.subList(0, Math.min(highCount, high.size())));
        while (result.size() < targetCount) {
            for (Question question : candidates) {
                if (!result.contains(question)) {
                    result.add(question);
                    break;
                }
            }
        }
        return result.subList(0, Math.min(targetCount, result.size()));
    }

    private Map<String, Integer> computeRationale(Map<String, Double> probMap, List<Question> ordered) {
        Map<String, Integer> rationale = new HashMap<>();
        rationale.put("highProb", (int) ordered.stream().filter(q -> probMap.getOrDefault(q.getId().toString(), 0.5) >= 0.7).count());
        rationale.put("mediumProb", (int) ordered.stream().filter(q -> {
            double prob = probMap.getOrDefault(q.getId().toString(), 0.5);
            return prob >= 0.4 && prob < 0.7;
        }).count());
        rationale.put("lowProb", ordered.size() - rationale.get("highProb") - rationale.get("mediumProb"));
        return rationale;
    }

    private String writeMetadata(List<Question> questions, Map<String, Double> probMap) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("questionOrder", questions.stream().map(q -> q.getId().toString()).toList());
        metadata.put("probabilities", probMap);
        try {
            return objectMapper.writeValueAsString(metadata);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    @SuppressWarnings("unchecked")
    private double extractProb(PracticeSession session, Question question) {
        if (session.getMetadata() == null) {
            return 0.5;
        }
        try {
            Map<String, Object> metadata = objectMapper.readValue(session.getMetadata(), Map.class);
            Map<String, Double> probs = (Map<String, Double>) metadata.getOrDefault("probabilities", Map.of());
            return probs.getOrDefault(question.getId().toString(), 0.5);
        } catch (Exception e) {
            return 0.5;
        }
    }

    private boolean evaluate(Question question, String selectedOption) {
        try {
            String correct = objectMapper.readTree(question.getCorrectAnswerJson()).asText();
            return correct.equalsIgnoreCase(selectedOption);
        } catch (Exception e) {
            return false;
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public AdaptiveEngineService(final QuestionRepository questionRepository, final PracticeSessionRepository practiceSessionRepository, final ResponseRepository responseRepository, final StudentTopicProfileRepository profileRepository, final AiServiceClient aiServiceClient, final QuestionMapper questionMapper, final ObjectMapper objectMapper, final DigitalTwinService digitalTwinService) {
        this.questionRepository = questionRepository;
        this.practiceSessionRepository = practiceSessionRepository;
        this.responseRepository = responseRepository;
        this.profileRepository = profileRepository;
        this.aiServiceClient = aiServiceClient;
        this.questionMapper = questionMapper;
        this.objectMapper = objectMapper;
        this.digitalTwinService = digitalTwinService;
    }
}
