package com.heytwin.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "model_training_runs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModelTrainingRun {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "model_id", nullable = false)
    private String modelId;

    @Column(name = "model_type", nullable = false)
    private String modelType;

    @Column(name = "training_mode", nullable = false)
    private String trainingMode;

    private Double accuracy;
    private Double f1;

    @Column(name = "data_version")
    private String dataVersion;

    @Column(name = "artifact_path")
    private String artifactPath;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = OffsetDateTime.now();
    }
}
