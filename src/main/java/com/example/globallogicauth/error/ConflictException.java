package com.example.globallogicauth.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class ConflictException extends CustomException {

    public ConflictException(@NonNull String detail) {
        super(HttpStatus.CONFLICT.value(), detail);
    }

}
