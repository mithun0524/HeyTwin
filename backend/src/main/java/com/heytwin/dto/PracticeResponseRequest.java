package com.heytwin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;
import lombok.Data;

@Data
public class PracticeResponseRequest {
    @NotNull
    private UUID questionId;

    @NotBlank
    private String selectedOption;

    private boolean skipped;

    @NotNull
    private Integer timeTakenSec;

    private Map<String, Object> metadata;
}
