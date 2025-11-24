package com.heytwin.ingestion;

import java.time.Duration;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import com.heytwin.ingestion.model.OpenTdbResponse;

@Component
public class OpenTdbClientImpl implements OpenTdbClient {
    private static final Logger log = LoggerFactory.getLogger(OpenTdbClientImpl.class);
    private final WebClient webClient;
    private final OpenTdbProperties properties;
    private final String baseUrl;

    public OpenTdbClientImpl(WebClient.Builder webClientBuilder, OpenTdbProperties properties) {
        this.properties = properties;
        this.baseUrl = Objects.requireNonNullElse(properties.getBaseUrl(), "https://opentdb.com");
    this.webClient = webClientBuilder.baseUrl(Objects.requireNonNull(this.baseUrl)).build();
    }

    @Override
    public OpenTdbResponse fetchQuestions(int amount) {
        int requestAmount = amount > 0 ? amount : properties.getDefaultAmount();
    var uri = UriComponentsBuilder.fromUriString(Objects.requireNonNull(baseUrl))
                .path("/api.php")
                .queryParam("amount", requestAmount)
                .build(true)
        .toUri();

        log.info("Requesting {} questions from OpenTDB", requestAmount);
        return webClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(OpenTdbResponse.class)
                .block(Duration.ofSeconds(10));
    }
}
