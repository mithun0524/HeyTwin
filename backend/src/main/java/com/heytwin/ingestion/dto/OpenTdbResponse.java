package com.heytwin.ingestion.dto;

import java.util.List;

public record OpenTdbResponse(int response_code, List<OpenTdbResult> results) {
    public record OpenTdbResult(String category,
                                String type,
                                String difficulty,
                                String question,
                                String correct_answer,
                                List<String> incorrect_answers) {
    }
}
