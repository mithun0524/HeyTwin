package com.heytwin.dashboard;

import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.UserRepository;
import com.heytwin.dto.ApiResponse;
import java.security.Principal;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    private final UserRepository userRepository;

    @GetMapping("/overview")
    public ResponseEntity<ApiResponse<Map<String, Object>>> overview(Principal principal) {
        Map<String, Object> response = dashboardService.overview(resolve(principal));
        return ResponseEntity.ok(ApiResponse.<Map<String, Object>>builder().status(200).path("/api/dashboard/overview").data(response).build());
    }

    private User resolve(Principal principal) {
        return userRepository.findByEmailIgnoreCase(principal.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @java.lang.SuppressWarnings("all")
    
    public DashboardController(final DashboardService dashboardService, final UserRepository userRepository) {
        this.dashboardService = dashboardService;
        this.userRepository = userRepository;
    }
}
