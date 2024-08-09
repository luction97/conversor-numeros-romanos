package com.possumus.conversor_numeros_romanos.model;

public enum RomanNumeral {
    M(1000, "M"), 
    CM(900, "CM"), 
    D(500, "D"),
    CD(400, "CD"), 
    C(100, "C"), 
    XC(90, "XC"), 
    L(50, "L"), 
    XL(40, "XL"), 
    X(10, "X"), 
    IX(9, "IX"), 
    V(5, "V"), 
    IV(4, "IV"), 
    I(1, "I"); 

    private final int value;
    private final String symbol;

    RomanNumeral(int value, String symbol) {
        this.value = value;
        this.symbol = symbol;
    }

    public int getValue() {
        return value;
    }

    public String getSymbol() {
        return symbol;
    }

    // Para obtener un número romano basado en el valor
    public static RomanNumeral fromValue(int value) {
        for (RomanNumeral numeral : values()) {

            if (numeral.getValue() == value) {
                return numeral;
            }
        }
        throw new IllegalArgumentException("Valor no soportado: " + value);
    }

    public static RomanNumeral fromSymbol(String symbol) {
        for (RomanNumeral numeral : values()) {
            if (numeral.getSymbol().equals(symbol)) {
                return numeral;
            }
        }
        return null;
    }

}
