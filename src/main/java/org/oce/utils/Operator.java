package org.oce.utils;

public enum Operator {
    ADD('+'),
    SUB('-'),
    MULTIPLICATION('*'),
    DIVISION('/'),
    POW('^'),
    SQRT('s'),
    LEFT_PARENTHESIS('('),
    RIGHT_PARENTHESIS(')');

    private final char symbol;

    Operator(final char symbol) {
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public static Operator fromSymbol(char symbol) {
        for (Operator operator : Operator.values()) {
            if (operator.getSymbol() == symbol) {
                return operator;
            }
        }
        throw new IllegalArgumentException("Invalid operator: " + symbol);
    }

    public static boolean isSimpleOperator(Operator request) {
        return request == ADD || request == SUB || request == MULTIPLICATION || request == DIVISION || request == POW || request == SQRT;
    }

    public static boolean isGroupOperator(Operator request) {
        return request == LEFT_PARENTHESIS || request == RIGHT_PARENTHESIS;
    }

    public static boolean isBasicOperator(Operator request) {
        return request == ADD || request == SUB || request == MULTIPLICATION || request == DIVISION;
    }
}
