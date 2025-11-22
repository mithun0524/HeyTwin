package com.heytwin.dto;

import com.heytwin.domain.model.enums.DifficultyLevel;
import com.heytwin.domain.model.enums.QuestionType;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class QuestionDto {
    String questionId;
    String topicId;
    String topicName;
    DifficultyLevel difficulty;
    QuestionType questionType;
    String prompt;
    List<String> options;
    Integer estimatedTimeSec;
}
