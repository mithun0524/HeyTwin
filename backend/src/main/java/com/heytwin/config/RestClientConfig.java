package com.heytwin.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfig {

    @Bean
    public RestTemplate aiRestTemplate(RestTemplateBuilder builder, AiServiceProperties properties) {
        ClientHttpRequestInterceptor authInterceptor = (request, body, execution) -> {
            request.getHeaders().add("X-Service-Key", properties.getApiKey());
            return execution.execute(request, body);
        };
        return builder
                .rootUri(properties.getBaseUrl())
                .additionalInterceptors(authInterceptor)
                .setConnectTimeout(java.time.Duration.ofSeconds(5))
                .setReadTimeout(java.time.Duration.ofSeconds(5))
                .build();
    }
}
