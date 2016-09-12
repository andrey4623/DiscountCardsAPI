package com.andrey4623.discount_cards_api.app;

public class Utils {
    public static final int cardNumberLength = 13;

    public static boolean isNumberValid(long number) {
        int length = String.valueOf(number).length();
        return (number >=0) && (length == cardNumberLength);
    }

    public static boolean isValueValid(float value) {
        return value >= 0.0;
    }

    public static boolean isDiscountValid(float discount) {
        return discount >= 0.0;
    }
}
