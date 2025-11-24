package com.heytwin.ingestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class QuestionIngestionScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionIngestionScheduler.class);

    private final QuestionIngestionService questionIngestionService;
    private final OpenTdbProperties openTdbProperties;

    public QuestionIngestionScheduler(QuestionIngestionService questionIngestionService,
            OpenTdbProperties openTdbProperties) {
        this.questionIngestionService = questionIngestionService;
        this.openTdbProperties = openTdbProperties;
    }

    @Scheduled(cron = "${ingestion.opentdb.cron:0 0 2 * * *}")
    public void scheduledImport() {
        if (!openTdbProperties.isEnabled()) {
            LOGGER.debug("OpenTDB scheduled import disabled via configuration");
            return;
        }
        int amount = openTdbProperties.getDefaultAmount();
        LOGGER.info("Starting scheduled OpenTDB import for {} questions", amount);
        questionIngestionService.importFromOpenTdb(amount);
    }
}
