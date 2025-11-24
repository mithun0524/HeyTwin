package com.heytwin.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "model_training_runs")
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
    @SuppressWarnings("unused")
    void onCreate() {
        createdAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class ModelTrainingRunBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private String modelId;
        @java.lang.SuppressWarnings("all")
        
        private String modelType;
        @java.lang.SuppressWarnings("all")
        
        private String trainingMode;
        @java.lang.SuppressWarnings("all")
        
        private Double accuracy;
        @java.lang.SuppressWarnings("all")
        
        private Double f1;
        @java.lang.SuppressWarnings("all")
        
        private String dataVersion;
        @java.lang.SuppressWarnings("all")
        
        private String artifactPath;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime createdAt;

        @java.lang.SuppressWarnings("all")
        
        ModelTrainingRunBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder modelId(final String modelId) {
            this.modelId = modelId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder modelType(final String modelType) {
            this.modelType = modelType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder trainingMode(final String trainingMode) {
            this.trainingMode = trainingMode;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder accuracy(final Double accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder f1(final Double f1) {
            this.f1 = f1;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder dataVersion(final String dataVersion) {
            this.dataVersion = dataVersion;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder artifactPath(final String artifactPath) {
            this.artifactPath = artifactPath;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun.ModelTrainingRunBuilder createdAt(final OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public ModelTrainingRun build() {
            return new ModelTrainingRun(this.id, this.modelId, this.modelType, this.trainingMode, this.accuracy, this.f1, this.dataVersion, this.artifactPath, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "ModelTrainingRun.ModelTrainingRunBuilder(id=" + this.id + ", modelId=" + this.modelId + ", modelType=" + this.modelType + ", trainingMode=" + this.trainingMode + ", accuracy=" + this.accuracy + ", f1=" + this.f1 + ", dataVersion=" + this.dataVersion + ", artifactPath=" + this.artifactPath + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static ModelTrainingRun.ModelTrainingRunBuilder builder() {
        return new ModelTrainingRun.ModelTrainingRunBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getModelId() {
        return this.modelId;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getModelType() {
        return this.modelType;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getTrainingMode() {
        return this.trainingMode;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getAccuracy() {
        return this.accuracy;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getF1() {
        return this.f1;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getDataVersion() {
        return this.dataVersion;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getArtifactPath() {
        return this.artifactPath;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setId(final UUID id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setModelId(final String modelId) {
        this.modelId = modelId;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setModelType(final String modelType) {
        this.modelType = modelType;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTrainingMode(final String trainingMode) {
        this.trainingMode = trainingMode;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAccuracy(final Double accuracy) {
        this.accuracy = accuracy;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setF1(final Double f1) {
        this.f1 = f1;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setDataVersion(final String dataVersion) {
        this.dataVersion = dataVersion;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setArtifactPath(final String artifactPath) {
        this.artifactPath = artifactPath;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public ModelTrainingRun() {
    }

    @java.lang.SuppressWarnings("all")
    
    public ModelTrainingRun(final UUID id, final String modelId, final String modelType, final String trainingMode, final Double accuracy, final Double f1, final String dataVersion, final String artifactPath, final OffsetDateTime createdAt) {
        this.id = id;
        this.modelId = modelId;
        this.modelType = modelType;
        this.trainingMode = trainingMode;
        this.accuracy = accuracy;
        this.f1 = f1;
        this.dataVersion = dataVersion;
        this.artifactPath = artifactPath;
        this.createdAt = createdAt;
    }
}
