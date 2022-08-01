package com.example.globallogicauth.controller.auth;

import com.example.globallogicauth.constant.Routes;
import com.example.globallogicauth.controller.auth.payload.*;
import com.example.globallogicauth.dto.PhoneDto;
import com.example.globallogicauth.dto.UserDto;
import com.example.globallogicauth.facade.DateTimeFormat;
import com.example.globallogicauth.jwt.JwtService;
import com.example.globallogicauth.service.PhoneService;
import com.example.globallogicauth.service.UserBaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@PreAuthorize("authenticated")
@RequestMapping(Routes.AUTH_BASE)
@RestController
@Validated
public class AuthController {

    private final UserBaseService userBaseService;

    private final PhoneService phoneService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @PostMapping(Routes.AUTH_SIGN_UP)
    public @NonNull ResponseEntity<?> auth_sign_up(@Valid @RequestBody SignUpRequest signUpRequest){

        this.userBaseService.isEmailInUse(signUpRequest.getEmail());

        UserDto userDto = UserDto.builder()
                .uuid(UUID.randomUUID().toString())
                .email(signUpRequest.getEmail())
                .created(Instant.now())
                .isActive(true)
                .name(signUpRequest.getName() != null ? signUpRequest.getName() : null)
                .lastLogin(Instant.now())
                .build();

        this.userBaseService.insertUser(userDto, signUpRequest.getPassword());

        // Java 8 functionality
        if(signUpRequest.getPhones() != null){
            signUpRequest.getPhones().forEach(phone -> {
                PhoneDto phoneDto = PhoneDto.builder()
                        .uuid(userDto.getUuid())
                        .number(phone.getNumber())
                        .cityCode(phone.getCityCode())
                        .countryCode(phone.getCountryCode())
                        .build();

                this.phoneService.save(phoneDto);
            } );
        }

        String jwt = this.jwtService.createJwtWithEmailAndPassword(userDto.getEmail(), signUpRequest.getPassword());

        return ResponseEntity.ok(SignUpResponse.builder()
                .uuid(userDto.getUuid())
                .created(DateTimeFormat.format(userDto.getCreated()))
                .lastLogin(DateTimeFormat.format(userDto.getLastLogin()))
                .token(jwt)
                .isActive(userDto.isActive())
                .build()
        );
    }

    @PostMapping(Routes.AUTH_LOGIN)
    public @NonNull ResponseEntity<?> auth_login(
            @Valid @RequestBody LoginRequest loginRequest) {

        String email = this.jwtService.getEmail(loginRequest.getJwt());

        UserDto userDto = this.userBaseService.findDtoByEmail(email);

        this.userBaseService.validationAuthSignIn(userDto.getEmail());

        String password = this.jwtService.getPassword(loginRequest.getJwt());

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        String newJwt = this.jwtService.createJwtWithEmailAndPassword(email, password);

        List<PhoneResponse> phones = new ArrayList<>();

        if(!this.phoneService.findByUuid(userDto.getUuid()).isEmpty()){
            List<PhoneDto> phoneDtos = this.phoneService.findByUuid(userDto.getUuid());
            // Functional programming incorporated in Java8
            phoneDtos.forEach( phoneDto -> {
                phones.add(PhoneResponse.builder()
                        .number(phoneDto.getNumber())
                        .cityCode(phoneDto.getCityCode())
                        .countryCode(phoneDto.getCountryCode())
                        .build()
                );
            });
        }

        this.userBaseService.updateLastLogin(userDto);

        return ResponseEntity.ok(LoginResponse.builder()
                .uuid(userDto.getUuid())
                .created(DateTimeFormat.format(userDto.getCreated()))
                .lastLogin(DateTimeFormat.format(userDto.getLastLogin()))
                        .token(newJwt)
                        .isActive(userDto.isActive())
                        .name(userDto.getName())
                        .email(userDto.getEmail())
                        .phones(phones)
                        .build()
                );
    }

}
