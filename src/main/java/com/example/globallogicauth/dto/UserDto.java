package com.example.globallogicauth.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class UserDto {

    private String uuid;

    private String name;

    private String email;

    private Instant created;

    private Instant lastLogin;

    private boolean isActive;

    public static @NonNull UserDtoBuilder.Uuid builder(){ return new UserDto.Builder(); }

    private static class Builder implements
            UserDtoBuilder.Uuid,
            UserDtoBuilder.Email,
            UserDtoBuilder.Created,
            UserDtoBuilder.IsActive,
            UserDtoBuilder.Optionals{

        private final UserDto userDto;

        private Builder(){
            this.userDto = new UserDto();
            this.userDto.setName(null);
            this.userDto.setLastLogin(null);
        }

        @Override
        public @NonNull UserDtoBuilder.Email uuid(@NonNull String uuid) {
            this.userDto.setUuid(uuid);
            return this;
        }

        @Override
        public @NonNull UserDtoBuilder.Created email(@NonNull String email) {
            this.userDto.setEmail(email);
            return this;
        }

        @Override
        public @NonNull UserDtoBuilder.IsActive created(@NonNull Instant created) {
            this.userDto.setCreated(created);
            return this;
        }

        @Override
        public @NonNull UserDtoBuilder.Optionals isActive(boolean isActive) {
            this.userDto.setActive(isActive);
            return this;
        }

        @Override
        public @NonNull UserDtoBuilder.Optionals name(String name) {
            this.userDto.setName(name);
            return this;
        }

        @Override
        public @NonNull UserDtoBuilder.Optionals lastLogin(@NonNull Instant lastLogin) {
            this.userDto.setLastLogin(lastLogin);
            return this;
        }

        @Override
        public @NonNull UserDto build() {
            return this.userDto;
        }
    }
    private interface UserDtoBuilder {

        interface Uuid{
            @NonNull Email uuid(@NonNull String uuid);
        }

        interface Email{
            @NonNull Created email(@NonNull String email);
        }

        interface Created {
            @NonNull IsActive created(@NonNull Instant created);
        }

        interface IsActive{
            @NonNull Optionals isActive(boolean isActive);
        }

        interface Optionals{
            @NonNull Optionals name(String name);

            @NonNull Optionals lastLogin(@NonNull Instant lastLogin);

            @NonNull UserDto build();
        }
    }

}
