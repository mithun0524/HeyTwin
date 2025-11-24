package com.heytwin.ingestion;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heytwin.domain.entity.Question;
import com.heytwin.domain.entity.Topic;
import com.heytwin.domain.model.enums.DifficultyLevel;
import com.heytwin.domain.model.enums.QuestionType;
import com.heytwin.ingestion.model.OpenTdbQuestion;

@Component
public class OpenTdbMapper {
    private final ObjectMapper objectMapper;

    public OpenTdbMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Question toQuestion(OpenTdbQuestion source, Topic topic) {
        List<String> options = mergeOptions(source);
        String prompt = sanitize(source.question());
        String explanation = "Source: OpenTDB";
        return Question.builder()
                .topic(topic)
                .prompt(prompt)
                .optionsJson(writeJson(options))
                .correctAnswerJson(writeJson(sanitize(source.correctAnswer())))
                .explanation(explanation)
                .difficulty(mapDifficulty(source.difficulty()))
                .tags(new String[] { topic.getSubject(), topic.getName(), mapDifficulty(source.difficulty()).name() })
                .estimatedTimeSec(45)
                .build();
    }

    private List<String> mergeOptions(OpenTdbQuestion source) {
        List<String> merged = new ArrayList<>();
        if (source.incorrectAnswers() != null) {
            source.incorrectAnswers().stream().map(this::sanitize).forEach(merged::add);
        }
        merged.add(sanitize(source.correctAnswer()));
        Collections.shuffle(merged);
        return merged;
    }

    private DifficultyLevel mapDifficulty(String value) {
        if (!StringUtils.hasText(value)) {
            return DifficultyLevel.MEDIUM;
        }
        return switch (value.toLowerCase(Locale.ROOT)) {
            case "easy" -> DifficultyLevel.EASY;
            case "hard" -> DifficultyLevel.HARD;
            default -> DifficultyLevel.MEDIUM;
        };
    }

    @SuppressWarnings("unused")
    private QuestionType mapType(String type) {
        if ("boolean".equalsIgnoreCase(type)) {
            return QuestionType.TRUE_FALSE;
        }
        return QuestionType.MULTIPLE_CHOICE;
    }

    private String sanitize(String input) {
        @SuppressWarnings("null")
        String value = HtmlUtils.htmlUnescape(input == null ? "" : input.trim());
        return value == null ? "" : value;
    }

    private String writeJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException("Failed to serialize question payload", e);
        }
    }
}
