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

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String subject;
    private String description;
    @Column(name = "difficulty_band")
    private String difficultyBand;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class TopicBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private String name;
        @java.lang.SuppressWarnings("all")
        
        private String subject;
        @java.lang.SuppressWarnings("all")
        
        private String description;
        @java.lang.SuppressWarnings("all")
        
        private String difficultyBand;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime createdAt;

        @java.lang.SuppressWarnings("all")
        
        TopicBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Topic.TopicBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Topic.TopicBuilder name(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Topic.TopicBuilder subject(final String subject) {
            this.subject = subject;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Topic.TopicBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Topic.TopicBuilder difficultyBand(final String difficultyBand) {
            this.difficultyBand = difficultyBand;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Topic.TopicBuilder createdAt(final OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public Topic build() {
            return new Topic(this.id, this.name, this.subject, this.description, this.difficultyBand, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "Topic.TopicBuilder(id=" + this.id + ", name=" + this.name + ", subject=" + this.subject + ", description=" + this.description + ", difficultyBand=" + this.difficultyBand + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static Topic.TopicBuilder builder() {
        return new Topic.TopicBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getName() {
        return this.name;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getSubject() {
        return this.subject;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getDescription() {
        return this.description;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getDifficultyBand() {
        return this.difficultyBand;
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
    
    public void setName(final String name) {
        this.name = name;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setSubject(final String subject) {
        this.subject = subject;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setDescription(final String description) {
        this.description = description;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setDifficultyBand(final String difficultyBand) {
        this.difficultyBand = difficultyBand;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public Topic() {
    }

    @java.lang.SuppressWarnings("all")
    
    public Topic(final UUID id, final String name, final String subject, final String description, final String difficultyBand, final OffsetDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.description = description;
        this.difficultyBand = difficultyBand;
        this.createdAt = createdAt;
    }
}
