package com.example.globallogicauth.error;

import lombok.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(@NonNull String detail) {
        super(Timestamp.from(Instant.now()), 401, detail);
    }
}
