package com.example.globallogicauth.error;

import lombok.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class ForbiddenException extends CustomException{

    public ForbiddenException(@NonNull String detail) {
        super(Timestamp.from(Instant.now()), 403, detail);
    }

}
