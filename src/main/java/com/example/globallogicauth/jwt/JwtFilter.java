package com.example.globallogicauth.jwt;

import com.example.globallogicauth.service.UserBaseService;
import lombok.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JwtFilter extends BasicAuthenticationFilter {

    private static final String INVALID_JWT = "Invalid jwt.";
    private static final String USER_NOT_ENABLED = "User not enabled";

    private final IJwtService jwtService;
    private final UserBaseService userBaseService;

    public JwtFilter(@NonNull final AuthenticationManager authenticationManager,
                     @NonNull final IJwtService jwtService,
                     @NonNull final UserBaseService userBaseService) {
        super(authenticationManager);
        this.jwtService = jwtService;
        this.userBaseService = userBaseService;
    }

    @Override
    public void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain chain
    ) throws IOException, ServletException {

        final String jwt = this.jwtService.resolveJwt(request);
        try {
            if (jwt != null && this.jwtService.isBearer(jwt)) {
                final Authentication auth = this.jwtService.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(auth);
                final String userUuid = this.jwtService.getEmail(jwt);
                if (this.userBaseService.userEnabled(userUuid)) {
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, USER_NOT_ENABLED);
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwt, null);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, INVALID_JWT);
            return;
        }

        chain.doFilter(request, response);
    }
}
