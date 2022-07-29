package com.example.globallogicauth.error;

import lombok.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class UnprocessableEntityException extends CustomException{
    public UnprocessableEntityException(@NonNull String detail) {
        super(Timestamp.from(Instant.now()), 422, detail);
    }
}
