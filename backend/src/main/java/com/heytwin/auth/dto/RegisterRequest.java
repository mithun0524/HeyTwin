package com.heytwin.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RegisterRequest {
    @NotBlank
    private String fullName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    private String gradeLevel;

    @java.lang.SuppressWarnings("all")
    
    public RegisterRequest() {
    }

    @java.lang.SuppressWarnings("all")
    
    public String getFullName() {
        return this.fullName;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getEmail() {
        return this.email;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getPassword() {
        return this.password;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getGradeLevel() {
        return this.gradeLevel;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setEmail(final String email) {
        this.email = email;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setPassword(final String password) {
        this.password = password;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setGradeLevel(final String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof RegisterRequest)) return false;
        final RegisterRequest other = (RegisterRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$fullName = this.getFullName();
        final java.lang.Object other$fullName = other.getFullName();
        if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) return false;
        final java.lang.Object this$email = this.getEmail();
        final java.lang.Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final java.lang.Object this$password = this.getPassword();
        final java.lang.Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final java.lang.Object this$gradeLevel = this.getGradeLevel();
        final java.lang.Object other$gradeLevel = other.getGradeLevel();
        if (this$gradeLevel == null ? other$gradeLevel != null : !this$gradeLevel.equals(other$gradeLevel)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof RegisterRequest;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $fullName = this.getFullName();
        result = result * PRIME + ($fullName == null ? 43 : $fullName.hashCode());
        final java.lang.Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final java.lang.Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final java.lang.Object $gradeLevel = this.getGradeLevel();
        result = result * PRIME + ($gradeLevel == null ? 43 : $gradeLevel.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "RegisterRequest(fullName=" + this.getFullName() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ", gradeLevel=" + this.getGradeLevel() + ")";
    }
}
