package com.heytwin.client.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AiPredictionResponse {
    String modelId;
    List<Item> predictions;

    @Value
    @Builder
    public static class Item {
        String questionId;
        double prob;
    }
}
