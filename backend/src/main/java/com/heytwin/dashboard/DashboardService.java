package com.heytwin.dashboard;

import com.heytwin.digitaltwin.DigitalTwinService;
import com.heytwin.digitaltwin.model.DigitalTwinSummary;
import com.heytwin.domain.entity.PracticeSession;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.PracticeSessionRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final PracticeSessionRepository practiceSessionRepository;
    private final DigitalTwinService digitalTwinService;

    public Map<String, Object> overview(User student) {
        PracticeSession lastSession = practiceSessionRepository.findTop1ByStudentOrderByStartedAtDesc(student).orElse(null);
        DigitalTwinSummary summary = digitalTwinService.getSummary(student.getId());
        Map<String, Object> data = new HashMap<>();
        data.put("diagnosticCompleted", lastSession != null);
        data.put("lastSession", lastSession == null ? null : Map.of("sessionId", lastSession.getId().toString(), "completedAt", lastSession.getCompletedAt(), "score", 0));
        data.put("twinSummary", Map.of("overallMastery", summary.getAccuracy(), "strongTopics", summary.getTopics().stream().filter(t -> t.getMastery() > 0.8).map(DigitalTwinSummary.TopicSnapshot::getName).toList(), "weakTopics", summary.getTopics().stream().filter(t -> t.getMastery() < 0.6).map(DigitalTwinSummary.TopicSnapshot::getName).toList(), "estimatedExamScore", Math.round(summary.getAccuracy() * 100)));
        data.put("reminders", Map.of());
        return data;
    }

    @java.lang.SuppressWarnings("all")
    
    public DashboardService(final PracticeSessionRepository practiceSessionRepository, final DigitalTwinService digitalTwinService) {
        this.practiceSessionRepository = practiceSessionRepository;
        this.digitalTwinService = digitalTwinService;
    }
}
