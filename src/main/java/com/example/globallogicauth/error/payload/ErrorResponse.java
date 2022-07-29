package com.example.globallogicauth.error.payload;

import com.example.globallogicauth.controller.auth.payload.PhoneResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    private List<ExceptionResponse> exceptionResponses = new ArrayList<>();

    public static @NonNull ErrorResponseBuilder.Optionals builder(){return new ErrorResponse.Builder();}

    private static class Builder implements
            ErrorResponseBuilder.Optionals{

        private final ErrorResponse errorResponse;

        private Builder(){
            this.errorResponse = new ErrorResponse();
        }

        @Override
        public @NonNull ErrorResponseBuilder.Optionals add(ExceptionResponse exception) {
            this.errorResponse.exceptionResponses.add(exception);
            return this;
        }

        @Override
        public @NonNull ErrorResponse build() {
            return this.errorResponse;
        }
    }
    private interface ErrorResponseBuilder{
        interface Optionals{
            @NonNull Optionals add(ExceptionResponse exception);

            @NonNull ErrorResponse build();
        }
    }

}
