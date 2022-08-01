package com.example.myauthenticationserver.jwt;

import lombok.NonNull;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IJwtService {

    @NonNull String createJwtWithEmailAndPassword(final @NonNull String email, final @NonNull String password);

    @NonNull Authentication getAuthentication(final @NonNull String jwt);

    @NonNull String getEmail(final @NonNull String jwt);

    String resolveJwt(final @NonNull HttpServletRequest request);

    boolean isBearer(final @NonNull String jwt);
}
