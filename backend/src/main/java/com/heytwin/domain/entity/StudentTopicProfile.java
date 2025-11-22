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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student_topic_profile")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentTopicProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @Column(nullable = false)
    private Double accuracy;

    @Column(name = "avg_time_sec", nullable = false)
    private Double averageTimeSec;

    @Column(name = "difficulty_score", nullable = false)
    private Double difficultyScore;

    @Column(name = "consistency_score", nullable = false)
    private Double consistencyScore;

    @Column(name = "forgetting_score", nullable = false)
    private Double forgettingScore;

    @Column(name = "mastery_level", nullable = false)
    private Double masteryLevel;

    @Column(name = "recent_window", columnDefinition = "jsonb")
    private String recentWindow;

    @Column(name = "last_updated", nullable = false)
    private OffsetDateTime lastUpdated;

    @PrePersist
    void onCreate() {
        lastUpdated = OffsetDateTime.now();
    }

    @PreUpdate
    void onUpdate() {
        lastUpdated = OffsetDateTime.now();
    }
}
