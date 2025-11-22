package com.heytwin.dto;

import com.heytwin.domain.model.enums.SessionStrategy;
import com.heytwin.domain.model.enums.SessionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class PracticeSessionStartRequest {
    @NotNull
    private SessionType sessionType = SessionType.PRACTICE;

    @NotNull
    private SessionStrategy strategy = SessionStrategy.BALANCED;

    @Min(5)
    @Max(20)
    private int count = 10;

    private List<UUID> topicFilter;
    private List<String> targetDifficulty;
}
