package com.heytwin.digitaltwin.model;

import java.util.List;

public final class DigitalTwinSummary {
    private final double accuracy;
    private final double averageTimeSec;
    private final double consistency;
    private final double difficultyScore;
    private final double forgettingScore;
    private final List<TopicSnapshot> topics;


    public static final class TopicSnapshot {
        private final String topicId;
        private final String name;
        private final double mastery;
        private final double trend;
        private final String recommendation;

        @java.lang.SuppressWarnings("all")
        
        TopicSnapshot(final String topicId, final String name, final double mastery, final double trend, final String recommendation) {
            this.topicId = topicId;
            this.name = name;
            this.mastery = mastery;
            this.trend = trend;
            this.recommendation = recommendation;
        }


        @java.lang.SuppressWarnings("all")
        
        public static class TopicSnapshotBuilder {
            @java.lang.SuppressWarnings("all")
            
            private String topicId;
            @java.lang.SuppressWarnings("all")
            
            private String name;
            @java.lang.SuppressWarnings("all")
            
            private double mastery;
            @java.lang.SuppressWarnings("all")
            
            private double trend;
            @java.lang.SuppressWarnings("all")
            
            private String recommendation;

            @java.lang.SuppressWarnings("all")
            
            TopicSnapshotBuilder() {
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder topicId(final String topicId) {
                this.topicId = topicId;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder name(final String name) {
                this.name = name;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder mastery(final double mastery) {
                this.mastery = mastery;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder trend(final double trend) {
                this.trend = trend;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder recommendation(final String recommendation) {
                this.recommendation = recommendation;
                return this;
            }

            @java.lang.SuppressWarnings("all")
            
            public DigitalTwinSummary.TopicSnapshot build() {
                return new DigitalTwinSummary.TopicSnapshot(this.topicId, this.name, this.mastery, this.trend, this.recommendation);
            }

            @java.lang.Override
            @java.lang.SuppressWarnings("all")
            
            public java.lang.String toString() {
                return "DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder(topicId=" + this.topicId + ", name=" + this.name + ", mastery=" + this.mastery + ", trend=" + this.trend + ", recommendation=" + this.recommendation + ")";
            }
        }

        @java.lang.SuppressWarnings("all")
        
        public static DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder builder() {
            return new DigitalTwinSummary.TopicSnapshot.TopicSnapshotBuilder();
        }

        @java.lang.SuppressWarnings("all")
        
        public String getTopicId() {
            return this.topicId;
        }

        @java.lang.SuppressWarnings("all")
        
        public String getName() {
            return this.name;
        }

        @java.lang.SuppressWarnings("all")
        
        public double getMastery() {
            return this.mastery;
        }

        @java.lang.SuppressWarnings("all")
        
        public double getTrend() {
            return this.trend;
        }

        @java.lang.SuppressWarnings("all")
        
        public String getRecommendation() {
            return this.recommendation;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof DigitalTwinSummary.TopicSnapshot)) return false;
            final DigitalTwinSummary.TopicSnapshot other = (DigitalTwinSummary.TopicSnapshot) o;
            if (java.lang.Double.compare(this.getMastery(), other.getMastery()) != 0) return false;
            if (java.lang.Double.compare(this.getTrend(), other.getTrend()) != 0) return false;
            final java.lang.Object this$topicId = this.getTopicId();
            final java.lang.Object other$topicId = other.getTopicId();
            if (this$topicId == null ? other$topicId != null : !this$topicId.equals(other$topicId)) return false;
            final java.lang.Object this$name = this.getName();
            final java.lang.Object other$name = other.getName();
            if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
            final java.lang.Object this$recommendation = this.getRecommendation();
            final java.lang.Object other$recommendation = other.getRecommendation();
            if (this$recommendation == null ? other$recommendation != null : !this$recommendation.equals(other$recommendation)) return false;
            return true;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final long $mastery = java.lang.Double.doubleToLongBits(this.getMastery());
            result = result * PRIME + (int) ($mastery >>> 32 ^ $mastery);
            final long $trend = java.lang.Double.doubleToLongBits(this.getTrend());
            result = result * PRIME + (int) ($trend >>> 32 ^ $trend);
            final java.lang.Object $topicId = this.getTopicId();
            result = result * PRIME + ($topicId == null ? 43 : $topicId.hashCode());
            final java.lang.Object $name = this.getName();
            result = result * PRIME + ($name == null ? 43 : $name.hashCode());
            final java.lang.Object $recommendation = this.getRecommendation();
            result = result * PRIME + ($recommendation == null ? 43 : $recommendation.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "DigitalTwinSummary.TopicSnapshot(topicId=" + this.getTopicId() + ", name=" + this.getName() + ", mastery=" + this.getMastery() + ", trend=" + this.getTrend() + ", recommendation=" + this.getRecommendation() + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    DigitalTwinSummary(final double accuracy, final double averageTimeSec, final double consistency, final double difficultyScore, final double forgettingScore, final List<TopicSnapshot> topics) {
        this.accuracy = accuracy;
        this.averageTimeSec = averageTimeSec;
        this.consistency = consistency;
        this.difficultyScore = difficultyScore;
        this.forgettingScore = forgettingScore;
        this.topics = topics;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class DigitalTwinSummaryBuilder {
        @java.lang.SuppressWarnings("all")
        
        private double accuracy;
        @java.lang.SuppressWarnings("all")
        
        private double averageTimeSec;
        @java.lang.SuppressWarnings("all")
        
        private double consistency;
        @java.lang.SuppressWarnings("all")
        
        private double difficultyScore;
        @java.lang.SuppressWarnings("all")
        
        private double forgettingScore;
        @java.lang.SuppressWarnings("all")
        
        private List<TopicSnapshot> topics;

        @java.lang.SuppressWarnings("all")
        
        DigitalTwinSummaryBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary.DigitalTwinSummaryBuilder accuracy(final double accuracy) {
            this.accuracy = accuracy;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary.DigitalTwinSummaryBuilder averageTimeSec(final double averageTimeSec) {
            this.averageTimeSec = averageTimeSec;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary.DigitalTwinSummaryBuilder consistency(final double consistency) {
            this.consistency = consistency;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary.DigitalTwinSummaryBuilder difficultyScore(final double difficultyScore) {
            this.difficultyScore = difficultyScore;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary.DigitalTwinSummaryBuilder forgettingScore(final double forgettingScore) {
            this.forgettingScore = forgettingScore;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary.DigitalTwinSummaryBuilder topics(final List<TopicSnapshot> topics) {
            this.topics = topics;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public DigitalTwinSummary build() {
            return new DigitalTwinSummary(this.accuracy, this.averageTimeSec, this.consistency, this.difficultyScore, this.forgettingScore, this.topics);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "DigitalTwinSummary.DigitalTwinSummaryBuilder(accuracy=" + this.accuracy + ", averageTimeSec=" + this.averageTimeSec + ", consistency=" + this.consistency + ", difficultyScore=" + this.difficultyScore + ", forgettingScore=" + this.forgettingScore + ", topics=" + this.topics + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static DigitalTwinSummary.DigitalTwinSummaryBuilder builder() {
        return new DigitalTwinSummary.DigitalTwinSummaryBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public double getAccuracy() {
        return this.accuracy;
    }

    @java.lang.SuppressWarnings("all")
    
    public double getAverageTimeSec() {
        return this.averageTimeSec;
    }

    @java.lang.SuppressWarnings("all")
    
    public double getConsistency() {
        return this.consistency;
    }

    @java.lang.SuppressWarnings("all")
    
    public double getDifficultyScore() {
        return this.difficultyScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public double getForgettingScore() {
        return this.forgettingScore;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<TopicSnapshot> getTopics() {
        return this.topics;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof DigitalTwinSummary)) return false;
        final DigitalTwinSummary other = (DigitalTwinSummary) o;
        if (java.lang.Double.compare(this.getAccuracy(), other.getAccuracy()) != 0) return false;
        if (java.lang.Double.compare(this.getAverageTimeSec(), other.getAverageTimeSec()) != 0) return false;
        if (java.lang.Double.compare(this.getConsistency(), other.getConsistency()) != 0) return false;
        if (java.lang.Double.compare(this.getDifficultyScore(), other.getDifficultyScore()) != 0) return false;
        if (java.lang.Double.compare(this.getForgettingScore(), other.getForgettingScore()) != 0) return false;
        final java.lang.Object this$topics = this.getTopics();
        final java.lang.Object other$topics = other.getTopics();
        if (this$topics == null ? other$topics != null : !this$topics.equals(other$topics)) return false;
        return true;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $accuracy = java.lang.Double.doubleToLongBits(this.getAccuracy());
        result = result * PRIME + (int) ($accuracy >>> 32 ^ $accuracy);
        final long $averageTimeSec = java.lang.Double.doubleToLongBits(this.getAverageTimeSec());
        result = result * PRIME + (int) ($averageTimeSec >>> 32 ^ $averageTimeSec);
        final long $consistency = java.lang.Double.doubleToLongBits(this.getConsistency());
        result = result * PRIME + (int) ($consistency >>> 32 ^ $consistency);
        final long $difficultyScore = java.lang.Double.doubleToLongBits(this.getDifficultyScore());
        result = result * PRIME + (int) ($difficultyScore >>> 32 ^ $difficultyScore);
        final long $forgettingScore = java.lang.Double.doubleToLongBits(this.getForgettingScore());
        result = result * PRIME + (int) ($forgettingScore >>> 32 ^ $forgettingScore);
        final java.lang.Object $topics = this.getTopics();
        result = result * PRIME + ($topics == null ? 43 : $topics.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "DigitalTwinSummary(accuracy=" + this.getAccuracy() + ", averageTimeSec=" + this.getAverageTimeSec() + ", consistency=" + this.getConsistency() + ", difficultyScore=" + this.getDifficultyScore() + ", forgettingScore=" + this.getForgettingScore() + ", topics=" + this.getTopics() + ")";
    }
}
