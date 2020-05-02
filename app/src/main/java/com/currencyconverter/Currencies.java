package com.currencyconverter;


import androidx.annotation.NonNull;

public enum Currencies {
    USD ("4.1807"),
    EURO ("4.5432"),
    RUR("0.057");

    private String kind;

    Currencies(String kind) {
        this.kind = kind;
    }

    @NonNull
    @Override
    public String toString() {
        return kind;
    }
}