package com.example.globallogicauth.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginResponse {

    private String uuid;

    private String created;

    private String lastLogin;

    private String token;

    private boolean isActive;

    private String name;

    private String email;

    private List<PhoneResponse> phones;

    public static @NonNull LoginResponseBuilder.Uuid builder(){return new LoginResponse.Builder();}

    private static class Builder implements
            LoginResponseBuilder.Uuid,
            LoginResponseBuilder.Created,
            LoginResponseBuilder.LastLogin,
            LoginResponseBuilder.Token,
            LoginResponseBuilder.IsActive,
            LoginResponseBuilder.Name,
            LoginResponseBuilder.Email,
            LoginResponseBuilder.Phones,
            LoginResponseBuilder.Build{

        private final LoginResponse loginResponse;

        private Builder(){
            this.loginResponse = new LoginResponse();
        }

        @Override
        public @NonNull LoginResponseBuilder.Created uuid(@NonNull String uuid) {
            this.loginResponse.setUuid(uuid);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.LastLogin created(@NonNull String created) {
            this.loginResponse.setCreated(created);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.Token lastLogin(@NonNull String lastLogin) {
            this.loginResponse.setLastLogin(lastLogin);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.IsActive token(@NonNull String token) {
            this.loginResponse.setToken(token);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.Name isActive(boolean isActive) {
            this.loginResponse.setActive(isActive);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.Email name(@NonNull String name) {
            this.loginResponse.setName(name);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.Phones email(@NonNull String email) {
            this.loginResponse.setEmail(email);
            return this;
        }

        @Override
        public @NonNull LoginResponseBuilder.Build phones(@NonNull List<PhoneResponse> phoneResponse) {
            this.loginResponse.setPhones(phoneResponse);
            return this;
        }

        @Override
        public @NonNull LoginResponse build() {
            return this.loginResponse;
        }
    }

    private interface LoginResponseBuilder {
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
            @NonNull Name isActive(boolean isActive);
        }
        interface Name{
            @NonNull Email name(@NonNull String name);
        }
        interface Email{
            @NonNull Phones email(@NonNull String email);
        }

        interface Phones{
            @NonNull Build phones(@NonNull List<PhoneResponse> phoneResponse);
        }

        interface Build{
            @NonNull LoginResponse build();
        }
    }
}
