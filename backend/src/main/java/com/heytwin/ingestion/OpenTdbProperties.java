package com.heytwin.ingestion;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ingestion.opentdb")
public class OpenTdbProperties {
    /** Base URL for OpenTDB API */
    private String baseUrl = "https://opentdb.com";
    /** Default amount of questions to request */
    private int defaultAmount = 50;
    /** Cron expression for scheduled imports */
    private String cron = "0 0 2 * * *";
    /** Whether scheduled imports are enabled */
    private boolean enabled = true;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public int getDefaultAmount() {
        return defaultAmount;
    }

    public void setDefaultAmount(int defaultAmount) {
        this.defaultAmount = defaultAmount;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
