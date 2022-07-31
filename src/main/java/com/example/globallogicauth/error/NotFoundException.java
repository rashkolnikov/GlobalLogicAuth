package com.example.globallogicauth.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;

public class NotFoundException extends CustomException {

    public NotFoundException(@NonNull String detail) {
        super(HttpStatus.NOT_FOUND.value(), detail);
    }

}
