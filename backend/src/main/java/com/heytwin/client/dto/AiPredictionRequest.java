package com.heytwin.client.dto;

import java.util.List;
import java.util.Map;

public final class AiPredictionRequest {
    private final String modelId;
    private final List<Instance> instances;


    public static final class Instance {
        private final String studentId;
        private final String questionId;
        private final Map<String, Object> features;

        @java.lang.SuppressWarnings("all")
        
        Instance(final String studentId, final String questionId, final Map<String, Object> features) {
            this.studentId = studentId;
            this.questionId = questionId;
            this.features = features;
        }


        @java.lang.SuppressWarnings("all")
        
        public static class InstanceBuilder {
            @java.lang.SuppressWarnings("all")
            
            private String studentId;
            @java.lang.SuppressWarnings("all")
            
            private String questionId;
            @java.lang.SuppressWarnings("all")
            
            private Map<String, Object> features;

            @java.lang.SuppressWarnings("all")
            
            InstanceBuilder() {
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AiPredictionRequest.Instance.InstanceBuilder studentId(final String studentId) {
                this.studentId = studentId;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AiPredictionRequest.Instance.InstanceBuilder questionId(final String questionId) {
                this.questionId = questionId;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AiPredictionRequest.Instance.InstanceBuilder features(final Map<String, Object> features) {
                this.features = features;
                return this;
            }

            @java.lang.SuppressWarnings("all")
            
            public AiPredictionRequest.Instance build() {
                return new AiPredictionRequest.Instance(this.studentId, this.questionId, this.features);
            }

            @java.lang.Override
            @java.lang.SuppressWarnings("all")
            
            public java.lang.String toString() {
                return "AiPredictionRequest.Instance.InstanceBuilder(studentId=" + this.studentId + ", questionId=" + this.questionId + ", features=" + this.features + ")";
            }
        }

        @java.lang.SuppressWarnings("all")
        
        public static AiPredictionRequest.Instance.InstanceBuilder builder() {
            return new AiPredictionRequest.Instance.InstanceBuilder();
        }

        @java.lang.SuppressWarnings("all")
        
        public String getStudentId() {
            return this.studentId;
        }

        @java.lang.SuppressWarnings("all")
        
        public String getQuestionId() {
            return this.questionId;
        }

        @java.lang.SuppressWarnings("all")
        
        public Map<String, Object> getFeatures() {
            return this.features;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof AiPredictionRequest.Instance)) return false;
            final AiPredictionRequest.Instance other = (AiPredictionRequest.Instance) o;
            final java.lang.Object this$studentId = this.getStudentId();
            final java.lang.Object other$studentId = other.getStudentId();
            if (this$studentId == null ? other$studentId != null : !this$studentId.equals(other$studentId)) return false;
            final java.lang.Object this$questionId = this.getQuestionId();
            final java.lang.Object other$questionId = other.getQuestionId();
            if (this$questionId == null ? other$questionId != null : !this$questionId.equals(other$questionId)) return false;
            final java.lang.Object this$features = this.getFeatures();
            final java.lang.Object other$features = other.getFeatures();
            if (this$features == null ? other$features != null : !this$features.equals(other$features)) return false;
            return true;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $studentId = this.getStudentId();
            result = result * PRIME + ($studentId == null ? 43 : $studentId.hashCode());
            final java.lang.Object $questionId = this.getQuestionId();
            result = result * PRIME + ($questionId == null ? 43 : $questionId.hashCode());
            final java.lang.Object $features = this.getFeatures();
            result = result * PRIME + ($features == null ? 43 : $features.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "AiPredictionRequest.Instance(studentId=" + this.getStudentId() + ", questionId=" + this.getQuestionId() + ", features=" + this.getFeatures() + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    AiPredictionRequest(final String modelId, final List<Instance> instances) {
        this.modelId = modelId;
        this.instances = instances;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class AiPredictionRequestBuilder {
        @java.lang.SuppressWarnings("all")
        
        private String modelId;
        @java.lang.SuppressWarnings("all")
        
        private List<Instance> instances;

        @java.lang.SuppressWarnings("all")
        
        AiPredictionRequestBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AiPredictionRequest.AiPredictionRequestBuilder modelId(final String modelId) {
            this.modelId = modelId;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AiPredictionRequest.AiPredictionRequestBuilder instances(final List<Instance> instances) {
            this.instances = instances;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public AiPredictionRequest build() {
            return new AiPredictionRequest(this.modelId, this.instances);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "AiPredictionRequest.AiPredictionRequestBuilder(modelId=" + this.modelId + ", instances=" + this.instances + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static AiPredictionRequest.AiPredictionRequestBuilder builder() {
        return new AiPredictionRequest.AiPredictionRequestBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public String getModelId() {
        return this.modelId;
    }

    @java.lang.SuppressWarnings("all")
    
    public List<Instance> getInstances() {
        return this.instances;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof AiPredictionRequest)) return false;
        final AiPredictionRequest other = (AiPredictionRequest) o;
        final java.lang.Object this$modelId = this.getModelId();
        final java.lang.Object other$modelId = other.getModelId();
        if (this$modelId == null ? other$modelId != null : !this$modelId.equals(other$modelId)) return false;
        final java.lang.Object this$instances = this.getInstances();
        final java.lang.Object other$instances = other.getInstances();
        if (this$instances == null ? other$instances != null : !this$instances.equals(other$instances)) return false;
        return true;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $modelId = this.getModelId();
        result = result * PRIME + ($modelId == null ? 43 : $modelId.hashCode());
        final java.lang.Object $instances = this.getInstances();
        result = result * PRIME + ($instances == null ? 43 : $instances.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "AiPredictionRequest(modelId=" + this.getModelId() + ", instances=" + this.getInstances() + ")";
    }
}
