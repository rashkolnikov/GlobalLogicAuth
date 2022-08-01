package com.example.globallogicauth.dto;

import com.example.globallogicauth.entity.Phone;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class PhoneDto {

    private String uuid;

    private long number;

    private int cityCode;

    private String countryCode;

    public static @NonNull PhoneDtoBuilder.Uuid builder(){return new PhoneDto.Builder();}

    private static class Builder implements
            PhoneDtoBuilder.Uuid,
            PhoneDtoBuilder.Number,
            PhoneDtoBuilder.CityCode,
            PhoneDtoBuilder.CountryCode,
            PhoneDtoBuilder.Build{

        private final PhoneDto phoneDto;

        private Builder(){
            this.phoneDto = new PhoneDto();
        }

        @Override
        public @NonNull PhoneDtoBuilder.Number uuid(@NonNull String uuid) {
            this.phoneDto.setUuid(uuid);
            return this;
        }

        @Override
        public @NonNull PhoneDtoBuilder.CityCode number(long number) {
            this.phoneDto.setNumber(number);
            return this;
        }

        @Override
        public @NonNull PhoneDtoBuilder.CountryCode cityCode(int cityCode) {
            this.phoneDto.setCityCode(cityCode);
            return this;
        }

        @Override
        public @NonNull PhoneDtoBuilder.Build countryCode(@NonNull String countryCode) {
            this.phoneDto.setCountryCode(countryCode);
            return this;
        }

        @Override
        public @NonNull PhoneDto build() {
            return this.phoneDto;
        }
    }

    private interface PhoneDtoBuilder{

        interface Uuid{
            @NonNull PhoneDtoBuilder.Number uuid(@NonNull String uuid);
        }

        interface Number{
            @NonNull PhoneDtoBuilder.CityCode number(long number);
        }

        interface CityCode{
            @NonNull PhoneDtoBuilder.CountryCode cityCode(int cityCode);
        }

        interface CountryCode{
            @NonNull PhoneDtoBuilder.Build countryCode(@NonNull String countryCode);
        }

        interface Build{
            @NonNull PhoneDto build();
        }
    }

}
