package com.heytwin.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.heytwin.domain.model.enums.DifficultyLevel;
import com.heytwin.domain.model.enums.QuestionType;

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
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
    @Column(nullable = false, columnDefinition = "text")
    private String prompt;
    @Column(name = "options", columnDefinition = "jsonb", nullable = false)
    private String optionsJson;
    @Column(name = "correct_answer", columnDefinition = "jsonb", nullable = false)
    private String correctAnswerJson;
    @Column(columnDefinition = "text")
    private String explanation;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DifficultyLevel difficulty;
    @Enumerated(EnumType.STRING)
    @Column(name = "question_type", nullable = false)
    private QuestionType questionType;
    @Column(columnDefinition = "text[]")
    private String[] tags;
    @Column(name = "estimated_time_sec", nullable = false)
    private Integer estimatedTimeSec;
    @Column(nullable = false)
    private Boolean active;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @PrePersist
    @SuppressWarnings("unused")
    void onCreate() {
        createdAt = OffsetDateTime.now();
    }

    @java.lang.SuppressWarnings("all")
    
    private static Boolean $default$active() {
        return Boolean.TRUE;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class QuestionBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private Topic topic;
        @java.lang.SuppressWarnings("all")
        
        private String prompt;
        @java.lang.SuppressWarnings("all")
        
        private String optionsJson;
        @java.lang.SuppressWarnings("all")
        
        private String correctAnswerJson;
        @java.lang.SuppressWarnings("all")
        
        private String explanation;
        @java.lang.SuppressWarnings("all")
        
        private DifficultyLevel difficulty;
        @java.lang.SuppressWarnings("all")
        
        private QuestionType questionType;
        @java.lang.SuppressWarnings("all")
        
        private String[] tags;
        @java.lang.SuppressWarnings("all")
        
        private Integer estimatedTimeSec;
        @java.lang.SuppressWarnings("all")
        
        private boolean active$set;
        @java.lang.SuppressWarnings("all")
        
        private Boolean active$value;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime createdAt;

        @java.lang.SuppressWarnings("all")
        
        QuestionBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder topic(final Topic topic) {
            this.topic = topic;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder prompt(final String prompt) {
            this.prompt = prompt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder optionsJson(final String optionsJson) {
            this.optionsJson = optionsJson;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder correctAnswerJson(final String correctAnswerJson) {
            this.correctAnswerJson = correctAnswerJson;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder explanation(final String explanation) {
            this.explanation = explanation;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder difficulty(final DifficultyLevel difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder questionType(final QuestionType questionType) {
            this.questionType = questionType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder tags(final String[] tags) {
            this.tags = tags;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder estimatedTimeSec(final Integer estimatedTimeSec) {
            this.estimatedTimeSec = estimatedTimeSec;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder active(final Boolean active) {
            this.active$value = active;
            active$set = true;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public Question.QuestionBuilder createdAt(final OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public Question build() {
            Boolean active$value = this.active$value;
            if (!this.active$set) active$value = Question.$default$active();
            return new Question(this.id, this.topic, this.prompt, this.optionsJson, this.correctAnswerJson, this.explanation, this.difficulty, this.questionType, this.tags, this.estimatedTimeSec, active$value, this.createdAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "Question.QuestionBuilder(id=" + this.id + ", topic=" + this.topic + ", prompt=" + this.prompt + ", optionsJson=" + this.optionsJson + ", correctAnswerJson=" + this.correctAnswerJson + ", explanation=" + this.explanation + ", difficulty=" + this.difficulty + ", questionType=" + this.questionType + ", tags=" + java.util.Arrays.deepToString(this.tags) + ", estimatedTimeSec=" + this.estimatedTimeSec + ", active$value=" + this.active$value + ", createdAt=" + this.createdAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static Question.QuestionBuilder builder() {
        return new Question.QuestionBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
    }

    @java.lang.SuppressWarnings("all")
    
    public Topic getTopic() {
        return this.topic;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getPrompt() {
        return this.prompt;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getOptionsJson() {
        return this.optionsJson;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getCorrectAnswerJson() {
        return this.correctAnswerJson;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getExplanation() {
        return this.explanation;
    }

    @java.lang.SuppressWarnings("all")
    
    public DifficultyLevel getDifficulty() {
        return this.difficulty;
    }

    @java.lang.SuppressWarnings("all")
    
    public QuestionType getQuestionType() {
        return this.questionType;
    }

    @java.lang.SuppressWarnings("all")
    
    public String[] getTags() {
        return this.tags;
    }

    @java.lang.SuppressWarnings("all")
    
    public Integer getEstimatedTimeSec() {
        return this.estimatedTimeSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public Boolean getActive() {
        return this.active;
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
    
    public void setTopic(final Topic topic) {
        this.topic = topic;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setPrompt(final String prompt) {
        this.prompt = prompt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setOptionsJson(final String optionsJson) {
        this.optionsJson = optionsJson;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCorrectAnswerJson(final String correctAnswerJson) {
        this.correctAnswerJson = correctAnswerJson;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setExplanation(final String explanation) {
        this.explanation = explanation;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setDifficulty(final DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setQuestionType(final QuestionType questionType) {
        this.questionType = questionType;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTags(final String[] tags) {
        this.tags = tags;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setEstimatedTimeSec(final Integer estimatedTimeSec) {
        this.estimatedTimeSec = estimatedTimeSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setActive(final Boolean active) {
        this.active = active;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public Question() {
        this.active = Question.$default$active();
    }

    @java.lang.SuppressWarnings("all")
    
    public Question(final UUID id, final Topic topic, final String prompt, final String optionsJson, final String correctAnswerJson, final String explanation, final DifficultyLevel difficulty, final QuestionType questionType, final String[] tags, final Integer estimatedTimeSec, final Boolean active, final OffsetDateTime createdAt) {
        this.id = id;
        this.topic = topic;
        this.prompt = prompt;
        this.optionsJson = optionsJson;
        this.correctAnswerJson = correctAnswerJson;
        this.explanation = explanation;
        this.difficulty = difficulty;
        this.questionType = questionType;
        this.tags = tags;
        this.estimatedTimeSec = estimatedTimeSec;
        this.active = active;
        this.createdAt = createdAt;
    }
}
