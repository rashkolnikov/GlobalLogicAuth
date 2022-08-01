package com.example.myauthenticationserver.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Phone {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.PROTECTED)
    private Long id;

    @Column(name = "uuid", length = 64, nullable = false, updatable = false)
    private String uuid;

    @Column(name = "number", nullable = false)
    private long number;

    @Column(name = "city_code", nullable = false)
    private int cityCode;

    @Column(name = "country_code", length = 8, nullable = false)
    private String countryCode;

    public static @NonNull PhoneBuilder.Uuid builder(){return new Phone.Builder();}

    private static class Builder implements
            PhoneBuilder.Uuid,
            PhoneBuilder.Number,
            PhoneBuilder.CityCode,
            PhoneBuilder.CountryCode,
            PhoneBuilder.Build{

        private final Phone phone;

        private Builder(){
            this.phone = new Phone();
        }

        @Override
        public @NonNull PhoneBuilder.Number uuid(@NonNull String uuid) {
            this.phone.setUuid(uuid);
            return this;
        }

        @Override
        public @NonNull PhoneBuilder.CityCode number(long number) {
            this.phone.setNumber(number);
            return this;
        }

        @Override
        public @NonNull PhoneBuilder.CountryCode cityCode(int cityCode) {
            this.phone.setCityCode(cityCode);
            return this;
        }

        @Override
        public @NonNull PhoneBuilder.Build countryCode(@NonNull String countryCode) {
            this.phone.setCountryCode(countryCode);
            return this;
        }

        @Override
        public @NonNull Phone build() {
            return this.phone;
        }
    }

    private interface PhoneBuilder{

        interface Uuid{
            @NonNull Number uuid(@NonNull String uuid);
        }

        interface Number{
            @NonNull CityCode number(long number);
        }

        interface CityCode{
            @NonNull CountryCode cityCode(@NonNull int cityCode);
        }

        interface CountryCode{
            @NonNull Build countryCode(@NonNull String countryCode);
        }

        interface Build{
            @NonNull Phone build();
        }
    }
}
