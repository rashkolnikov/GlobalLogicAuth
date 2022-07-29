package com.example.globallogicauth.jwt;

import com.example.globallogicauth.service.UserBaseService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;

@RequiredArgsConstructor
@Component
public class JwtService extends BaseJwtService {

    private static final String AUDIENCE = "2meetup";

    private final UserBaseService userBaseService;

    @Value("${app-jwt.secret}")
    private String secretKey;

    @Value("${app-jwt.expire-length}")
    private long validityInMilliseconds;

    @PostConstruct
    protected void initialize() {
        this.secretKey = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
    }

    @Override
    public @NonNull String getSecretKey() {
        return this.secretKey;
    }

    @Override
    public long getJwtDurationInMilliseconds() {
        return this.validityInMilliseconds;
    }

    @Override
    public @NonNull UserDetailsService getUserDetailsService() {
        return this.userBaseService;
    }

    @Override
    public @NonNull String getAudience() {
        return AUDIENCE;
    }
}
