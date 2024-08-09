package com.possumus.conversor_numeros_romanos.service;

import com.possumus.conversor_numeros_romanos.exception.InvalidNumberException;
import com.possumus.conversor_numeros_romanos.exception.InvalidRomanException;

public interface RomanNumeralConverterService {

        String toRomanNumeral(int number) throws InvalidNumberException;
        int fromRomanNumeral(String romanNumeral) throws InvalidRomanException;

}
