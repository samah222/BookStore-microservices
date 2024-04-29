package com.samah.userservice.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class TokenExpirationTime {
    private static final long EXPIRATION_TIME = 10;

    public static LocalDateTime calculateExpirationDate() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expirationTime = now.plusMinutes(EXPIRATION_TIME);
        return expirationTime;
    }

    public static int calculateDifferenceTime(LocalDateTime time1, LocalDateTime time2) {
        //LocalDateTime diff = time1.
        Duration duration = Duration.between(time1, time2);
        if (duration.toDays() == 0 && duration.toHours() == 0 && duration.toMinutes() < 10)
            return 1;
        else
            return -1;
    }
}
