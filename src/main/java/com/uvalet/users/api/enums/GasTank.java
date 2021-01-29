package com.uvalet.users.api.enums;

public enum GasTank {

    EMPTY("E"), FULL("F"), HALF("1/2"), QUARTER("1/4"), THREE_QUARTERS("3/4");

    private String symbol;


    GasTank(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
