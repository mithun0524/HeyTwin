package com.heytwin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ai.service")
public class AiServiceProperties {
    private String baseUrl;
    private String apiKey;

    @java.lang.SuppressWarnings("all")
    
    public AiServiceProperties() {
    }

    @java.lang.SuppressWarnings("all")
    
    public String getBaseUrl() {
        return this.baseUrl;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getApiKey() {
        return this.apiKey;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setBaseUrl(final String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof AiServiceProperties)) return false;
        final AiServiceProperties other = (AiServiceProperties) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$baseUrl = this.getBaseUrl();
        final java.lang.Object other$baseUrl = other.getBaseUrl();
        if (this$baseUrl == null ? other$baseUrl != null : !this$baseUrl.equals(other$baseUrl)) return false;
        final java.lang.Object this$apiKey = this.getApiKey();
        final java.lang.Object other$apiKey = other.getApiKey();
        if (this$apiKey == null ? other$apiKey != null : !this$apiKey.equals(other$apiKey)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof AiServiceProperties;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $baseUrl = this.getBaseUrl();
        result = result * PRIME + ($baseUrl == null ? 43 : $baseUrl.hashCode());
        final java.lang.Object $apiKey = this.getApiKey();
        result = result * PRIME + ($apiKey == null ? 43 : $apiKey.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "AiServiceProperties(baseUrl=" + this.getBaseUrl() + ", apiKey=" + this.getApiKey() + ")";
    }
}
