package com.heytwin.ingestion;

import com.heytwin.ingestion.model.OpenTdbResponse;

public interface OpenTdbClient {
    OpenTdbResponse fetchQuestions(int amount);
}
