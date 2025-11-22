package com.heytwin.analytics;

import com.heytwin.digitaltwin.DigitalTwinService;
import com.heytwin.digitaltwin.model.DigitalTwinSummary;
import com.heytwin.domain.entity.PracticeSession;
import com.heytwin.domain.entity.Response;
import com.heytwin.domain.entity.StudentAchievement;
import com.heytwin.domain.entity.StudentTopicProfile;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.PracticeSessionRepository;
import com.heytwin.domain.repository.ResponseRepository;
import com.heytwin.domain.repository.StudentAchievementRepository;
import com.heytwin.domain.repository.StudentTopicProfileRepository;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final PracticeSessionRepository practiceSessionRepository;
    private final ResponseRepository responseRepository;
    private final StudentTopicProfileRepository profileRepository;
    private final StudentAchievementRepository achievementRepository;
    private final DigitalTwinService digitalTwinService;

    public Map<String, Object> masteryTrend(User student) {
        DigitalTwinSummary summary = digitalTwinService.getSummary(student.getId());
        Map<String, Object> twoWeekPoint = new HashMap<>();
        twoWeekPoint.put("timestamp", OffsetDateTime.now().minusDays(14).toLocalDate().toString());
        twoWeekPoint.put("mastery", summary.getAccuracy() - 0.05);
        Map<String, Object> todayPoint = new HashMap<>();
        todayPoint.put("timestamp", OffsetDateTime.now().toLocalDate().toString());
        todayPoint.put("mastery", summary.getAccuracy());
        List<Map<String, Object>> points = List.of(twoWeekPoint, todayPoint);
        return Map.of("points", points);
    }

    public Map<String, Object> learningCurve(User student) {
        List<PracticeSession> sessions = practiceSessionRepository.findByStudentOrderByStartedAtDesc(student);
        List<Map<String, Object>> payload = sessions.stream().limit(10)
                .map(session -> {
                    List<Response> responses = responseRepository.findBySession(session);
                    double score = responses.stream().filter(Response::getCorrect).count() / (double) Math.max(1, responses.size());
                    double avgTime = responses.stream()
                            .mapToInt(response -> response.getTimeTakenSec() != null ? response.getTimeTakenSec() : 45)
                            .average().orElse(45.0);
                    Map<String, Object> sessionMap = new HashMap<>();
                    sessionMap.put("sessionId", session.getId().toString());
                    sessionMap.put("score", score);
                    sessionMap.put("avgTimeSec", avgTime);
                    return sessionMap;
                })
                .toList();
        return Map.of("sessions", payload);
    }

    public Map<String, Object> topicPie(User student) {
        List<StudentTopicProfile> profiles = profileRepository.findByStudent(student);
        List<Map<String, Object>> topics = profiles.stream()
                .map(profile -> {
                    Map<String, Object> topicMap = new HashMap<>();
                    topicMap.put("name", profile.getTopic().getName());
                    topicMap.put("mastery", profile.getMasteryLevel());
                    return topicMap;
                })
                .toList();
        return Map.of("topics", topics);
    }

    public Map<String, Object> spacedReminders(User student) {
        List<Map<String, Object>> reminders = profileRepository.findByStudent(student).stream()
                .filter(profile -> profile.getForgettingScore() < 0.5)
                .map(profile -> {
                    Map<String, Object> reminderMap = new HashMap<>();
                    reminderMap.put("topic", profile.getTopic().getName());
                    reminderMap.put("due", OffsetDateTime.now().plusDays(2).toLocalDate().toString());
                    return reminderMap;
                })
                .toList();
        return Map.of("reminders", reminders);
    }

    public Map<String, Object> achievements(User student) {
        List<StudentAchievement> earned = achievementRepository.findByStudent(student);
        List<Map<String, Object>> earnedPayload = earned.stream()
                .filter(StudentAchievement::getAwarded)
                .map(item -> {
                    Map<String, Object> earnedMap = new HashMap<>();
                    earnedMap.put("code", item.getAchievement().getCode());
                    earnedMap.put("awardedAt", item.getAwardedAt());
                    return earnedMap;
                })
                .toList();
        List<Map<String, Object>> progress = earned.stream()
                .filter(item -> !item.getAwarded())
                .map(item -> {
                    Map<String, Object> progressMap = new HashMap<>();
                    progressMap.put("code", item.getAchievement().getCode());
                    progressMap.put("progress", item.getProgress());
                    return progressMap;
                })
                .toList();
        return Map.of("earned", earnedPayload, "available", progress);
    }

    public Map<String, Object> estimatedScore(User student) {
        DigitalTwinSummary summary = digitalTwinService.getSummary(student.getId());
        double estimated = (summary.getAccuracy() * 100) * 0.7 + (summary.getConsistency() * 100) * 0.3;
        return Map.of(
                "estimatedScore", Math.round(estimated),
                "confidence", summary.getConsistency(),
                "inputs", Map.of(
                        "mastery", summary.getAccuracy(),
                        "consistency", summary.getConsistency(),
                        "recentAverage", summary.getDifficultyScore()
                )
        );
    }
}
