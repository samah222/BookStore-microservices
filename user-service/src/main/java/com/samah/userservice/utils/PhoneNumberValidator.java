package com.samah.userservice.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {
    private static final String PHONE_REGEX =
            "^\\+(?:[0-9] ?){6,14}[0-9]$";

    private static final Pattern pattern = Pattern.compile(PHONE_REGEX);

    public static boolean isValidPhoneNumber(String phoneNumber) {
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
