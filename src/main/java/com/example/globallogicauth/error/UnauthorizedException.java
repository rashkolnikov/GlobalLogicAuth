package com.example.globallogicauth.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(@NonNull String detail) {
        super(HttpStatus.UNAUTHORIZED.value(), detail);
    }
}
