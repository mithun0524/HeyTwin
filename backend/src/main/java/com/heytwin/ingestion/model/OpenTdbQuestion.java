package com.heytwin.ingestion.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenTdbQuestion(
        String category,
        String type,
        String difficulty,
        String question,
        @JsonProperty("correct_answer") String correctAnswer,
        @JsonProperty("incorrect_answers") List<String> incorrectAnswers) {
}
