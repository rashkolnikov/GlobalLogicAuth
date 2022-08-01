package com.example.globallogicauth.error;

import lombok.NonNull;

public class BadRequestException extends CustomException{

    public BadRequestException(int code, @NonNull String detail) {
        super(code, detail);
    }
}
