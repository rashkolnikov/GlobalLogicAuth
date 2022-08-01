package com.example.myauthenticationserver.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(@NonNull String detail) {
        super(HttpStatus.UNAUTHORIZED.value(), detail);
    }
}
