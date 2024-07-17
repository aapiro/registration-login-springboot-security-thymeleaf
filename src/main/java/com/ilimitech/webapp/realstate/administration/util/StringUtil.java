package com.ilimitech.webapp.realstate.administration.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    // Expresión regular para validar el formato de un email
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    public static boolean isEmail(String input) {
        if (input == null) {
            return false;
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

}
