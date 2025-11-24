package com.heytwin.ingestion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.heytwin.dto.ApiResponse;

@RestController
@RequestMapping("/api/admin/questions")
public class QuestionIngestionController {

    private static final String SYNC_PATH = "/api/admin/questions/sync";

    private final QuestionIngestionService questionIngestionService;
    private final OpenTdbProperties openTdbProperties;

    public QuestionIngestionController(QuestionIngestionService questionIngestionService,
            OpenTdbProperties openTdbProperties) {
        this.questionIngestionService = questionIngestionService;
        this.openTdbProperties = openTdbProperties;
    }

    @PostMapping("/sync")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<QuestionImportResult>> sync(@RequestParam(name = "amount", required = false) Integer amount) {
        int batchSize = resolveAmount(amount);
        QuestionImportResult result = questionIngestionService.importFromOpenTdb(batchSize);
        ApiResponse<QuestionImportResult> payload = ApiResponse.<QuestionImportResult>builder()
                .status(HttpStatus.OK.value())
                .path(SYNC_PATH)
                .data(result)
                .build();
        return ResponseEntity.ok(payload);
    }

    private int resolveAmount(Integer amount) {
        if (amount == null || amount <= 0) {
            return openTdbProperties.getDefaultAmount();
        }
        return Math.min(amount, 100);
    }
}
