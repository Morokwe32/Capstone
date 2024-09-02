package za.ac.cput.util;

import java.util.UUID;

public class Helper
{
    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static void checkStringParam(String paramName, String paramValue) {
        if (isNullOrEmpty(paramValue)) {
            throw new IllegalArgumentException(paramName + " is required");
        }
    }

    public static void checkPositiveValue(String paramName, double paramValue) {
        if (paramValue <= 0) {
            throw new IllegalArgumentException(paramName + " must be greater than zero");
        }
    }
}
