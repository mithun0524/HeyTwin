package com.heytwin.client.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AiPredictionRequest {
    String modelId;
    List<Instance> instances;

    @Value
    @Builder
    public static class Instance {
        String studentId;
        String questionId;
        Map<String, Object> features;
    }
}
