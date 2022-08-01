package com.example.myauthenticationserver.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Getter
@Value
@AllArgsConstructor(onConstructor=@__(@JsonCreator))
public class LoginRequest {

    @NonNull
    String jwt;

}
