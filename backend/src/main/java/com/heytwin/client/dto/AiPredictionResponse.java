package com.heytwin.client.dto;

import java.util.List;

public final class AiPredictionResponse {
    private final String modelId;
    private final List<Item> predictions;


    public static final class Item {
        private final String questionId;
        private final double prob;

        @java.lang.SuppressWarnings("all")
        
        Item(final String questionId, final double prob) {
            this.questionId = questionId;
            this.prob = prob;
        }


        @java.lang.SuppressWarnings("all")
        
        public static class ItemBuilder {
            @java.lang.SuppressWarnings("all")
            
            private String questionId;
            @java.lang.SuppressWarnings("all")
            
            private double prob;

            @java.lang.SuppressWarnings("all")
            
            ItemBuilder() {
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AiPredictionResponse.Item.ItemBuilder questionId(final String questionId) {
                this.questionId = questionId;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AiPredictionResponse.Item.ItemBuilder prob(final double prob) {
                this.prob = prob;
                return this;
            }

            @java.lang.SuppressWarnings("all")
            
            public AiPredictionResponse.Item build() {
                return new AiPredictionResponse.Item(this.questionId, this.prob);
            }

            @java.lang.Override
            @java.lang.SuppressWarnings("all")
            
            public java.lang.String toString() {
                return "AiPredictionResponse.Item.ItemBuilder(questionId=" + this.questionId + ", prob=" + this.prob + ")";
            }
        }

        @java.lang.SuppressWarnings("all")
        
        public static AiPredictionResponse.Item.ItemBuilder builder() {
            return new AiPredictionResponse.Item.ItemBuilder();
        }

        @java.lang.SuppressWarnings("all")
        
        public String getQuestionId() {
            return this.questionId;
        }

        @java.lang.SuppressWarnings("all")
        
        public double getProb() {
            return this.prob;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof AiPredictionResponse.Item)) return false;
            final AiPredictionResponse.Item other = (AiPredictionResponse.Item) o;
            if (java.lang.Double.compare(this.getProb(), other.getProb()) != 0) return false;
            final java.lang.Object this$questionId = this.getQuestionId();
            final java.lang.Object other$questionId = other.getQuestionId();
            if (this$questionId == null ? other$questionId != null : !this$questionId.equals(other$questionId)) return false;
            return true;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final long $prob = java.lang.Double.doubleToLongBits(this.getProb());
            result = result * PRIME + (int) ($prob >>> 32 ^ $prob);
            final java.lang.Object $questionId = this.getQuestionId();
            result = result * PRIME + ($questionId == null ? 43 : $questionId.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "AiPredictionResponse.Item(questionId=" + this.getQuestionId() + ", prob=" + this.getProb() + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    AiPredictionResponse(final String modelId, final List<Item> predictions) {
        this.modelId = modelId;
        this.predictions = predictions;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class AiPredictionResponseBuilder {
        @java.lang.SuppressWarnings("all")
        
        private String modelId;
        @java.lang.SuppressWarnings("all")
        
        private List<Item> predictions;

        @java.lang.SuppressWarnings("all")
        
        AiPredictionResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AiPredictionResponse.AiPredictionResponseBuilder modelId(final String modelId) {
            this.modelId = modelId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AiPredictionResponse.AiPredictionResponseBuilder predictions(final List<Item> predictions) {
            this.predictions = predictions;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public AiPredictionResponse build() {
            return new AiPredictionResponse(this.modelId, this.predictions);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "AiPredictionResponse.AiPredictionResponseBuilder(modelId=" + this.modelId + ", predictions=" + this.predictions + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static AiPredictionResponse.AiPredictionResponseBuilder builder() {
        return new AiPredictionResponse.AiPredictionResponseBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public String getModelId() {
        return this.modelId;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<Item> getPredictions() {
        return this.predictions;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof AiPredictionResponse)) return false;
        final AiPredictionResponse other = (AiPredictionResponse) o;
        final java.lang.Object this$modelId = this.getModelId();
        final java.lang.Object other$modelId = other.getModelId();
        if (this$modelId == null ? other$modelId != null : !this$modelId.equals(other$modelId)) return false;
        final java.lang.Object this$predictions = this.getPredictions();
        final java.lang.Object other$predictions = other.getPredictions();
        if (this$predictions == null ? other$predictions != null : !this$predictions.equals(other$predictions)) return false;
        return true;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $modelId = this.getModelId();
        result = result * PRIME + ($modelId == null ? 43 : $modelId.hashCode());
        final java.lang.Object $predictions = this.getPredictions();
        result = result * PRIME + ($predictions == null ? 43 : $predictions.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "AiPredictionResponse(modelId=" + this.getModelId() + ", predictions=" + this.getPredictions() + ")";
    }
}
