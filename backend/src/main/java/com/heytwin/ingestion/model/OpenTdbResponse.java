package com.heytwin.ingestion.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OpenTdbResponse(@JsonProperty("response_code") int responseCode, List<OpenTdbQuestion> results) {
}
