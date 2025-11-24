package com.heytwin.domain.entity;

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
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "student_achievements")
public class StudentAchievement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "achievement_id", nullable = false)
    private Achievement achievement;
    @Column(nullable = false)
    private Double progress;
    @Column(nullable = false)
    private Boolean awarded;
    @Column(name = "awarded_at")
    private OffsetDateTime awardedAt;
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    void onCreate() {
        updatedAt = OffsetDateTime.now();
        if (awarded == null) {
            awarded = Boolean.FALSE;
        }
        if (progress == null) {
            progress = 0.0;
        }
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class StudentAchievementBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private User student;
        @java.lang.SuppressWarnings("all")
        
        private Achievement achievement;
        @java.lang.SuppressWarnings("all")
        
        private Double progress;
        @java.lang.SuppressWarnings("all")
        
        private Boolean awarded;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime awardedAt;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime updatedAt;

        @java.lang.SuppressWarnings("all")
        
        StudentAchievementBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder student(final User student) {
            this.student = student;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder achievement(final Achievement achievement) {
            this.achievement = achievement;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder progress(final Double progress) {
            this.progress = progress;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder awarded(final Boolean awarded) {
            this.awarded = awarded;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder awardedAt(final OffsetDateTime awardedAt) {
            this.awardedAt = awardedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement.StudentAchievementBuilder updatedAt(final OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public StudentAchievement build() {
            return new StudentAchievement(this.id, this.student, this.achievement, this.progress, this.awarded, this.awardedAt, this.updatedAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "StudentAchievement.StudentAchievementBuilder(id=" + this.id + ", student=" + this.student + ", achievement=" + this.achievement + ", progress=" + this.progress + ", awarded=" + this.awarded + ", awardedAt=" + this.awardedAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static StudentAchievement.StudentAchievementBuilder builder() {
        return new StudentAchievement.StudentAchievementBuilder();
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
    
    public Achievement getAchievement() {
        return this.achievement;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getProgress() {
        return this.progress;
    }

    @java.lang.SuppressWarnings("all")
    
    public Boolean getAwarded() {
        return this.awarded;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getAwardedAt() {
        return this.awardedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getUpdatedAt() {
        return this.updatedAt;
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
    
    public void setAchievement(final Achievement achievement) {
        this.achievement = achievement;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setProgress(final Double progress) {
        this.progress = progress;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAwarded(final Boolean awarded) {
        this.awarded = awarded;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAwardedAt(final OffsetDateTime awardedAt) {
        this.awardedAt = awardedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setUpdatedAt(final OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public StudentAchievement() {
    }

    @java.lang.SuppressWarnings("all")
    
    public StudentAchievement(final UUID id, final User student, final Achievement achievement, final Double progress, final Boolean awarded, final OffsetDateTime awardedAt, final OffsetDateTime updatedAt) {
        this.id = id;
        this.student = student;
        this.achievement = achievement;
        this.progress = progress;
        this.awarded = awarded;
        this.awardedAt = awardedAt;
        this.updatedAt = updatedAt;
    }
}
