package com.example.globallogicauth.controller.auth.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PhoneResponse {

    private long number;

    private int cityCode;

    private String countryCode;

    public static @NonNull PhoneResponseBuilder.Number builder(){return new PhoneResponse.Builder();}

    private static class Builder implements
            PhoneResponseBuilder.Number,
            PhoneResponseBuilder.CityCode,
            PhoneResponseBuilder.CountryCode,
            PhoneResponseBuilder.Build {

        private final PhoneResponse phoneResponse;

        private Builder(){
            this.phoneResponse = new PhoneResponse();
        }

        @Override
        public PhoneResponseBuilder.@NonNull CityCode number(long number) {
            this.phoneResponse.setNumber(number);
            return this;
        }

        @Override
        public PhoneResponseBuilder.@NonNull CountryCode cityCode(int cityCode) {
            this.phoneResponse.setCityCode(cityCode);
            return this;
        }

        @Override
        public PhoneResponseBuilder.@NonNull Build countryCode(String countryCode) {
            this.phoneResponse.setCountryCode(countryCode);
            return this;
        }

        @Override
        public @NonNull PhoneResponse build() {
            return this.phoneResponse;
        }
    }
    private interface PhoneResponseBuilder{
        interface Number{
            @NonNull CityCode number(long number);
        }
        interface CityCode{
            @NonNull CountryCode cityCode(int cityCode);
        }
        interface CountryCode{
            @NonNull Build countryCode(String countryCode);
        }

        interface Build{
            @NonNull PhoneResponse build();
        }
    }

}
