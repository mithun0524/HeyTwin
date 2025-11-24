package com.heytwin.dto;

import java.time.OffsetDateTime;

public class ApiResponse<T> {
    private final OffsetDateTime timestamp;
    private final int status;
    private final String path;
    private final T data;

    @java.lang.SuppressWarnings("all")
    
    private static <T> OffsetDateTime $default$timestamp() {
        return OffsetDateTime.now();
    }

    @java.lang.SuppressWarnings("all")
    
    ApiResponse(final OffsetDateTime timestamp, final int status, final String path, final T data) {
        this.timestamp = timestamp;
        this.status = status;
        this.path = path;
        this.data = data;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class ApiResponseBuilder<T> {
        @java.lang.SuppressWarnings("all")
        
        private boolean timestamp$set;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime timestamp$value;
        @java.lang.SuppressWarnings("all")
        
        private int status;
        @java.lang.SuppressWarnings("all")
        
        private String path;
        @java.lang.SuppressWarnings("all")
        
        private T data;

        @java.lang.SuppressWarnings("all")
        
        ApiResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ApiResponse.ApiResponseBuilder<T> timestamp(final OffsetDateTime timestamp) {
            this.timestamp$value = timestamp;
            timestamp$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ApiResponse.ApiResponseBuilder<T> status(final int status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ApiResponse.ApiResponseBuilder<T> path(final String path) {
            this.path = path;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ApiResponse.ApiResponseBuilder<T> data(final T data) {
            this.data = data;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public ApiResponse<T> build() {
            OffsetDateTime timestamp$value = this.timestamp$value;
            if (!this.timestamp$set) timestamp$value = ApiResponse.<T>$default$timestamp();
            return new ApiResponse<T>(timestamp$value, this.status, this.path, this.data);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "ApiResponse.ApiResponseBuilder(timestamp$value=" + this.timestamp$value + ", status=" + this.status + ", path=" + this.path + ", data=" + this.data + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static <T> ApiResponse.ApiResponseBuilder<T> builder() {
        return new ApiResponse.ApiResponseBuilder<T>();
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    @java.lang.SuppressWarnings("all")
    
    public int getStatus() {
        return this.status;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getPath() {
        return this.path;
    }

    @java.lang.SuppressWarnings("all")
    
    public T getData() {
        return this.data;
    }
}
