package com.heytwin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import java.util.UUID;

public class PracticeResponseRequest {
    @NotNull
    private UUID questionId;
    @NotBlank
    private String selectedOption;
    private boolean skipped;
    @NotNull
    private Integer timeTakenSec;
    private Map<String, Object> metadata;

    @java.lang.SuppressWarnings("all")
    
    public PracticeResponseRequest() {
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
    
    public boolean isSkipped() {
        return this.skipped;
    }

    @java.lang.SuppressWarnings("all")
    
    public Integer getTimeTakenSec() {
        return this.timeTakenSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public Map<String, Object> getMetadata() {
        return this.metadata;
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
    
    public void setSkipped(final boolean skipped) {
        this.skipped = skipped;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTimeTakenSec(final Integer timeTakenSec) {
        this.timeTakenSec = timeTakenSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setMetadata(final Map<String, Object> metadata) {
        this.metadata = metadata;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof PracticeResponseRequest)) return false;
        final PracticeResponseRequest other = (PracticeResponseRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        if (this.isSkipped() != other.isSkipped()) return false;
        final java.lang.Object this$timeTakenSec = this.getTimeTakenSec();
        final java.lang.Object other$timeTakenSec = other.getTimeTakenSec();
        if (this$timeTakenSec == null ? other$timeTakenSec != null : !this$timeTakenSec.equals(other$timeTakenSec)) return false;
        final java.lang.Object this$questionId = this.getQuestionId();
        final java.lang.Object other$questionId = other.getQuestionId();
        if (this$questionId == null ? other$questionId != null : !this$questionId.equals(other$questionId)) return false;
        final java.lang.Object this$selectedOption = this.getSelectedOption();
        final java.lang.Object other$selectedOption = other.getSelectedOption();
        if (this$selectedOption == null ? other$selectedOption != null : !this$selectedOption.equals(other$selectedOption)) return false;
        final java.lang.Object this$metadata = this.getMetadata();
        final java.lang.Object other$metadata = other.getMetadata();
        if (this$metadata == null ? other$metadata != null : !this$metadata.equals(other$metadata)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof PracticeResponseRequest;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + (this.isSkipped() ? 79 : 97);
        final java.lang.Object $timeTakenSec = this.getTimeTakenSec();
        result = result * PRIME + ($timeTakenSec == null ? 43 : $timeTakenSec.hashCode());
        final java.lang.Object $questionId = this.getQuestionId();
        result = result * PRIME + ($questionId == null ? 43 : $questionId.hashCode());
        final java.lang.Object $selectedOption = this.getSelectedOption();
        result = result * PRIME + ($selectedOption == null ? 43 : $selectedOption.hashCode());
        final java.lang.Object $metadata = this.getMetadata();
        result = result * PRIME + ($metadata == null ? 43 : $metadata.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "PracticeResponseRequest(questionId=" + this.getQuestionId() + ", selectedOption=" + this.getSelectedOption() + ", skipped=" + this.isSkipped() + ", timeTakenSec=" + this.getTimeTakenSec() + ", metadata=" + this.getMetadata() + ")";
    }
}
