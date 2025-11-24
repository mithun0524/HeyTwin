package com.heytwin.client;

import com.heytwin.client.dto.AiPredictionRequest;
import com.heytwin.client.dto.AiPredictionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AiServiceClient {
    private static final Logger log = LoggerFactory.getLogger(AiServiceClient.class);
    private final RestTemplate aiRestTemplate;

    public AiPredictionResponse predict(AiPredictionRequest request) {
        try {
            ResponseEntity<AiPredictionResponse> response = aiRestTemplate.exchange("/predict", HttpMethod.POST, new HttpEntity<>(request), AiPredictionResponse.class);
            if (response.getBody() != null) {
                return response.getBody();
            }
        } catch (Exception ex) {
            log.error("AI prediction call failed", ex);
        }
        return AiPredictionResponse.builder().modelId(request.getModelId()).predictions(request.getInstances().stream().map(instance -> AiPredictionResponse.Item.builder().questionId(instance.getQuestionId()).prob(0.5).build()).toList()).build();
    }

    @java.lang.SuppressWarnings("all")
    
    public AiServiceClient(final RestTemplate aiRestTemplate) {
        this.aiRestTemplate = aiRestTemplate;
    }
}
