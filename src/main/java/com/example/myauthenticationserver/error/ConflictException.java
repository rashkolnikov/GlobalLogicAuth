package com.example.myauthenticationserver.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class ConflictException extends CustomException {

    public ConflictException(@NonNull String detail) {
        super(HttpStatus.CONFLICT.value(), detail);
    }

}
