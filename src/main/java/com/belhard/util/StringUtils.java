package com.belhard.util;

public final class StringUtils {

    public static boolean isEmpty(String value) {
        return ((value == null) || value.trim().isEmpty());
    }

}
