package com.example.globallogicauth.error;

import lombok.NonNull;

import java.sql.Timestamp;
import java.time.Instant;

public class NotFoundException extends CustomException {

    public NotFoundException(@NonNull String detail) {
        super(Timestamp.from(Instant.now()), 404, detail);
    }

}
