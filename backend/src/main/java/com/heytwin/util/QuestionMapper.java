package com.heytwin.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heytwin.domain.entity.Question;
import com.heytwin.dto.QuestionDto;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    private final ObjectMapper objectMapper;

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder().questionId(question.getId().toString()).topicId(question.getTopic().getId().toString()).topicName(question.getTopic().getName()).difficulty(question.getDifficulty()).questionType(question.getQuestionType()).prompt(question.getPrompt()).options(readOptions(question.getOptionsJson())).estimatedTimeSec(question.getEstimatedTimeSec()).build();
    }

    private List<String> readOptions(String optionsJson) {
        if (optionsJson == null) {
            return Collections.emptyList();
        }
        try {
            return objectMapper.readValue(optionsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, String.class));
        } catch (JsonProcessingException e) {
            return Collections.emptyList();
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public QuestionMapper(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
