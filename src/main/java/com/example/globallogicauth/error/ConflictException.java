package com.example.globallogicauth.error;

import lombok.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class ConflictException extends CustomException {

    public ConflictException(@NonNull String detail) {
        super(Timestamp.from(Instant.now()), 409, detail);
    }

}
