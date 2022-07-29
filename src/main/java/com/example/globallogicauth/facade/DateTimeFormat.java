package com.example.globallogicauth.facade;

import lombok.NonNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DateTimeFormat {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.systemDefault());

    @NonNull public static String format(@NonNull Instant instant){
        return formatter.format(instant);
    }
}
