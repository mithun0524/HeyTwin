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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "digital_twin_history")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
