package com.heytwin.config;

import com.heytwin.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgument(IllegalArgumentException ex, org.springframework.web.context.request.WebRequest request) {
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false))
                .data(ex.getMessage())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidation(MethodArgumentNotValidException ex, org.springframework.web.context.request.WebRequest request) {
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false))
                .data(ex.getBindingResult().getFieldErrors())
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneric(Exception ex, org.springframework.web.context.request.WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponse.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .path(request.getDescription(false))
                        .data("Unexpected error")
                        .build());
    }
}
