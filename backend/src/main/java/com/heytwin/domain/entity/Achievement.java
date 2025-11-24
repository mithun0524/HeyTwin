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
@Table(name = "achievements")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "jsonb", nullable = false)
    private String criteria;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    void onCreate() {
        createdAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class AchievementBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private String code;
        @java.lang.SuppressWarnings("all")
        
        private String title;
        @java.lang.SuppressWarnings("all")
        
        private String description;
        @java.lang.SuppressWarnings("all")
        
        private String criteria;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime createdAt;

        @java.lang.SuppressWarnings("all")
        
        AchievementBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Achievement.AchievementBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Achievement.AchievementBuilder code(final String code) {
            this.code = code;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Achievement.AchievementBuilder title(final String title) {
            this.title = title;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Achievement.AchievementBuilder description(final String description) {
            this.description = description;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Achievement.AchievementBuilder criteria(final String criteria) {
            this.criteria = criteria;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Achievement.AchievementBuilder createdAt(final OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public Achievement build() {
            return new Achievement(this.id, this.code, this.title, this.description, this.criteria, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "Achievement.AchievementBuilder(id=" + this.id + ", code=" + this.code + ", title=" + this.title + ", description=" + this.description + ", criteria=" + this.criteria + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static Achievement.AchievementBuilder builder() {
        return new Achievement.AchievementBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getCode() {
        return this.code;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getTitle() {
        return this.title;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getDescription() {
        return this.description;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getCriteria() {
        return this.criteria;
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
    
    public void setCode(final String code) {
        this.code = code;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTitle(final String title) {
        this.title = title;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setDescription(final String description) {
        this.description = description;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCriteria(final String criteria) {
        this.criteria = criteria;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public Achievement() {
    }

    @java.lang.SuppressWarnings("all")
    
    public Achievement(final UUID id, final String code, final String title, final String description, final String criteria, final OffsetDateTime createdAt) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.description = description;
        this.criteria = criteria;
        this.createdAt = createdAt;
    }
}
