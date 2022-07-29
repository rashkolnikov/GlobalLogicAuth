package com.example.globallogicauth.error;

import com.example.globallogicauth.error.payload.ErrorResponse;
import com.example.globallogicauth.error.payload.ExceptionResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            org.springframework.security.core.userdetails.UsernameNotFoundException.class,
            UnauthorizedException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse unauthorizedRequest(@NonNull UnauthorizedException unauthorizedException) {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(unauthorizedException.getTimestamp())
                        .code(unauthorizedException.getCode())
                        .detail(unauthorizedException.getDetail())
                        .build())
                .build();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            NotFoundException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse notFoundRequest(@NonNull NotFoundException notFoundException) {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(notFoundException.getTimestamp())
                        .code(notFoundException.getCode())
                        .detail(notFoundException.getDetail())
                        .build())
                .build();
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            ForbiddenException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse forbiddenRequest(@NonNull ForbiddenException forbiddenException) {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(forbiddenException.getTimestamp())
                        .code(forbiddenException.getCode())
                        .detail(forbiddenException.getDetail())
                        .build())
                .build();    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            ConflictException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse conflictRequest(@NonNull ConflictException conflictException) {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(conflictException.getTimestamp())
                        .code(conflictException.getCode())
                        .detail(conflictException.getDetail())
                        .build())
                .build();    }

}
