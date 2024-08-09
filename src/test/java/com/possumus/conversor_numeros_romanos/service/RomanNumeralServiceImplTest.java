package com.possumus.conversor_numeros_romanos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.possumus.conversor_numeros_romanos.exception.InvalidNumberException;
import com.possumus.conversor_numeros_romanos.exception.InvalidRomanException;


public class RomanNumeralServiceImplTest {

    private final RomanNumeralConverterServiceImpl converter = new RomanNumeralConverterServiceImpl();

    @Test       // El mínimo y máximo válido
    void testToRomanNumeralValidInputs() {
        assertEquals("I", converter.toRomanNumeral(1)); 
        assertEquals("MMMCMXCIX", converter.toRomanNumeral(3999)); 
    }

    @Test      // Números fuera del rango permitido
    void testToRomanNumeralInvalidInput() {
        assertThrows(InvalidNumberException.class, () -> converter.toRomanNumeral(0)); 
        assertThrows(InvalidNumberException.class, () -> converter.toRomanNumeral(4000)); 
        assertThrows(InvalidNumberException.class, () -> converter.toRomanNumeral(-1)); 
    }

    @Test    // El mínimo y máximo válido para números romanos
    void testFromRomanNumeralValidInputs() {
        assertEquals(1, converter.fromRomanNumeral("I")); 
        assertEquals(3999, converter.fromRomanNumeral("MMMCMXCIX")); 
    }

    @Test  // Casos inválidos que no siguen las reglas de los números romanos
    void testFromRomanNumeralInvalidInputs() {
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("IIII")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("ABCD")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("MMMCMXCIXI")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("VX")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("IM")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("MMMM")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral("")); 
        assertThrows(InvalidRomanException.class, () -> converter.fromRomanNumeral(null)); 
    }

    @Test // Verifica que la conversión maneja correctamente las entradas con espacios
    void testFromRomanNumeralInputWithSpaces() {
        assertEquals(4, converter.fromRomanNumeral(" IV "));
        assertEquals(99, converter.fromRomanNumeral(" XCIX "));
    }

    @Test // Verifica que la conversión maneja correctamente entradas en mayúsculas y minúsculas
    void testFromRomanNumeralMixedCaseInput() {
        assertEquals(4, converter.fromRomanNumeral("Iv"));
        assertEquals(99, converter.fromRomanNumeral("xciX"));
    }

}
