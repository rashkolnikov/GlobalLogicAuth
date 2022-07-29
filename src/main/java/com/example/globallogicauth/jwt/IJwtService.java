package com.example.globallogicauth.jwt;

import com.example.globallogicauth.dto.UserDto;
import lombok.NonNull;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface IJwtService {

    @NonNull String createJwt(final @NonNull String username);

    @NonNull Authentication getAuthentication(final @NonNull String jwt);

    @NonNull String getUserUuid(final @NonNull String jwt);

    String resolveJwt(final @NonNull HttpServletRequest request);

    boolean isBearer(final @NonNull String jwt);
}
