package com.heytwin.dto;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiResponse<T> {
    @Builder.Default
    private final OffsetDateTime timestamp = OffsetDateTime.now();
    private final int status;
    private final String path;
    private final T data;
}
