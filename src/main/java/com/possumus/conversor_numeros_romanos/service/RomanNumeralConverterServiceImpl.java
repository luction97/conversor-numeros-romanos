package com.possumus.conversor_numeros_romanos.service;

import org.springframework.stereotype.Service;

import com.possumus.conversor_numeros_romanos.exception.InvalidNumberException;
import com.possumus.conversor_numeros_romanos.exception.InvalidRomanException;
import com.possumus.conversor_numeros_romanos.model.RomanNumeral;
import com.possumus.conversor_numeros_romanos.utils.RomanNumeralValidator;

@Service
public class RomanNumeralConverterServiceImpl implements RomanNumeralConverterService {

    @Override
    public String toRomanNumeral(int number) throws InvalidNumberException {
        if (number <= 0 || number > 3999) {
            throw new InvalidNumberException("Número fuera de rango: " + number);
        }
    
        StringBuilder romanNumeral = new StringBuilder();
    
        // Itera sobre los valores del enum en orden descendente
        for (RomanNumeral numeral : RomanNumeral.values()) {
            while (number >= numeral.getValue()) {
                romanNumeral.append(numeral.getSymbol());
                number -= numeral.getValue();
            }
        }
    
        return romanNumeral.toString();
    }

    @Override
    public int fromRomanNumeral(String romanNumeral) throws InvalidRomanException {
        if (romanNumeral == null || romanNumeral.trim().isEmpty()) {
            throw new InvalidRomanException("Número romano inválido: " + romanNumeral);
        }
    
        romanNumeral = romanNumeral.toUpperCase().trim();
    
        // Valida que el número romano es válido
        if (!RomanNumeralValidator.isValidRoman(romanNumeral)) {
            throw new InvalidRomanException("Número romano inválido: " + romanNumeral);
        }
    
        int result = 0;
        int prevValue = 0;
    
        // Convertir el número romano a entero usando el enum
        for (int i = romanNumeral.length() - 1; i >= 0; ) {
            // Intenta encontrar el símbolo de dos caracteres primero
            RomanNumeral currentNumeral = null;
            if (i > 0) {
                String twoCharSymbol = romanNumeral.substring(i - 1, i + 1);
                currentNumeral = RomanNumeral.fromSymbol(twoCharSymbol);
            }
    
            // Si no encuentra un símbolo de dos caracteres, busca uno de un solo carácter
            if (currentNumeral == null) {
                String oneCharSymbol = romanNumeral.substring(i, i + 1);
                currentNumeral = RomanNumeral.fromSymbol(oneCharSymbol);
            }
    
            if (currentNumeral == null) {
                throw new InvalidRomanException("Carácter romano inválido: " + romanNumeral.charAt(i));
            }
    
            int currentValue = currentNumeral.getValue();
    
            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }
    
            prevValue = currentValue;
            i -= currentNumeral.getSymbol().length(); // Decrementa por la longitud del símbolo encontrado
        }
    
        return result;
    }

}
