package com.example.mooncalculator.Notation;

public class Analyzer {
    private String expression;

    public Analyzer(String expression) {
        this.expression = expression;
    }

    public NotationType getType() {
        return NotationType.infix;
    }

}

