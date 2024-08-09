package com.possumus.conversor_numeros_romanos.controller;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.possumus.conversor_numeros_romanos.exception.ErrorResponse;
import com.possumus.conversor_numeros_romanos.exception.InvalidNumberException;
import com.possumus.conversor_numeros_romanos.exception.InvalidRomanException;
import com.possumus.conversor_numeros_romanos.service.RomanNumeralConverterService;

import static org.hamcrest.Matchers.is;
import org.springframework.http.MediaType;

@WebMvcTest(RomanNumeralController.class)
public class RomanNumeralControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RomanNumeralConverterService converterService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test  // Conversión de un número entero a número romano
    public void testConvertToRoman_Success() throws Exception {
        int number = 1994;
        String romanNumeral = "MCMXCIV";
        when(converterService.toRomanNumeral(number)).thenReturn(romanNumeral);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-roman/{number}", number)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(romanNumeral)));
    }
    
    @Test // Error para la conversión de un número entero a número romano con un número inválido
    public void testConvertToRoman_Failure() throws Exception {
        int number = -1;
        when(converterService.toRomanNumeral(number)).thenThrow(new InvalidNumberException("Invalid number"));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-roman/{number}", number)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(new ErrorResponse("Invalid number"))));
    }
    
    @Test // Conversión de un número romano a entero
    public void testConvertToInteger_Success() throws Exception {
        String roman = "MCMXCIV";
        int number = 1994;
        when(converterService.fromRomanNumeral(roman)).thenReturn(number);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-arabic/{roman}", roman)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(String.valueOf(number))));
    }
    
    @Test // Conversión de un número romano a entero con un valor inválido
    public void testConvertToInteger_Failure() throws Exception {
        String roman = "INVALID";
        when(converterService.fromRomanNumeral(roman)).thenThrow(new InvalidRomanException("Número romano inválido"));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-arabic/{roman}", roman)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(new ErrorResponse("Número romano inválido"))));
    }
    
    @Test // Conversión de un número entero al mínimo número romano válido
    public void testConvertToRoman_MinimumValidNumber() throws Exception {
        int number = 1;
        String romanNumeral = "I";
        when(converterService.toRomanNumeral(number)).thenReturn(romanNumeral);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-roman/{number}", number)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(romanNumeral)));
    }
    
    @Test   // Conversión de un número entero al máximo número romano válido
    public void testConvertToRoman_MaximumValidNumber() throws Exception {
        int number = 3999;
        String romanNumeral = "MMMCMXCIX";
        when(converterService.toRomanNumeral(number)).thenReturn(romanNumeral);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-roman/{number}", number)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(romanNumeral)));
    }
    
    @Test // Error para la conversión de un número entero fuera del rango válido
    public void testConvertToRoman_OutOfRangeNumber() throws Exception {
        int number = 4000; // El rango válido es hasta 3999
        when(converterService.toRomanNumeral(number)).thenThrow(new InvalidNumberException("Número fuera de rango"));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-roman/{number}", number)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(new ErrorResponse("Número fuera de rango"))));
    }
    
    @Test     // Conversión de un número romano al mínimo número entero válido
    public void testConvertToInteger_MinimumValidRoman() throws Exception {
        String roman = "I";
        int number = 1;
        when(converterService.fromRomanNumeral(roman)).thenReturn(number);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-arabic/{roman}", roman)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(String.valueOf(number))));
    }
    
    @Test
    // Prueba exitosa para la conversión de un número romano al máximo número entero válido
    public void testConvertToInteger_MaximumValidRoman() throws Exception {
        String roman = "MMMCMXCIX";
        int number = 3999;
        when(converterService.fromRomanNumeral(roman)).thenReturn(number);
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-arabic/{roman}", roman)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(is(String.valueOf(number))));
    }
    
    @Test     // Error para la conversión de un número romano inválido
    public void testConvertToInteger_InvalidRoman() throws Exception {
        String roman = "INVALID_ROMAN";
        when(converterService.fromRomanNumeral(roman)).thenThrow(new InvalidRomanException("Número romano inválido"));
    
        mockMvc.perform(MockMvcRequestBuilders.get("/api/convert-to-arabic/{roman}", roman)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .json(objectMapper.writeValueAsString(new ErrorResponse("Número romano inválido"))));
    }
}
