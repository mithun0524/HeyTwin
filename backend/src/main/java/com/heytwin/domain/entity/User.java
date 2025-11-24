package com.heytwin.domain.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import com.heytwin.domain.model.enums.RoleType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "full_name", nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true, columnDefinition = "citext")
    private String email;
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;
    @Column(name = "grade_level")
    private String gradeLevel;
    private String timezone;
    @Column(name = "last_login_at")
    private OffsetDateTime lastLoginAt;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    @PrePersist
    @SuppressWarnings("unused")
    void onCreate() {
        createdAt = OffsetDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    @SuppressWarnings("unused")
    void onUpdate() {
        updatedAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class UserBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private String fullName;
        @java.lang.SuppressWarnings("all")
        
        private String email;
        @java.lang.SuppressWarnings("all")
        
        private String passwordHash;
        @java.lang.SuppressWarnings("all")
        
        private RoleType role;
        @java.lang.SuppressWarnings("all")
        
        private String gradeLevel;
        @java.lang.SuppressWarnings("all")
        
        private String timezone;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime lastLoginAt;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime createdAt;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime updatedAt;

        @java.lang.SuppressWarnings("all")
        
        UserBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder fullName(final String fullName) {
            this.fullName = fullName;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder passwordHash(final String passwordHash) {
            this.passwordHash = passwordHash;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder role(final RoleType role) {
            this.role = role;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder gradeLevel(final String gradeLevel) {
            this.gradeLevel = gradeLevel;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder timezone(final String timezone) {
            this.timezone = timezone;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder lastLoginAt(final OffsetDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder createdAt(final OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public User.UserBuilder updatedAt(final OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public User build() {
            return new User(this.id, this.fullName, this.email, this.passwordHash, this.role, this.gradeLevel, this.timezone, this.lastLoginAt, this.createdAt, this.updatedAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "User.UserBuilder(id=" + this.id + ", fullName=" + this.fullName + ", email=" + this.email + ", passwordHash=" + this.passwordHash + ", role=" + this.role + ", gradeLevel=" + this.gradeLevel + ", timezone=" + this.timezone + ", lastLoginAt=" + this.lastLoginAt + ", createdAt=" + this.createdAt + ", updatedAt=" + this.updatedAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static User.UserBuilder builder() {
        return new User.UserBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public UUID getId() {
        return this.id;
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
    
    public String getPasswordHash() {
        return this.passwordHash;
    }

    @java.lang.SuppressWarnings("all")
    
    public RoleType getRole() {
        return this.role;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getGradeLevel() {
        return this.gradeLevel;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getTimezone() {
        return this.timezone;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getLastLoginAt() {
        return this.lastLoginAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
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
    
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setEmail(final String email) {
        this.email = email;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setPasswordHash(final String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setRole(final RoleType role) {
        this.role = role;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setGradeLevel(final String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setTimezone(final String timezone) {
        this.timezone = timezone;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setLastLoginAt(final OffsetDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCreatedAt(final OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setUpdatedAt(final OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public User() {
    }

    @java.lang.SuppressWarnings("all")
    
    public User(final UUID id, final String fullName, final String email, final String passwordHash, final RoleType role, final String gradeLevel, final String timezone, final OffsetDateTime lastLoginAt, final OffsetDateTime createdAt, final OffsetDateTime updatedAt) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.gradeLevel = gradeLevel;
        this.timezone = timezone;
        this.lastLoginAt = lastLoginAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
