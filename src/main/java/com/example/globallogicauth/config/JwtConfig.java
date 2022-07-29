package com.example.globallogicauth.config;

import com.example.globallogicauth.jwt.IJwtService;
import com.example.globallogicauth.jwt.JwtFilter;
import com.example.globallogicauth.service.UserBaseService;
import lombok.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final IJwtService jwtService;
    private final UserBaseService userBaseService;
    private final AuthenticationManager authenticationManager;

    public JwtConfig(@NonNull final AuthenticationManager authenticationManager,
                     @NonNull final IJwtService jwtService,
                     @NonNull final UserBaseService userBaseService) {
        this.jwtService = jwtService;
        this.userBaseService = userBaseService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(@NonNull HttpSecurity http) {
        final JwtFilter customFilter = new JwtFilter(this.authenticationManager, this.jwtService, this.userBaseService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
