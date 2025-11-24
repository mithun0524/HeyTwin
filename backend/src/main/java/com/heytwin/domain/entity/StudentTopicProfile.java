package com.heytwin.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "student_topic_profile")
public class StudentTopicProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @Column(nullable = false)
    private Double accuracy;
    @Column(name = "avg_time_sec", nullable = false)
    private Double averageTimeSec;
    @Column(name = "difficulty_score", nullable = false)
    private Double difficultyScore;
    @Column(name = "consistency_score", nullable = false)
    private Double consistencyScore;
    @Column(name = "forgetting_score", nullable = false)
    private Double forgettingScore;
    @Column(name = "mastery_level", nullable = false)
    private Double masteryLevel;
    @Column(name = "recent_window", columnDefinition = "jsonb")
    private String recentWindow;
    @Column(name = "last_updated", nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    @SuppressWarnings("unused")
    void onCreate() {
        lastUpdated = OffsetDateTime.now();
    }

    @PreUpdate
    @SuppressWarnings("unused")
    void onUpdate() {
        lastUpdated = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class StudentTopicProfileBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private User student;
        @java.lang.SuppressWarnings("all")
        
        private Topic topic;
        @java.lang.SuppressWarnings("all")
        
        private Double accuracy;
        @java.lang.SuppressWarnings("all")
        
        private Double averageTimeSec;
        @java.lang.SuppressWarnings("all")
        
        private Double difficultyScore;
        @java.lang.SuppressWarnings("all")
        
        private Double consistencyScore;
        @java.lang.SuppressWarnings("all")
        
        private Double forgettingScore;
        @java.lang.SuppressWarnings("all")
        
        private Double masteryLevel;
        @java.lang.SuppressWarnings("all")
        
        private String recentWindow;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime lastUpdated;

        @java.lang.SuppressWarnings("all")
        
        StudentTopicProfileBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder student(final User student) {
            this.student = student;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder topic(final Topic topic) {
            this.topic = topic;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder accuracy(final Double accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder averageTimeSec(final Double averageTimeSec) {
            this.averageTimeSec = averageTimeSec;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder difficultyScore(final Double difficultyScore) {
            this.difficultyScore = difficultyScore;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder consistencyScore(final Double consistencyScore) {
            this.consistencyScore = consistencyScore;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder forgettingScore(final Double forgettingScore) {
            this.forgettingScore = forgettingScore;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder masteryLevel(final Double masteryLevel) {
            this.masteryLevel = masteryLevel;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder recentWindow(final String recentWindow) {
            this.recentWindow = recentWindow;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile.StudentTopicProfileBuilder lastUpdated(final OffsetDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public StudentTopicProfile build() {
            return new StudentTopicProfile(this.id, this.student, this.topic, this.accuracy, this.averageTimeSec, this.difficultyScore, this.consistencyScore, this.forgettingScore, this.masteryLevel, this.recentWindow, this.lastUpdated);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "StudentTopicProfile.StudentTopicProfileBuilder(id=" + this.id + ", student=" + this.student + ", topic=" + this.topic + ", accuracy=" + this.accuracy + ", averageTimeSec=" + this.averageTimeSec + ", difficultyScore=" + this.difficultyScore + ", consistencyScore=" + this.consistencyScore + ", forgettingScore=" + this.forgettingScore + ", masteryLevel=" + this.masteryLevel + ", recentWindow=" + this.recentWindow + ", lastUpdated=" + this.lastUpdated + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static StudentTopicProfile.StudentTopicProfileBuilder builder() {
        return new StudentTopicProfile.StudentTopicProfileBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    
    public User getStudent() {
        return this.student;
    }

    @java.lang.SuppressWarnings("all")
    
    public Topic getTopic() {
        return this.topic;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getAccuracy() {
        return this.accuracy;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getAverageTimeSec() {
        return this.averageTimeSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getDifficultyScore() {
        return this.difficultyScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getConsistencyScore() {
        return this.consistencyScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getForgettingScore() {
        return this.forgettingScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getMasteryLevel() {
        return this.masteryLevel;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getRecentWindow() {
        return this.recentWindow;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getLastUpdated() {
        return this.lastUpdated;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setId(final UUID id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setStudent(final User student) {
        this.student = student;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTopic(final Topic topic) {
        this.topic = topic;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAccuracy(final Double accuracy) {
        this.accuracy = accuracy;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAverageTimeSec(final Double averageTimeSec) {
        this.averageTimeSec = averageTimeSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setDifficultyScore(final Double difficultyScore) {
        this.difficultyScore = difficultyScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setConsistencyScore(final Double consistencyScore) {
        this.consistencyScore = consistencyScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setForgettingScore(final Double forgettingScore) {
        this.forgettingScore = forgettingScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setMasteryLevel(final Double masteryLevel) {
        this.masteryLevel = masteryLevel;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setRecentWindow(final String recentWindow) {
        this.recentWindow = recentWindow;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @java.lang.SuppressWarnings("all")
    
    public StudentTopicProfile() {
    }

    @java.lang.SuppressWarnings("all")
    
    public StudentTopicProfile(final UUID id, final User student, final Topic topic, final Double accuracy, final Double averageTimeSec, final Double difficultyScore, final Double consistencyScore, final Double forgettingScore, final Double masteryLevel, final String recentWindow, final OffsetDateTime lastUpdated) {
        this.id = id;
        this.student = student;
        this.topic = topic;
        this.accuracy = accuracy;
        this.averageTimeSec = averageTimeSec;
        this.difficultyScore = difficultyScore;
        this.consistencyScore = consistencyScore;
        this.forgettingScore = forgettingScore;
        this.masteryLevel = masteryLevel;
        this.recentWindow = recentWindow;
        this.lastUpdated = lastUpdated;
    }
}
