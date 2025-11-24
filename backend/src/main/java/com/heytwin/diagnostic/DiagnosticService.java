package com.heytwin.diagnostic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heytwin.diagnostic.dto.DiagnosticResponseRequest;
import com.heytwin.diagnostic.dto.DiagnosticSessionPayload;
import com.heytwin.digitaltwin.DigitalTwinService;
import com.heytwin.domain.entity.PracticeSession;
import com.heytwin.domain.entity.Question;
import com.heytwin.domain.entity.Response;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.model.enums.SessionStrategy;
import com.heytwin.domain.model.enums.SessionType;
import com.heytwin.domain.repository.PracticeSessionRepository;
import com.heytwin.domain.repository.QuestionRepository;
import com.heytwin.domain.repository.ResponseRepository;
import com.heytwin.dto.QuestionDto;
import com.heytwin.util.QuestionMapper;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DiagnosticService {
    private static final int QUESTION_COUNT = 15;
    private final QuestionRepository questionRepository;
    private final PracticeSessionRepository practiceSessionRepository;
    private final ResponseRepository responseRepository;
    private final QuestionMapper questionMapper;
    private final ObjectMapper objectMapper;
    private final DigitalTwinService digitalTwinService;

    @Transactional
    public DiagnosticSessionPayload startDiagnostic(User student) {
        List<Question> pool = questionRepository.findAll();
        Collections.shuffle(pool);
        List<Question> selected = pool.stream().limit(QUESTION_COUNT).toList();
        PracticeSession session = practiceSessionRepository.save(PracticeSession.builder().student(student).sessionType(SessionType.DIAGNOSTIC).selectionStrategy(SessionStrategy.BALANCED).questionCount(selected.size()).metadata(writeMetadata(selected)).build());
        List<QuestionDto> questionDtos = selected.stream().map(questionMapper::toDto).toList();
        return DiagnosticSessionPayload.builder().sessionId(session.getId().toString()).questions(questionDtos).build();
    }

    @Transactional
    public void submitResponses(User student, DiagnosticResponseRequest request) {
        PracticeSession session = practiceSessionRepository.findById(UUID.fromString(request.getSessionId())).orElseThrow(() -> new IllegalArgumentException("Session not found"));
        Map<UUID, Question> questionLookup = extractQuestionIds(session).stream().map(questionRepository::findById).flatMap(Optional::stream).collect(Collectors.toMap(Question::getId, q -> q));
        List<Response> responses = request.getResponses().stream().map(item -> buildResponse(student, session, questionLookup.get(item.getQuestionId()), item)).toList();
        responseRepository.saveAll(responses);
        digitalTwinService.updateTwinFromResponses(student, responses);
        session.setStatus("COMPLETED");
        session.setCompletedAt(java.time.OffsetDateTime.now());
        practiceSessionRepository.save(session);
    }

    private Response buildResponse(User student, PracticeSession session, Question question, DiagnosticResponseRequest.Item item) {
        boolean isCorrect = evaluate(question, item.getSelectedOption());
        return Response.builder().session(session).student(student).question(question).selectedOption(item.getSelectedOption()).correct(isCorrect).timeTakenSec(item.getTimeTakenSec()).aiPredictedProbability(0.0).build();
    }

    private boolean evaluate(Question question, String selectedOption) {
        try {
            String correct = objectMapper.readTree(question.getCorrectAnswerJson()).asText();
            return correct.equalsIgnoreCase(selectedOption);
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    private String writeMetadata(List<Question> questions) {
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("questionIds", questions.stream().map(q -> q.getId().toString()).toList());
        metadata.put("difficultySpread", questions.stream().collect(Collectors.groupingBy(Question::getDifficulty, Collectors.counting())));
        try {
            return objectMapper.writeValueAsString(metadata);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }

    @SuppressWarnings("unchecked")
    private List<UUID> extractQuestionIds(PracticeSession session) {
        if (session.getMetadata() == null) {
            return List.of();
        }
        try {
            Map<String, Object> metadata = objectMapper.readValue(session.getMetadata(), Map.class);
            List<String> ids = (List<String>) metadata.getOrDefault("questionIds", List.of());
            return ids.stream().map(UUID::fromString).toList();
        } catch (Exception e) {
            return List.of();
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public DiagnosticService(final QuestionRepository questionRepository, final PracticeSessionRepository practiceSessionRepository, final ResponseRepository responseRepository, final QuestionMapper questionMapper, final ObjectMapper objectMapper, final DigitalTwinService digitalTwinService) {
        this.questionRepository = questionRepository;
        this.practiceSessionRepository = practiceSessionRepository;
        this.responseRepository = responseRepository;
        this.questionMapper = questionMapper;
        this.objectMapper = objectMapper;
        this.digitalTwinService = digitalTwinService;
    }
}
