package com.example.globallogicauth.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Value;

@Getter
@Value
@AllArgsConstructor(onConstructor=@__(@JsonCreator))
public class PhoneRequest {

    long number;

    int cityCode;

    @NonNull
    String countryCode;

}
