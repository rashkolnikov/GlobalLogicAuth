package com.example.globallogicauth.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Getter
@Setter
public class UserBase {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(name = "uuid", length = 64, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email", length = 320, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @CreatedDate
    @Column(name = "created", nullable = false, updatable = false)
    private Instant created;

    @Column(name = "last_login")
    private Instant lastLogin;

    @Column(name="is_active", nullable = false)
    private boolean isActive;

    public static @NonNull UserBase.UserBaseBuilder.Uuid builder(){ return new UserBase.Builder(); }

    private static class Builder implements
            UserBaseBuilder.Uuid,
            UserBaseBuilder.Email,
            UserBaseBuilder.Password,
            UserBaseBuilder.Created,
            UserBaseBuilder.IsActive,
            UserBaseBuilder.Optionals{

        private final UserBase userBase;

        private Builder(){
            this.userBase = new UserBase();
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.Email uuid(@NonNull String uuid) {
            this.userBase.setUuid(uuid);
            return this;
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.Password email(@NonNull String email) {
            this.userBase.setEmail(email);
            return this;
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.Created password(@NonNull String password) {
            this.userBase.setPassword(password);
            return this;
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.IsActive created(@NonNull Instant created) {
            this.userBase.setCreated(created);
            return this;
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.Optionals isActive(boolean isActive) {
            this.userBase.setActive(isActive);
            return this;
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.Optionals name(String name) {
            this.userBase.setName(name);
            return this;
        }

        @Override
        public @NonNull UserBase.UserBaseBuilder.Optionals lastLogin(Instant lastLogin) {
            this.userBase.setLastLogin(lastLogin);
            return this;
        }

        @Override
        public @NonNull UserBase build() {
            return this.userBase;
        }
    }
    private interface UserBaseBuilder {

        interface Uuid{
            @NonNull Email uuid(@NonNull String uuid);
        }

        interface Email{
            @NonNull Password email(@NonNull String email);
        }

        interface Password{
            @NonNull Created password(@NonNull String password);
        }

        interface Created {
            @NonNull IsActive created(@NonNull Instant created);
        }

        interface IsActive{
            @NonNull Optionals isActive(boolean isActive);
        }

        interface Optionals{
            @NonNull Optionals name(String name);

            @NonNull Optionals lastLogin(Instant lastLogin);

            @NonNull UserBase build();
        }
    }
}
