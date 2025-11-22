package com.heytwin.digitaltwin;

import com.heytwin.digitaltwin.model.DigitalTwinSummary;
import com.heytwin.dto.ApiResponse;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.UserRepository;
import java.security.Principal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/digital-twin")
@RequiredArgsConstructor
public class DigitalTwinController {

    private final DigitalTwinService digitalTwinService;
    private final UserRepository userRepository;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponse<DigitalTwinSummary>> summary(Principal principal) {
    DigitalTwinSummary summary = digitalTwinService.getSummary(resolveStudentId(principal));
        return ResponseEntity.ok(ApiResponse.<DigitalTwinSummary>builder()
                .status(200)
                .path("/api/digital-twin/summary")
                .data(summary)
                .build());
    }

    private UUID resolveStudentId(Principal principal) {
        User user = userRepository.findByEmailIgnoreCase(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return user.getId();
    }
}
