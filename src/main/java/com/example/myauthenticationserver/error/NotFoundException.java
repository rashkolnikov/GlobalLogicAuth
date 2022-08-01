package com.example.myauthenticationserver.error;

import lombok.NonNull;
import org.springframework.http.HttpStatus;

public class NotFoundException extends CustomException {

    public NotFoundException(@NonNull String detail) {
        super(HttpStatus.NOT_FOUND.value(), detail);
    }

}
