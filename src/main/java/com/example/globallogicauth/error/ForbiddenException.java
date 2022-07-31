package com.example.globallogicauth.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class ForbiddenException extends CustomException{

    public ForbiddenException(@NonNull String detail) {
        super(HttpStatus.FORBIDDEN.value(), detail);
    }

}
