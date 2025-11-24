package com.heytwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.heytwin.ingestion.OpenTdbProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(OpenTdbProperties.class)
public class HeytwinApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeytwinApplication.class, args);
    }
}
