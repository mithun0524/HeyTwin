package com.heytwin.diagnostic.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

public class DiagnosticResponseRequest {
    @NotBlank
    private String sessionId;
    @Valid
    @NotNull
    private List<Item> responses;


    public static class Item {
        @NotNull
        private UUID questionId;
        @NotBlank
        private String selectedOption;
        @NotNull
        private Integer timeTakenSec;

        @java.lang.SuppressWarnings("all")
        
        public Item() {
        }

        @java.lang.SuppressWarnings("all")
        
        public UUID getQuestionId() {
            return this.questionId;
        }

        @java.lang.SuppressWarnings("all")
        
        public String getSelectedOption() {
            return this.selectedOption;
        }

        @java.lang.SuppressWarnings("all")
        
        public Integer getTimeTakenSec() {
            return this.timeTakenSec;
        }

        @java.lang.SuppressWarnings("all")
        
        public void setQuestionId(final UUID questionId) {
            this.questionId = questionId;
        }

        @java.lang.SuppressWarnings("all")
        
        public void setSelectedOption(final String selectedOption) {
            this.selectedOption = selectedOption;
        }

        @java.lang.SuppressWarnings("all")
        
        public void setTimeTakenSec(final Integer timeTakenSec) {
            this.timeTakenSec = timeTakenSec;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof DiagnosticResponseRequest.Item)) return false;
            final DiagnosticResponseRequest.Item other = (DiagnosticResponseRequest.Item) o;
            if (!other.canEqual((java.lang.Object) this)) return false;
            final java.lang.Object this$timeTakenSec = this.getTimeTakenSec();
            final java.lang.Object other$timeTakenSec = other.getTimeTakenSec();
            if (this$timeTakenSec == null ? other$timeTakenSec != null : !this$timeTakenSec.equals(other$timeTakenSec)) return false;
            final java.lang.Object this$questionId = this.getQuestionId();
            final java.lang.Object other$questionId = other.getQuestionId();
            if (this$questionId == null ? other$questionId != null : !this$questionId.equals(other$questionId)) return false;
            final java.lang.Object this$selectedOption = this.getSelectedOption();
            final java.lang.Object other$selectedOption = other.getSelectedOption();
            if (this$selectedOption == null ? other$selectedOption != null : !this$selectedOption.equals(other$selectedOption)) return false;
            return true;
        }

        @java.lang.SuppressWarnings("all")
        
        protected boolean canEqual(final java.lang.Object other) {
            return other instanceof DiagnosticResponseRequest.Item;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $timeTakenSec = this.getTimeTakenSec();
            result = result * PRIME + ($timeTakenSec == null ? 43 : $timeTakenSec.hashCode());
            final java.lang.Object $questionId = this.getQuestionId();
            result = result * PRIME + ($questionId == null ? 43 : $questionId.hashCode());
            final java.lang.Object $selectedOption = this.getSelectedOption();
            result = result * PRIME + ($selectedOption == null ? 43 : $selectedOption.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "DiagnosticResponseRequest.Item(questionId=" + this.getQuestionId() + ", selectedOption=" + this.getSelectedOption() + ", timeTakenSec=" + this.getTimeTakenSec() + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public DiagnosticResponseRequest() {
    }

    @java.lang.SuppressWarnings("all")
    
    public String getSessionId() {
        return this.sessionId;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<Item> getResponses() {
        return this.responses;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setSessionId(final String sessionId) {
        this.sessionId = sessionId;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setResponses(final List<Item> responses) {
        this.responses = responses;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof DiagnosticResponseRequest)) return false;
        final DiagnosticResponseRequest other = (DiagnosticResponseRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$sessionId = this.getSessionId();
        final java.lang.Object other$sessionId = other.getSessionId();
        if (this$sessionId == null ? other$sessionId != null : !this$sessionId.equals(other$sessionId)) return false;
        final java.lang.Object this$responses = this.getResponses();
        final java.lang.Object other$responses = other.getResponses();
        if (this$responses == null ? other$responses != null : !this$responses.equals(other$responses)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof DiagnosticResponseRequest;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $sessionId = this.getSessionId();
        result = result * PRIME + ($sessionId == null ? 43 : $sessionId.hashCode());
        final java.lang.Object $responses = this.getResponses();
        result = result * PRIME + ($responses == null ? 43 : $responses.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "DiagnosticResponseRequest(sessionId=" + this.getSessionId() + ", responses=" + this.getResponses() + ")";
    }
}
