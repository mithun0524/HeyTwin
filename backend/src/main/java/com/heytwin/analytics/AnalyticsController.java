package com.heytwin.analytics;

import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.UserRepository;
import com.heytwin.dto.ApiResponse;
import java.security.Principal;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService analyticsService;
    private final UserRepository userRepository;

    @GetMapping("/mastery")
    public ResponseEntity<ApiResponse<Map<String, Object>>> mastery(Principal principal) {
        return ok("/api/analytics/mastery", analyticsService.masteryTrend(resolve(principal)));
    }

    @GetMapping("/learning-curve")
    public ResponseEntity<ApiResponse<Map<String, Object>>> learningCurve(Principal principal) {
        return ok("/api/analytics/learning-curve", analyticsService.learningCurve(resolve(principal)));
    }

    @GetMapping("/topic-pie")
    public ResponseEntity<ApiResponse<Map<String, Object>>> topicPie(Principal principal) {
        return ok("/api/analytics/topic-pie", analyticsService.topicPie(resolve(principal)));
    }

    @GetMapping("/spaced-repetition")
    public ResponseEntity<ApiResponse<Map<String, Object>>> reminders(Principal principal) {
        return ok("/api/analytics/spaced-repetition", analyticsService.spacedReminders(resolve(principal)));
    }

    @GetMapping("/achievements")
    public ResponseEntity<ApiResponse<Map<String, Object>>> achievements(Principal principal) {
        return ok("/api/analytics/achievements", analyticsService.achievements(resolve(principal)));
    }

    @GetMapping("/estimated-score")
    public ResponseEntity<ApiResponse<Map<String, Object>>> estimatedScore(Principal principal) {
        return ok("/api/analytics/estimated-score", analyticsService.estimatedScore(resolve(principal)));
    }

    private ResponseEntity<ApiResponse<Map<String, Object>>> ok(String path, Map<String, Object> data) {
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder()
                .status(200)
                .path(path)
                .data(data)
                .build());
    }

    private User resolve(Principal principal) {
        return userRepository.findByEmailIgnoreCase(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }
}
