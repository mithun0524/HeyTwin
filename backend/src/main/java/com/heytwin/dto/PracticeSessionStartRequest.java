package com.heytwin.dto;

import com.heytwin.domain.model.enums.SessionStrategy;
import com.heytwin.domain.model.enums.SessionType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
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

    public PracticeSessionStartRequest() {
    }

    public SessionType getSessionType() {
        return sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public SessionStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(SessionStrategy strategy) {
        this.strategy = strategy;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<UUID> getTopicFilter() {
        return topicFilter;
    }

    public void setTopicFilter(List<UUID> topicFilter) {
        this.topicFilter = topicFilter;
    }

    public List<String> getTargetDifficulty() {
        return targetDifficulty;
    }

    public void setTargetDifficulty(List<String> targetDifficulty) {
        this.targetDifficulty = targetDifficulty;
    }
}
