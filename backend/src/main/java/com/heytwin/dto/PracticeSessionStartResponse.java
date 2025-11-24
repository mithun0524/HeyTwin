package com.heytwin.dto;

import java.util.List;
import java.util.Map;

public final class PracticeSessionStartResponse {
    private final String sessionId;
    private final List<QuestionDto> questions;
    private final Map<String, Integer> selectionRationale;

    @java.lang.SuppressWarnings("all")
    
    PracticeSessionStartResponse(final String sessionId, final List<QuestionDto> questions, final Map<String, Integer> selectionRationale) {
        this.sessionId = sessionId;
        this.questions = questions;
        this.selectionRationale = selectionRationale;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class PracticeSessionStartResponseBuilder {
        @java.lang.SuppressWarnings("all")
        
        private String sessionId;
        @java.lang.SuppressWarnings("all")
        
        private List<QuestionDto> questions;
        @java.lang.SuppressWarnings("all")
        
        private Map<String, Integer> selectionRationale;

        @java.lang.SuppressWarnings("all")
        
        PracticeSessionStartResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSessionStartResponse.PracticeSessionStartResponseBuilder sessionId(final String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSessionStartResponse.PracticeSessionStartResponseBuilder questions(final List<QuestionDto> questions) {
            this.questions = questions;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public PracticeSessionStartResponse.PracticeSessionStartResponseBuilder selectionRationale(final Map<String, Integer> selectionRationale) {
            this.selectionRationale = selectionRationale;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public PracticeSessionStartResponse build() {
            return new PracticeSessionStartResponse(this.sessionId, this.questions, this.selectionRationale);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "PracticeSessionStartResponse.PracticeSessionStartResponseBuilder(sessionId=" + this.sessionId + ", questions=" + this.questions + ", selectionRationale=" + this.selectionRationale + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static PracticeSessionStartResponse.PracticeSessionStartResponseBuilder builder() {
        return new PracticeSessionStartResponse.PracticeSessionStartResponseBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public String getSessionId() {
        return this.sessionId;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<QuestionDto> getQuestions() {
        return this.questions;
    }

    @java.lang.SuppressWarnings("all")
    
    public Map<String, Integer> getSelectionRationale() {
        return this.selectionRationale;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof PracticeSessionStartResponse)) return false;
        final PracticeSessionStartResponse other = (PracticeSessionStartResponse) o;
        final java.lang.Object this$sessionId = this.getSessionId();
        final java.lang.Object other$sessionId = other.getSessionId();
        if (this$sessionId == null ? other$sessionId != null : !this$sessionId.equals(other$sessionId)) return false;
        final java.lang.Object this$questions = this.getQuestions();
        final java.lang.Object other$questions = other.getQuestions();
        if (this$questions == null ? other$questions != null : !this$questions.equals(other$questions)) return false;
        final java.lang.Object this$selectionRationale = this.getSelectionRationale();
        final java.lang.Object other$selectionRationale = other.getSelectionRationale();
        if (this$selectionRationale == null ? other$selectionRationale != null : !this$selectionRationale.equals(other$selectionRationale)) return false;
        return true;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $sessionId = this.getSessionId();
        result = result * PRIME + ($sessionId == null ? 43 : $sessionId.hashCode());
        final java.lang.Object $questions = this.getQuestions();
        result = result * PRIME + ($questions == null ? 43 : $questions.hashCode());
        final java.lang.Object $selectionRationale = this.getSelectionRationale();
        result = result * PRIME + ($selectionRationale == null ? 43 : $selectionRationale.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "PracticeSessionStartResponse(sessionId=" + this.getSessionId() + ", questions=" + this.getQuestions() + ", selectionRationale=" + this.getSelectionRationale() + ")";
    }
}
