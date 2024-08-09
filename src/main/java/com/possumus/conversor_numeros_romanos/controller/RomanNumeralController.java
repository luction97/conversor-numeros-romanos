package com.possumus.conversor_numeros_romanos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.possumus.conversor_numeros_romanos.exception.ErrorResponse;
import com.possumus.conversor_numeros_romanos.exception.InvalidNumberException;
import com.possumus.conversor_numeros_romanos.exception.InvalidRomanException;
import com.possumus.conversor_numeros_romanos.service.RomanNumeralConverterService;

@RestController
@RequestMapping("/api")
public class RomanNumeralController {

    private final RomanNumeralConverterService converterService;

    public RomanNumeralController(RomanNumeralConverterService converterService) {
        this.converterService = converterService;
    }

    @GetMapping("/convert-to-roman/{number}")
    public ResponseEntity<?> convertToRoman(@PathVariable int number) {
        try {
 
            String romanNumeral = converterService.toRomanNumeral(number);
            return ResponseEntity.ok(romanNumeral);
        } catch (InvalidNumberException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/convert-to-arabic/{roman}")
    public ResponseEntity<?> convertToInteger(@PathVariable String roman) {
        try {

            Integer result = converterService.fromRomanNumeral(roman);
            return ResponseEntity.ok(result);
        } catch (InvalidRomanException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

}
