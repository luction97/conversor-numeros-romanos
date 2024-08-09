package com.possumus.conversor_numeros_romanos.utils;

public class RomanNumeralValidator {
    // Valida un número romano bien formado
    private static final String ROMAN_REGEX = "^(M{0,3})(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";

    public static boolean isValidRoman(String romanNumeral) {
        return romanNumeral != null && !romanNumeral.isEmpty() && romanNumeral.matches(ROMAN_REGEX);
    }

}
