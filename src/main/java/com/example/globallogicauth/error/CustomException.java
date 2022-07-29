package com.example.globallogicauth.error;

import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;

@Getter
public abstract class CustomException extends RuntimeException{

    private final Timestamp timestamp;

    private final int code;

    private final String detail;


    public CustomException(@NonNull final Timestamp timestamp, final int code, @NonNull final String detail){
        super(detail);
        this.timestamp = timestamp;
        this.code = code;
        this.detail = detail;
    }
}
