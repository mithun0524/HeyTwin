package com.heytwin.diagnostic.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class DiagnosticResponseRequest {

    @NotBlank
    private String sessionId;

    @Valid
    @NotNull
    private List<Item> responses;

    @Data
    public static class Item {
        @NotNull
        private UUID questionId;

        @NotBlank
        private String selectedOption;

        @NotNull
        private Integer timeTakenSec;
    }
}
