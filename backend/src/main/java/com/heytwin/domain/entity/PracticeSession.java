package com.heytwin.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.heytwin.domain.model.enums.SessionStrategy;
import com.heytwin.domain.model.enums.SessionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "practice_sessions")
public class PracticeSession {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "session_type", nullable = false, columnDefinition = "session_type")
    private SessionType sessionType;
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Column(name = "selection_strategy", nullable = false, columnDefinition = "session_strategy")
    private SessionStrategy selectionStrategy;
    @Column(nullable = false)
    private String status;
    @Column(name = "question_count", nullable = false)
    private Integer questionCount;
    @Column(name = "ai_model_id")
    private String aiModelId;
    @Column(name = "started_at", nullable = false)
    private OffsetDateTime startedAt;
    @Column(name = "completed_at")
    private OffsetDateTime completedAt;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private String metadata;

    @PrePersist
    @SuppressWarnings("unused")
    void onCreate() {
        if (startedAt == null) {
            startedAt = OffsetDateTime.now();
        }
        if (status == null) {
            status = "IN_PROGRESS";
        }
    }


    @java.lang.SuppressWarnings("all")
    
    public static class PracticeSessionBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private User student;
        @java.lang.SuppressWarnings("all")
        
        private SessionType sessionType;
        @java.lang.SuppressWarnings("all")
        
        private SessionStrategy selectionStrategy;
        @java.lang.SuppressWarnings("all")
        
        private String status;
        @java.lang.SuppressWarnings("all")
        
        private Integer questionCount;
        @java.lang.SuppressWarnings("all")
        
        private String aiModelId;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime startedAt;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime completedAt;
        @java.lang.SuppressWarnings("all")
        
        private String metadata;

        @java.lang.SuppressWarnings("all")
        
        PracticeSessionBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder student(final User student) {
            this.student = student;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder sessionType(final SessionType sessionType) {
            this.sessionType = sessionType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder selectionStrategy(final SessionStrategy selectionStrategy) {
            this.selectionStrategy = selectionStrategy;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder status(final String status) {
            this.status = status;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder questionCount(final Integer questionCount) {
            this.questionCount = questionCount;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder aiModelId(final String aiModelId) {
            this.aiModelId = aiModelId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder startedAt(final OffsetDateTime startedAt) {
            this.startedAt = startedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder completedAt(final OffsetDateTime completedAt) {
            this.completedAt = completedAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSession.PracticeSessionBuilder metadata(final String metadata) {
            this.metadata = metadata;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public PracticeSession build() {
            return new PracticeSession(this.id, this.student, this.sessionType, this.selectionStrategy, this.status, this.questionCount, this.aiModelId, this.startedAt, this.completedAt, this.metadata);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "PracticeSession.PracticeSessionBuilder(id=" + this.id + ", student=" + this.student + ", sessionType=" + this.sessionType + ", selectionStrategy=" + this.selectionStrategy + ", status=" + this.status + ", questionCount=" + this.questionCount + ", aiModelId=" + this.aiModelId + ", startedAt=" + this.startedAt + ", completedAt=" + this.completedAt + ", metadata=" + this.metadata + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static PracticeSession.PracticeSessionBuilder builder() {
        return new PracticeSession.PracticeSessionBuilder();
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
    
    public SessionType getSessionType() {
        return this.sessionType;
    }

    @java.lang.SuppressWarnings("all")
    
    public SessionStrategy getSelectionStrategy() {
        return this.selectionStrategy;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getStatus() {
        return this.status;
    }

    @java.lang.SuppressWarnings("all")
    
    public Integer getQuestionCount() {
        return this.questionCount;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getAiModelId() {
        return this.aiModelId;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getStartedAt() {
        return this.startedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getCompletedAt() {
        return this.completedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getMetadata() {
        return this.metadata;
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
    
    public void setSessionType(final SessionType sessionType) {
        this.sessionType = sessionType;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setSelectionStrategy(final SessionStrategy selectionStrategy) {
        this.selectionStrategy = selectionStrategy;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setStatus(final String status) {
        this.status = status;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setQuestionCount(final Integer questionCount) {
        this.questionCount = questionCount;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAiModelId(final String aiModelId) {
        this.aiModelId = aiModelId;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setStartedAt(final OffsetDateTime startedAt) {
        this.startedAt = startedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCompletedAt(final OffsetDateTime completedAt) {
        this.completedAt = completedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setMetadata(final String metadata) {
        this.metadata = metadata;
    }

    @java.lang.SuppressWarnings("all")
    
    public PracticeSession() {
    }

    @java.lang.SuppressWarnings("all")
    
    public PracticeSession(final UUID id, final User student, final SessionType sessionType, final SessionStrategy selectionStrategy, final String status, final Integer questionCount, final String aiModelId, final OffsetDateTime startedAt, final OffsetDateTime completedAt, final String metadata) {
        this.id = id;
        this.student = student;
        this.sessionType = sessionType;
        this.selectionStrategy = selectionStrategy;
        this.status = status;
        this.questionCount = questionCount;
        this.aiModelId = aiModelId;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.metadata = metadata;
    }
}
