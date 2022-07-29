package com.example.globallogicauth.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SignUpResponse {

    private String uuid;

    private String created;

    private String lastLogin;

    private String token;

    private boolean isActive;

    public static @NonNull SignUpResponseBuilder.Uuid builder(){return new SignUpResponse.Builder();}

    private static class Builder implements
            SignUpResponseBuilder.Uuid,
            SignUpResponseBuilder.Created,
            SignUpResponseBuilder.LastLogin,
            SignUpResponseBuilder.Token,
            SignUpResponseBuilder.IsActive,
            SignUpResponseBuilder.Build{

        private final SignUpResponse signUpResponse;

        private Builder(){
            this.signUpResponse = new SignUpResponse();
        }

        @Override
        public SignUpResponseBuilder.@NonNull Created uuid(@NonNull String uuid) {
            this.signUpResponse.setUuid(uuid);
            return this;
        }

        @Override
        public SignUpResponseBuilder.@NonNull LastLogin created(@NonNull String created) {
            this.signUpResponse.setCreated(created);
            return this;
        }

        @Override
        public SignUpResponseBuilder.@NonNull Token lastLogin(@NonNull String lastLogin) {
            this.signUpResponse.setLastLogin(lastLogin);
            return this;
        }

        @Override
        public SignUpResponseBuilder.@NonNull IsActive token(@NonNull String token) {
            this.signUpResponse.setToken(token);
            return this;
        }

        @Override
        public SignUpResponseBuilder.@NonNull Build isActive(boolean isActive) {
            this.signUpResponse.setActive(isActive);
            return this;
        }

        @Override
        public @NonNull SignUpResponse build() {
            return this.signUpResponse;
        }
    }
    private interface SignUpResponseBuilder{
        interface Uuid{
            @NonNull Created uuid(@NonNull String uuid);
        }

        interface Created{
            @NonNull LastLogin created(@NonNull String created);
        }

        interface LastLogin{
            @NonNull Token lastLogin(@NonNull String lastLogin);
        }

        interface Token{
            @NonNull IsActive token(@NonNull String token);
        }

        interface IsActive{
            @NonNull Build isActive(boolean isActive);

        }

        interface Build{
            @NonNull SignUpResponse build();
        }
    }
}
