package com.heytwin.adaptive;

import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.UserRepository;
import com.heytwin.dto.ApiResponse;
import com.heytwin.dto.PracticeResponseRequest;
import com.heytwin.dto.PracticeSessionStartRequest;
import com.heytwin.dto.PracticeSessionStartResponse;
import jakarta.validation.Valid;
import java.security.Principal;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practice")
public class PracticeController {
    private final AdaptiveEngineService adaptiveEngineService;
    private final UserRepository userRepository;

    @PostMapping("/session/start")
    public ResponseEntity<ApiResponse<PracticeSessionStartResponse>> startSession(Principal principal, @Valid @RequestBody PracticeSessionStartRequest request) {
        User student = resolve(principal);
        PracticeSessionStartResponse response = adaptiveEngineService.startSession(student, request);
        return ResponseEntity.ok(ApiResponse.<PracticeSessionStartResponse>builder().status(HttpStatus.OK.value()).path("/api/practice/session/start").data(response).build());
    }

    @PostMapping("/session/{sessionId}/response")
    public ResponseEntity<Void> submitResponse(Principal principal, @PathVariable UUID sessionId, @Valid @RequestBody PracticeResponseRequest request) {
        adaptiveEngineService.recordResponse(resolve(principal), sessionId, request);
        return ResponseEntity.accepted().build();
    }

    @PostMapping("/session/{sessionId}/complete")
    public ResponseEntity<Void> completeSession(Principal principal, @PathVariable UUID sessionId) {
        adaptiveEngineService.completeSession(resolve(principal), sessionId);
        return ResponseEntity.ok().build();
    }

    private User resolve(Principal principal) {
        return userRepository.findByEmailIgnoreCase(principal.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @java.lang.SuppressWarnings("all")
    
    public PracticeController(final AdaptiveEngineService adaptiveEngineService, final UserRepository userRepository) {
        this.adaptiveEngineService = adaptiveEngineService;
        this.userRepository = userRepository;
    }
}
