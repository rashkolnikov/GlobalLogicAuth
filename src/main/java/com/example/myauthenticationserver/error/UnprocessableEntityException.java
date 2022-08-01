package com.example.myauthenticationserver.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class UnprocessableEntityException extends CustomException{
    public UnprocessableEntityException(@NonNull String detail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY.value(), detail);
    }
}
