package com.heytwin.auth.dto;

public final class AuthResponse {
    private final String accessToken;
    private final String refreshToken;
    private final long expiresIn;
    private final UserSummary user;


    public static final class UserSummary {
        private final String id;
        private final String fullName;
        private final String role;

        @java.lang.SuppressWarnings("all")
        
        UserSummary(final String id, final String fullName, final String role) {
            this.id = id;
            this.fullName = fullName;
            this.role = role;
        }


        @java.lang.SuppressWarnings("all")
        
        public static class UserSummaryBuilder {
            @java.lang.SuppressWarnings("all")
            
            private String id;
            @java.lang.SuppressWarnings("all")
            
            private String fullName;
            @java.lang.SuppressWarnings("all")
            
            private String role;

            @java.lang.SuppressWarnings("all")
            
            UserSummaryBuilder() {
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AuthResponse.UserSummary.UserSummaryBuilder id(final String id) {
                this.id = id;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AuthResponse.UserSummary.UserSummaryBuilder fullName(final String fullName) {
                this.fullName = fullName;
                return this;
            }

            /**
             * @return {@code this}.
             */
            @java.lang.SuppressWarnings("all")
            
            public AuthResponse.UserSummary.UserSummaryBuilder role(final String role) {
                this.role = role;
                return this;
            }

            @java.lang.SuppressWarnings("all")
            
            public AuthResponse.UserSummary build() {
                return new AuthResponse.UserSummary(this.id, this.fullName, this.role);
            }

            @java.lang.Override
            @java.lang.SuppressWarnings("all")
            
            public java.lang.String toString() {
                return "AuthResponse.UserSummary.UserSummaryBuilder(id=" + this.id + ", fullName=" + this.fullName + ", role=" + this.role + ")";
            }
        }

        @java.lang.SuppressWarnings("all")
        
        public static AuthResponse.UserSummary.UserSummaryBuilder builder() {
            return new AuthResponse.UserSummary.UserSummaryBuilder();
        }

        @java.lang.SuppressWarnings("all")
        
        public String getId() {
            return this.id;
        }

        @java.lang.SuppressWarnings("all")
        
        public String getFullName() {
            return this.fullName;
        }

        @java.lang.SuppressWarnings("all")
        
        public String getRole() {
            return this.role;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public boolean equals(final java.lang.Object o) {
            if (o == this) return true;
            if (!(o instanceof AuthResponse.UserSummary)) return false;
            final AuthResponse.UserSummary other = (AuthResponse.UserSummary) o;
            final java.lang.Object this$id = this.getId();
            final java.lang.Object other$id = other.getId();
            if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
            final java.lang.Object this$fullName = this.getFullName();
            final java.lang.Object other$fullName = other.getFullName();
            if (this$fullName == null ? other$fullName != null : !this$fullName.equals(other$fullName)) return false;
            final java.lang.Object this$role = this.getRole();
            final java.lang.Object other$role = other.getRole();
            if (this$role == null ? other$role != null : !this$role.equals(other$role)) return false;
            return true;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final java.lang.Object $id = this.getId();
            result = result * PRIME + ($id == null ? 43 : $id.hashCode());
            final java.lang.Object $fullName = this.getFullName();
            result = result * PRIME + ($fullName == null ? 43 : $fullName.hashCode());
            final java.lang.Object $role = this.getRole();
            result = result * PRIME + ($role == null ? 43 : $role.hashCode());
            return result;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "AuthResponse.UserSummary(id=" + this.getId() + ", fullName=" + this.getFullName() + ", role=" + this.getRole() + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    AuthResponse(final String accessToken, final String refreshToken, final long expiresIn, final UserSummary user) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresIn = expiresIn;
        this.user = user;
    }


    @java.lang.SuppressWarnings("all")
    
    public static class AuthResponseBuilder {
        @java.lang.SuppressWarnings("all")
        
        private String accessToken;
        @java.lang.SuppressWarnings("all")
        
        private String refreshToken;
        @java.lang.SuppressWarnings("all")
        
        private long expiresIn;
        @java.lang.SuppressWarnings("all")
        
        private UserSummary user;

        @java.lang.SuppressWarnings("all")
        
        AuthResponseBuilder() {
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AuthResponse.AuthResponseBuilder accessToken(final String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AuthResponse.AuthResponseBuilder refreshToken(final String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AuthResponse.AuthResponseBuilder expiresIn(final long expiresIn) {
            this.expiresIn = expiresIn;
            return this;
        }

        /**
         * @return {@code this}.
         */
        @java.lang.SuppressWarnings("all")
        
        public AuthResponse.AuthResponseBuilder user(final UserSummary user) {
            this.user = user;
            return this;
        }

        @java.lang.SuppressWarnings("all")
        
        public AuthResponse build() {
            return new AuthResponse(this.accessToken, this.refreshToken, this.expiresIn, this.user);
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("all")
        
        public java.lang.String toString() {
            return "AuthResponse.AuthResponseBuilder(accessToken=" + this.accessToken + ", refreshToken=" + this.refreshToken + ", expiresIn=" + this.expiresIn + ", user=" + this.user + ")";
        }
    }

    @java.lang.SuppressWarnings("all")
    
    public static AuthResponse.AuthResponseBuilder builder() {
        return new AuthResponse.AuthResponseBuilder();
    }

    @java.lang.SuppressWarnings("all")
    
    public String getAccessToken() {
        return this.accessToken;
    }

    @java.lang.SuppressWarnings("all")
    
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @java.lang.SuppressWarnings("all")
    
    public long getExpiresIn() {
        return this.expiresIn;
    }

    @java.lang.SuppressWarnings("all")
    
    public UserSummary getUser() {
        return this.user;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof AuthResponse)) return false;
        final AuthResponse other = (AuthResponse) o;
        if (this.getExpiresIn() != other.getExpiresIn()) return false;
        final java.lang.Object this$accessToken = this.getAccessToken();
        final java.lang.Object other$accessToken = other.getAccessToken();
        if (this$accessToken == null ? other$accessToken != null : !this$accessToken.equals(other$accessToken)) return false;
        final java.lang.Object this$refreshToken = this.getRefreshToken();
        final java.lang.Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken)) return false;
        final java.lang.Object this$user = this.getUser();
        final java.lang.Object other$user = other.getUser();
        if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
        return true;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $expiresIn = this.getExpiresIn();
        result = result * PRIME + (int) ($expiresIn >>> 32 ^ $expiresIn);
        final java.lang.Object $accessToken = this.getAccessToken();
        result = result * PRIME + ($accessToken == null ? 43 : $accessToken.hashCode());
        final java.lang.Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        final java.lang.Object $user = this.getUser();
        result = result * PRIME + ($user == null ? 43 : $user.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "AuthResponse(accessToken=" + this.getAccessToken() + ", refreshToken=" + this.getRefreshToken() + ", expiresIn=" + this.getExpiresIn() + ", user=" + this.getUser() + ")";
    }
}
