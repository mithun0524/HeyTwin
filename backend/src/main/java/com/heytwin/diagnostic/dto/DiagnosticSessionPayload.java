package com.heytwin.diagnostic.dto;

import com.heytwin.dto.QuestionDto;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DiagnosticSessionPayload {
    String sessionId;
    List<QuestionDto> questions;
}
