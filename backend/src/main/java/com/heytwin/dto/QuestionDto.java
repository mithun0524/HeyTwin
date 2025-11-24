package com.heytwin.dto;

import com.heytwin.domain.model.enums.DifficultyLevel;
import com.heytwin.domain.model.enums.QuestionType;
import java.util.List;

public final class QuestionDto {
    private final String questionId;
    private final String topicId;
    private final String topicName;
    private final DifficultyLevel difficulty;
    private final QuestionType questionType;
    private final String prompt;
    private final List<String> options;
    private final Integer estimatedTimeSec;

    @java.lang.SuppressWarnings("all")
    
    QuestionDto(final String questionId, final String topicId, final String topicName, final DifficultyLevel difficulty, final QuestionType questionType, final String prompt, final List<String> options, final Integer estimatedTimeSec) {
        this.questionId = questionId;
        this.topicId = topicId;
        this.topicName = topicName;
        this.difficulty = difficulty;
        this.questionType = questionType;
        this.prompt = prompt;
        this.options = options;
        this.estimatedTimeSec = estimatedTimeSec;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class QuestionDtoBuilder {
        @java.lang.SuppressWarnings("all")
        
        private String questionId;
        @java.lang.SuppressWarnings("all")
        
        private String topicId;
        @java.lang.SuppressWarnings("all")
        
        private String topicName;
        @java.lang.SuppressWarnings("all")
        
        private DifficultyLevel difficulty;
        @java.lang.SuppressWarnings("all")
        
        private QuestionType questionType;
        @java.lang.SuppressWarnings("all")
        
        private String prompt;
        @java.lang.SuppressWarnings("all")
        
        private List<String> options;
        @java.lang.SuppressWarnings("all")
        
        private Integer estimatedTimeSec;

        @java.lang.SuppressWarnings("all")
        
        QuestionDtoBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder questionId(final String questionId) {
            this.questionId = questionId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder topicId(final String topicId) {
            this.topicId = topicId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder topicName(final String topicName) {
            this.topicName = topicName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder difficulty(final DifficultyLevel difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder questionType(final QuestionType questionType) {
            this.questionType = questionType;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder prompt(final String prompt) {
            this.prompt = prompt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder options(final List<String> options) {
            this.options = options;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public QuestionDto.QuestionDtoBuilder estimatedTimeSec(final Integer estimatedTimeSec) {
            this.estimatedTimeSec = estimatedTimeSec;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public QuestionDto build() {
            return new QuestionDto(this.questionId, this.topicId, this.topicName, this.difficulty, this.questionType, this.prompt, this.options, this.estimatedTimeSec);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "QuestionDto.QuestionDtoBuilder(questionId=" + this.questionId + ", topicId=" + this.topicId + ", topicName=" + this.topicName + ", difficulty=" + this.difficulty + ", questionType=" + this.questionType + ", prompt=" + this.prompt + ", options=" + this.options + ", estimatedTimeSec=" + this.estimatedTimeSec + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static QuestionDto.QuestionDtoBuilder builder() {
        return new QuestionDto.QuestionDtoBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public String getQuestionId() {
        return this.questionId;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getTopicId() {
        return this.topicId;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getTopicName() {
        return this.topicName;
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
    
    public String getPrompt() {
        return this.prompt;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<String> getOptions() {
        return this.options;
    }

    @java.lang.SuppressWarnings("all")
    
    public Integer getEstimatedTimeSec() {
        return this.estimatedTimeSec;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof QuestionDto)) return false;
        final QuestionDto other = (QuestionDto) o;
        final java.lang.Object this$estimatedTimeSec = this.getEstimatedTimeSec();
        final java.lang.Object other$estimatedTimeSec = other.getEstimatedTimeSec();
        if (this$estimatedTimeSec == null ? other$estimatedTimeSec != null : !this$estimatedTimeSec.equals(other$estimatedTimeSec)) return false;
        final java.lang.Object this$questionId = this.getQuestionId();
        final java.lang.Object other$questionId = other.getQuestionId();
        if (this$questionId == null ? other$questionId != null : !this$questionId.equals(other$questionId)) return false;
        final java.lang.Object this$topicId = this.getTopicId();
        final java.lang.Object other$topicId = other.getTopicId();
        if (this$topicId == null ? other$topicId != null : !this$topicId.equals(other$topicId)) return false;
        final java.lang.Object this$topicName = this.getTopicName();
        final java.lang.Object other$topicName = other.getTopicName();
        if (this$topicName == null ? other$topicName != null : !this$topicName.equals(other$topicName)) return false;
        final java.lang.Object this$difficulty = this.getDifficulty();
        final java.lang.Object other$difficulty = other.getDifficulty();
        if (this$difficulty == null ? other$difficulty != null : !this$difficulty.equals(other$difficulty)) return false;
        final java.lang.Object this$questionType = this.getQuestionType();
        final java.lang.Object other$questionType = other.getQuestionType();
        if (this$questionType == null ? other$questionType != null : !this$questionType.equals(other$questionType)) return false;
        final java.lang.Object this$prompt = this.getPrompt();
        final java.lang.Object other$prompt = other.getPrompt();
        if (this$prompt == null ? other$prompt != null : !this$prompt.equals(other$prompt)) return false;
        final java.lang.Object this$options = this.getOptions();
        final java.lang.Object other$options = other.getOptions();
        if (this$options == null ? other$options != null : !this$options.equals(other$options)) return false;
        return true;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $estimatedTimeSec = this.getEstimatedTimeSec();
        result = result * PRIME + ($estimatedTimeSec == null ? 43 : $estimatedTimeSec.hashCode());
        final java.lang.Object $questionId = this.getQuestionId();
        result = result * PRIME + ($questionId == null ? 43 : $questionId.hashCode());
        final java.lang.Object $topicId = this.getTopicId();
        result = result * PRIME + ($topicId == null ? 43 : $topicId.hashCode());
        final java.lang.Object $topicName = this.getTopicName();
        result = result * PRIME + ($topicName == null ? 43 : $topicName.hashCode());
        final java.lang.Object $difficulty = this.getDifficulty();
        result = result * PRIME + ($difficulty == null ? 43 : $difficulty.hashCode());
        final java.lang.Object $questionType = this.getQuestionType();
        result = result * PRIME + ($questionType == null ? 43 : $questionType.hashCode());
        final java.lang.Object $prompt = this.getPrompt();
        result = result * PRIME + ($prompt == null ? 43 : $prompt.hashCode());
        final java.lang.Object $options = this.getOptions();
        result = result * PRIME + ($options == null ? 43 : $options.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "QuestionDto(questionId=" + this.getQuestionId() + ", topicId=" + this.getTopicId() + ", topicName=" + this.getTopicName() + ", difficulty=" + this.getDifficulty() + ", questionType=" + this.getQuestionType() + ", prompt=" + this.getPrompt() + ", options=" + this.getOptions() + ", estimatedTimeSec=" + this.getEstimatedTimeSec() + ")";
    }
}
