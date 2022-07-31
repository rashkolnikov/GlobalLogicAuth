package com.example.globallogicauth.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class UnprocessableEntityException extends CustomException{
    public UnprocessableEntityException(@NonNull String detail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(), detail);
    }
}
