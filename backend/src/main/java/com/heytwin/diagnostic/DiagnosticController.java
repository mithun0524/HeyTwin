package com.heytwin.diagnostic;

import com.heytwin.diagnostic.dto.DiagnosticResponseRequest;
import com.heytwin.diagnostic.dto.DiagnosticSessionPayload;
import com.heytwin.domain.entity.User;
import com.heytwin.domain.repository.UserRepository;
import com.heytwin.dto.ApiResponse;
import jakarta.validation.Valid;
import java.security.Principal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/diagnostic")
public class DiagnosticController {
    private final DiagnosticService diagnosticService;
    private final UserRepository userRepository;

    @GetMapping("/questions")
    public ResponseEntity<ApiResponse<DiagnosticSessionPayload>> startDiagnostic(Principal principal) {
        User student = resolve(principal);
        DiagnosticSessionPayload payload = diagnosticService.startDiagnostic(student);
        return ResponseEntity.ok(ApiResponse.<DiagnosticSessionPayload>builder().status(HttpStatus.OK.value()).path("/api/diagnostic/questions").data(payload).build());
    }

    @PostMapping("/responses")
    public ResponseEntity<Void> submitResponses(Principal principal, @Valid @RequestBody DiagnosticResponseRequest request) {
        diagnosticService.submitResponses(resolve(principal), request);
        return ResponseEntity.accepted().build();
    }

    private User resolve(Principal principal) {
        return userRepository.findByEmailIgnoreCase(principal.getName()).orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    @java.lang.SuppressWarnings("all")
    
    public DiagnosticController(final DiagnosticService diagnosticService, final UserRepository userRepository) {
        this.diagnosticService = diagnosticService;
        this.userRepository = userRepository;
    }
}
