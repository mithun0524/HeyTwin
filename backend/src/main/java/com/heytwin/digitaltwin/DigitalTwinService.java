package com.heytwin.digitaltwin;

import com.heytwin.digitaltwin.model.DigitalTwinSummary;
import com.heytwin.domain.entity.Response;
import com.heytwin.domain.entity.StudentTopicProfile;
import com.heytwin.domain.entity.Topic;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.StudentTopicProfileRepository;
import com.heytwin.domain.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DigitalTwinService {

                private final StudentTopicProfileRepository profileRepository;
                private final UserRepository userRepository;
    private final RuleBasedTwinCalculator calculator;

    @Transactional(readOnly = true)
    public DigitalTwinSummary getSummary(UUID studentId) {
        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));
        List<StudentTopicProfile> profiles = profileRepository.findByStudent(student);

        double overallAccuracy = profiles.stream().mapToDouble(StudentTopicProfile::getAccuracy).average().orElse(0.0);
        double averageTime = profiles.stream().mapToDouble(StudentTopicProfile::getAverageTimeSec).average().orElse(0.0);
        double consistency = profiles.stream().mapToDouble(StudentTopicProfile::getConsistencyScore).average().orElse(0.0);
        double difficulty = profiles.stream().mapToDouble(StudentTopicProfile::getDifficultyScore).average().orElse(0.0);
        double forgetting = profiles.stream().mapToDouble(StudentTopicProfile::getForgettingScore).average().orElse(0.0);

        List<DigitalTwinSummary.TopicSnapshot> topics = profiles.stream()
                .map(profile -> DigitalTwinSummary.TopicSnapshot.builder()
                        .topicId(profile.getTopic().getId().toString())
                        .name(profile.getTopic().getName())
                        .mastery(profile.getMasteryLevel())
                        .trend(profile.getConsistencyScore() - 0.5)
                        .recommendation(recommendationFor(profile))
                        .build())
                .toList();

        return DigitalTwinSummary.builder()
                .accuracy(overallAccuracy)
                .averageTimeSec(averageTime)
                .consistency(consistency)
                .difficultyScore(difficulty)
                .forgettingScore(forgetting)
                .topics(topics)
                .build();
    }

    @Transactional
    public void updateTwinFromResponses(User student, List<Response> responses) {
        Map<Topic, List<Response>> grouped = responses.stream()
                .collect(Collectors.groupingBy(response -> response.getQuestion().getTopic()));

        List<StudentTopicProfile> updatedProfiles = new ArrayList<>();

        for (Map.Entry<Topic, List<Response>> entry : grouped.entrySet()) {
            Topic topic = entry.getKey();
            List<Response> topicResponses = entry.getValue();
            StudentTopicProfile profile = profileRepository.findByStudentAndTopic(student, topic)
                    .orElseGet(() -> StudentTopicProfile.builder()
                            .student(student)
                            .topic(topic)
                            .accuracy(0.5)
                            .averageTimeSec(45.0)
                            .difficultyScore(0.5)
                            .consistencyScore(0.5)
                            .forgettingScore(0.5)
                            .masteryLevel(0.5)
                            .recentWindow("[]")
                            .build());

            double latestAccuracy = topicResponses.stream()
                    .map(Response::getCorrect)
                    .filter(Boolean::booleanValue)
                    .count() / (double) topicResponses.size();
            double avgTime = topicResponses.stream()
                    .mapToInt(response -> response.getTimeTakenSec() != null ? response.getTimeTakenSec() : 45)
                    .average().orElse(45.0);
            double difficultyDelta = topicResponses.stream()
                    .mapToDouble(response -> response.getQuestion().getDifficulty().ordinal() / 2.0)
                    .average().orElse(0.5);
            double consistencyDelta = 1 - Math.abs(latestAccuracy - profile.getAccuracy());
            double forgettingDelta = Math.max(0, 1 - (System.currentTimeMillis() - responseLatestTime(topicResponses)) / (1000d * 60 * 60 * 24 * 7));

            calculator.recalculate(profile, latestAccuracy, avgTime, difficultyDelta, consistencyDelta, forgettingDelta);
            updatedProfiles.add(profile);
        }

        profileRepository.saveAll(updatedProfiles);
    }

    private double responseLatestTime(List<Response> responses) {
        return responses.stream()
                .map(Response::getSubmittedAt)
                .filter(java.util.Objects::nonNull)
                .mapToLong(value -> value.toInstant().toEpochMilli())
                .max()
                .orElse(System.currentTimeMillis());
    }

    private String recommendationFor(StudentTopicProfile profile) {
        if (profile.getMasteryLevel() > 0.8) {
            return "Maintain";
        } else if (profile.getMasteryLevel() > 0.6) {
            return "Reinforce";
        }
        return "Focus";
    }
}
