package com.example.myauthenticationserver.error.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {

    private Timestamp timestamp;

    private int code;

    private String detail;

    public static @NonNull ExceptionResponseBuilder.ITimestamp builder(){return new ExceptionResponse.Builder();}

    private static class Builder implements
            ExceptionResponseBuilder.ITimestamp,
            ExceptionResponseBuilder.Code,
            ExceptionResponseBuilder.Detail,
            ExceptionResponseBuilder.Build {

        private final ExceptionResponse exceptionResponse;

        private Builder(){
            this.exceptionResponse = new ExceptionResponse();
        }


        @Override
        public @NonNull ExceptionResponse build() {
            return this.exceptionResponse;
        }

        @Override
        public @NonNull ExceptionResponseBuilder.Code timestamp(@NonNull Timestamp timestamp) {
            this.exceptionResponse.setTimestamp(timestamp);
            return this;
        }

        @Override
        public @NonNull ExceptionResponseBuilder.Detail code(int code) {
            this.exceptionResponse.setCode(code);
            return this;
        }

        @Override
        public @NonNull ExceptionResponseBuilder.Build detail(@NonNull String detail) {
            this.exceptionResponse.setDetail(detail);
            return this;
        }
    }

    private interface ExceptionResponseBuilder{
        interface ITimestamp{
            @NonNull Code timestamp(@NonNull Timestamp timestamp);
        }
        interface Code{
            @NonNull Detail code(int code);
        }
        interface Detail{
            @NonNull Build detail(@NonNull String detail);
        }

        interface Build{
            @NonNull ExceptionResponse build();
        }
    }

}
