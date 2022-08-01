package com.example.myauthenticationserver.service;

import com.example.myauthenticationserver.config.SecurityConfig;
import com.example.myauthenticationserver.dto.UserDto;
import com.example.myauthenticationserver.entity.UserBase;
import com.example.myauthenticationserver.error.ConflictException;
import com.example.myauthenticationserver.error.NotFoundException;
import com.example.myauthenticationserver.error.UnauthorizedException;
import com.example.myauthenticationserver.facade.ModelMapperService;
import com.example.myauthenticationserver.repository.UserBaseRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserBaseService implements UserDetailsService {

    private final UserBaseRepository userBaseRepository;

    private final ModelMapperService modelMapperService;

    private Optional<UserBase> findByUuid(String uuid) {
        return this.userBaseRepository.findByUuid(uuid);
    }

    private void save(@NonNull final UserBase userBase) {
        this.userBaseRepository.save(userBase);
    }

    public UserBase findByUuidSafeNull(String uuid) {
        Optional<UserBase> userBase = this.userBaseRepository.findByUuid(uuid);

        if (!userBase.isPresent()) {
            throw new NotFoundException("The user doesnt exist.");
        }

        return userBase.get();
    }

    public UserBase findByEmailSafeNull(String email) {
        Optional<UserBase> userBase = this.userBaseRepository.findByEmail(email);

        if (!userBase.isPresent()) {
            throw new NotFoundException("The user doesnt exist.");
        }

        return userBase.get();
    }

    public UserDto findDtoByUuid(String uuid) {
        UserBase userBase = this.findByUuidSafeNull(uuid);

        return this.modelMapperService.map(userBase, UserDto.class);
    }

    public UserDto findDtoByEmail(String email) {
        UserBase userBase = this.findByEmailSafeNull(email);

        return this.modelMapperService.map(userBase, UserDto.class);
    }

    public void isEmailInUse(@NonNull final String email) {
        if (this.existingUserWithEmail(email)) {
            throw new UnauthorizedException("The user already exists.");
        }
    }

    public void insertUser(@NonNull final UserDto dto, final String password) {
        this.save(UserBase.builder()
                .uuid(dto.getUuid())
                .email(dto.getEmail())
                .password(SecurityConfig.passwordEncoder().encode(password))
                .created(dto.getCreated())
                .isActive(dto.isActive())
                .lastLogin(dto.getLastLogin())
                .name(dto.getName())
                .build());
    }

    public void validationAuthSignIn(String email) {
        if (!this.existingUserWithEmail(email)) {
            throw new ConflictException("The user doesnt exist.");
        }
    }

    public boolean existingUserWithEmail(String email){
        return this.userBaseRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final UserBase userBase = this.findByEmailSafeNull(email);
        return buildUserForAuthentication(userBase);
    }

    private @NonNull UserDetails buildUserForAuthentication(@NonNull final UserBase userBase) {
        return new org.springframework.security.core.userdetails.User(
                userBase.getEmail(),
                userBase.getPassword(),
                userBase.isActive(),
                true,
                true,
                true,
                new ArrayList<>()
        );
    }

    public void updateLastLogin(UserDto userDto){
        UserBase userBase = this.findByUuidSafeNull(userDto.getUuid());
        userBase.setLastLogin(Instant.now());
        this.save(userBase);
    }

    public boolean userEnabled(@NonNull final String uuid) {
        if (!this.isUuidRegistered(uuid)) {
            return true;
        }
        return this.findByUuid(uuid).map(UserBase::isActive).orElse(false);
    }

    public boolean isUuidRegistered(@NonNull final String uuid) {
        Optional<UserBase> userBase = this.findByUuid(uuid);
        return userBase.isPresent();
    }
}
