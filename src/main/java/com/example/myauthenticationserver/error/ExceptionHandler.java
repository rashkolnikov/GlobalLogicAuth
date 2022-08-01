package com.example.myauthenticationserver.error;

import com.example.myauthenticationserver.error.payload.ErrorResponse;
import com.example.myauthenticationserver.error.payload.ExceptionResponse;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.Timestamp;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            org.springframework.http.converter.HttpMessageNotReadableException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse badRequestRequestHttpMessageNotReadableException() {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(Timestamp.from(Instant.now()))
                        .code(400)
                        .detail("The request has invalid valid data.")
                        .build())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            org.springframework.web.bind.MethodArgumentNotValidException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse badRequestMethodArgumentNotValidException() {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(Timestamp.from(Instant.now()))
                        .code(400)
                        .detail("The request has invalid valid data.")
                        .build())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            org.springframework.web.bind.MethodArgumentNotValidException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse badRequestRequestMethodArgumentNotValidException() {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(Timestamp.from(Instant.now()))
                        .code(400)
                        .detail("The request has invalid valid data.")
                        .build())
                .build();
    }

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
                .build();
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @org.springframework.web.bind.annotation.ExceptionHandler({
            UnprocessableEntityException.class
    })
    @ResponseBody
    public @NonNull ErrorResponse conflictRequest(@NonNull UnprocessableEntityException unprocessableEntityException) {
        return ErrorResponse.builder()
                .add(ExceptionResponse.builder()
                        .timestamp(unprocessableEntityException.getTimestamp())
                        .code(unprocessableEntityException.getCode())
                        .detail(unprocessableEntityException.getDetail())
                        .build())
                .build();
    }

}
