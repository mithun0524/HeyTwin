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
import jakarta.persistence.Table;

@Entity
@Table(name = "responses")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "session_id", nullable = false)
    private PracticeSession session;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
    @Column(name = "selected_option", columnDefinition = "text")
    private String selectedOption;
    @Column(name = "is_correct")
    private Boolean correct;
    @Column(name = "ai_pred_prob")
    private Double aiPredictedProbability;
    @Column(name = "time_taken_sec")
    private Integer timeTakenSec;
    @Column(name = "submitted_at", nullable = false)
    private OffsetDateTime submittedAt;

    @PrePersist
    @SuppressWarnings("unused")
    void onCreate() {
        submittedAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class ResponseBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private PracticeSession session;
        @java.lang.SuppressWarnings("all")
        
        private User student;
        @java.lang.SuppressWarnings("all")
        
        private Question question;
        @java.lang.SuppressWarnings("all")
        
        private String selectedOption;
        @java.lang.SuppressWarnings("all")
        
        private Boolean correct;
        @java.lang.SuppressWarnings("all")
        
        private Double aiPredictedProbability;
        @java.lang.SuppressWarnings("all")
        
        private Integer timeTakenSec;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime submittedAt;

        @java.lang.SuppressWarnings("all")
        
        ResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder session(final PracticeSession session) {
            this.session = session;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder student(final User student) {
            this.student = student;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder question(final Question question) {
            this.question = question;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder selectedOption(final String selectedOption) {
            this.selectedOption = selectedOption;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder correct(final Boolean correct) {
            this.correct = correct;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder aiPredictedProbability(final Double aiPredictedProbability) {
            this.aiPredictedProbability = aiPredictedProbability;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder timeTakenSec(final Integer timeTakenSec) {
            this.timeTakenSec = timeTakenSec;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Response.ResponseBuilder submittedAt(final OffsetDateTime submittedAt) {
            this.submittedAt = submittedAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public Response build() {
            return new Response(this.id, this.session, this.student, this.question, this.selectedOption, this.correct, this.aiPredictedProbability, this.timeTakenSec, this.submittedAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "Response.ResponseBuilder(id=" + this.id + ", session=" + this.session + ", student=" + this.student + ", question=" + this.question + ", selectedOption=" + this.selectedOption + ", correct=" + this.correct + ", aiPredictedProbability=" + this.aiPredictedProbability + ", timeTakenSec=" + this.timeTakenSec + ", submittedAt=" + this.submittedAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static Response.ResponseBuilder builder() {
        return new Response.ResponseBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    
    public PracticeSession getSession() {
        return this.session;
    }

    @java.lang.SuppressWarnings("all")
    
    public User getStudent() {
        return this.student;
    }

    @java.lang.SuppressWarnings("all")
    
    public Question getQuestion() {
        return this.question;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getSelectedOption() {
        return this.selectedOption;
    }

    @java.lang.SuppressWarnings("all")
    
    public Boolean getCorrect() {
        return this.correct;
    }

    @java.lang.SuppressWarnings("all")
    
    public Double getAiPredictedProbability() {
        return this.aiPredictedProbability;
    }

    @java.lang.SuppressWarnings("all")
    
    public Integer getTimeTakenSec() {
        return this.timeTakenSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getSubmittedAt() {
        return this.submittedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setId(final UUID id) {
        this.id = id;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setSession(final PracticeSession session) {
        this.session = session;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setStudent(final User student) {
        this.student = student;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setQuestion(final Question question) {
        this.question = question;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setSelectedOption(final String selectedOption) {
        this.selectedOption = selectedOption;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCorrect(final Boolean correct) {
        this.correct = correct;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setAiPredictedProbability(final Double aiPredictedProbability) {
        this.aiPredictedProbability = aiPredictedProbability;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTimeTakenSec(final Integer timeTakenSec) {
        this.timeTakenSec = timeTakenSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setSubmittedAt(final OffsetDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public Response() {
    }

    @java.lang.SuppressWarnings("all")
    
    public Response(final UUID id, final PracticeSession session, final User student, final Question question, final String selectedOption, final Boolean correct, final Double aiPredictedProbability, final Integer timeTakenSec, final OffsetDateTime submittedAt) {
        this.id = id;
        this.session = session;
        this.student = student;
        this.question = question;
        this.selectedOption = selectedOption;
        this.correct = correct;
        this.aiPredictedProbability = aiPredictedProbability;
        this.timeTakenSec = timeTakenSec;
        this.submittedAt = submittedAt;
    }
}
