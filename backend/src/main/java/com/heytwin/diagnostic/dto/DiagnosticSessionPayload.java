package com.heytwin.diagnostic.dto;

import com.heytwin.dto.QuestionDto;
import java.util.List;

public final class DiagnosticSessionPayload {
    private final String sessionId;
    private final List<QuestionDto> questions;

    @java.lang.SuppressWarnings("all")
    
    DiagnosticSessionPayload(final String sessionId, final List<QuestionDto> questions) {
        this.sessionId = sessionId;
        this.questions = questions;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class DiagnosticSessionPayloadBuilder {
        @java.lang.SuppressWarnings("all")
        
        private String sessionId;
        @java.lang.SuppressWarnings("all")
        
        private List<QuestionDto> questions;

        @java.lang.SuppressWarnings("all")
        
        DiagnosticSessionPayloadBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DiagnosticSessionPayload.DiagnosticSessionPayloadBuilder sessionId(final String sessionId) {
            this.sessionId = sessionId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DiagnosticSessionPayload.DiagnosticSessionPayloadBuilder questions(final List<QuestionDto> questions) {
            this.questions = questions;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public DiagnosticSessionPayload build() {
            return new DiagnosticSessionPayload(this.sessionId, this.questions);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "DiagnosticSessionPayload.DiagnosticSessionPayloadBuilder(sessionId=" + this.sessionId + ", questions=" + this.questions + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static DiagnosticSessionPayload.DiagnosticSessionPayloadBuilder builder() {
        return new DiagnosticSessionPayload.DiagnosticSessionPayloadBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public String getSessionId() {
        return this.sessionId;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<QuestionDto> getQuestions() {
        return this.questions;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof DiagnosticSessionPayload)) return false;
        final DiagnosticSessionPayload other = (DiagnosticSessionPayload) o;
        final java.lang.Object this$sessionId = this.getSessionId();
        final java.lang.Object other$sessionId = other.getSessionId();
        if (this$sessionId == null ? other$sessionId != null : !this$sessionId.equals(other$sessionId)) return false;
        final java.lang.Object this$questions = this.getQuestions();
        final java.lang.Object other$questions = other.getQuestions();
        if (this$questions == null ? other$questions != null : !this$questions.equals(other$questions)) return false;
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
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "DiagnosticSessionPayload(sessionId=" + this.getSessionId() + ", questions=" + this.getQuestions() + ")";
    }
}
