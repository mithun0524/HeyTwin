package com.heytwin.digitaltwin;

import com.heytwin.domain.entity.StudentTopicProfile;
import org.springframework.stereotype.Component;

@Component
public class RuleBasedTwinCalculator {

    public StudentTopicProfile recalculate(StudentTopicProfile profile, double latestAccuracy,
                                           double avgTimeSec, double difficultyScoreDelta,
                                           double consistencyDelta, double forgettingDelta) {
        double accuracy = mix(profile.getAccuracy(), latestAccuracy, 0.7, 0.3);
        double avgTime = mix(profile.getAverageTimeSec(), avgTimeSec, 0.6, 0.4);
        double difficultyScore = clamp(mix(profile.getDifficultyScore(), difficultyScoreDelta, 0.8, 0.2));
        double consistencyScore = clamp(mix(profile.getConsistencyScore(), consistencyDelta, 0.6, 0.4));
        double forgettingScore = clamp(mix(profile.getForgettingScore(), forgettingDelta, 0.5, 0.5));
        double mastery = computeMastery(accuracy, difficultyScore, avgTime);

        profile.setAccuracy(accuracy);
        profile.setAverageTimeSec(avgTime);
        profile.setDifficultyScore(difficultyScore);
        profile.setConsistencyScore(consistencyScore);
        profile.setForgettingScore(forgettingScore);
        profile.setMasteryLevel(mastery);
        return profile;
    }

    public double computeMastery(double accuracy, double difficultyScore, double avgTime) {
        double timeEfficiency = 1 - Math.min(avgTime / 120d, 1);
        return clamp(accuracy * 0.5 + difficultyScore * 0.3 + timeEfficiency * 0.2);
    }

    private double mix(double oldValue, double latestValue, double oldWeight, double newWeight) {
        return oldValue * oldWeight + latestValue * newWeight;
    }

    private double clamp(double value) {
        return Math.max(0, Math.min(1, value));
    }
}
