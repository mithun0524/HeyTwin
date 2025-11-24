package com.heytwin.domain.entity;

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
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "digital_twin_history")
public class DigitalTwinHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;
    @Column(name = "snapshot", columnDefinition = "jsonb", nullable = false)
    private String snapshot;
    @Column(name = "captured_at", nullable = false)
    private OffsetDateTime capturedAt;

    @PrePersist
    void onCreate() {
        capturedAt = OffsetDateTime.now();
    }


    @java.lang.SuppressWarnings("all")
    
    public static class DigitalTwinHistoryBuilder {
        @java.lang.SuppressWarnings("all")
        
        private UUID id;
        @java.lang.SuppressWarnings("all")
        
        private User student;
        @java.lang.SuppressWarnings("all")
        
        private String snapshot;
        @java.lang.SuppressWarnings("all")
        
        private OffsetDateTime capturedAt;

        @java.lang.SuppressWarnings("all")
        
        DigitalTwinHistoryBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinHistory.DigitalTwinHistoryBuilder id(final UUID id) {
            this.id = id;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinHistory.DigitalTwinHistoryBuilder student(final User student) {
            this.student = student;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinHistory.DigitalTwinHistoryBuilder snapshot(final String snapshot) {
            this.snapshot = snapshot;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinHistory.DigitalTwinHistoryBuilder capturedAt(final OffsetDateTime capturedAt) {
            this.capturedAt = capturedAt;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinHistory build() {
            return new DigitalTwinHistory(this.id, this.student, this.snapshot, this.capturedAt);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "DigitalTwinHistory.DigitalTwinHistoryBuilder(id=" + this.id + ", student=" + this.student + ", snapshot=" + this.snapshot + ", capturedAt=" + this.capturedAt + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static DigitalTwinHistory.DigitalTwinHistoryBuilder builder() {
        return new DigitalTwinHistory.DigitalTwinHistoryBuilder();
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
    
    public String getSnapshot() {
        return this.snapshot;
    }

    @java.lang.SuppressWarnings("all")
    
    public OffsetDateTime getCapturedAt() {
        return this.capturedAt;
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
    
    public void setSnapshot(final String snapshot) {
        this.snapshot = snapshot;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setCapturedAt(final OffsetDateTime capturedAt) {
        this.capturedAt = capturedAt;
    }

    @java.lang.SuppressWarnings("all")
    
    public DigitalTwinHistory() {
    }

    @java.lang.SuppressWarnings("all")
    
    public DigitalTwinHistory(final UUID id, final User student, final String snapshot, final OffsetDateTime capturedAt) {
        this.id = id;
        this.student = student;
        this.snapshot = snapshot;
        this.capturedAt = capturedAt;
    }
}
