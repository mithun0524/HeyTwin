package com.heytwin.digitaltwin.model;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DigitalTwinSummary {
    double accuracy;
    double averageTimeSec;
    double consistency;
    double difficultyScore;
    double forgettingScore;
    List<TopicSnapshot> topics;

    @Value
    @Builder
    public static class TopicSnapshot {
        String topicId;
        String name;
        double mastery;
        double trend;
        String recommendation;
    }
}
