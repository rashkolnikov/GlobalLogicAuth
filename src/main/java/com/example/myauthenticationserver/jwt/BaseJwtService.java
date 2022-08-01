package com.example.myauthenticationserver.jwt;

import io.jsonwebtoken.*;
import lombok.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;

public abstract class BaseJwtService implements IJwtService{

    private static final String ISSUER = "Franco";

    private static final String BEARER = "Bearer ";

    public BaseJwtService(){

    }

    public abstract @NonNull String getSecretKey();

    public abstract long getJwtDurationInMilliseconds();

    public abstract @NonNull UserDetailsService getUserDetailsService();

    public abstract @NonNull String getAudience();

    public @NonNull String createJwtWithEmailAndPassword(final @NonNull String email, final @NonNull String password){
        final Claims claims = Jwts.claims()
                .setSubject(email)
                .setIssuer(ISSUER)
                .setAudience(this.getAudience());
        claims.put("Password", password);
        final Date now = new Date();
        final Date expiration = new Date(now.getTime() + this.getJwtDurationInMilliseconds());
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.getSecretKey())
                .compact();
    }

    public @NonNull Authentication getAuthentication(final @NonNull String jwt){
        UserDetails userDetails = this.getUserDetailsService().loadUserByUsername(this.getEmail(jwt));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public @NonNull String getEmail(final @NonNull String jwt) {
        return Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(jwt).getBody().getSubject();
    }

    public @NonNull String getPassword(final @NonNull String jwt) {
        return Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(jwt).getBody().get("Password", String.class);
    }


    public String resolveJwt(final @NonNull HttpServletRequest req) {
        String jwt = req.getHeader("Authorization");
        return jwt != null && jwt.startsWith(BEARER) ? jwt.substring(7) : null;
    }

    public boolean isBearer(final @NonNull String jwt) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.getSecretKey()).parseClaimsJws(jwt);
        Claims body = claims.getBody();
        return !body.getExpiration().before(Calendar.getInstance().getTime())
                && body.getIssuer().equals(ISSUER) &&
                body.getAudience().equals(this.getAudience());
    }
}
