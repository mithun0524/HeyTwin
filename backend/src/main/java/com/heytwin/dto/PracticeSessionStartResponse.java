package com.heytwin.dto;

import java.util.List;
import java.util.Map;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PracticeSessionStartResponse {
    String sessionId;
    List<QuestionDto> questions;
    Map<String, Integer> selectionRationale;
}
