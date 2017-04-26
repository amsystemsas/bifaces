package com.cobus.util;

/**
 * Created by Naldo on 24/07/2016.
 */
public enum SymbolType {

    COMMA(",", 1),
    DOT(".", 2),
    SEMI_COLON(";", 3),
    COLON(":", 4),
    SPACE(" ", 5),
    LEFT_PARENTHESIS("(", 6),
    RIGHT_PARENTHESIS(")", 7);

    private final String value;
    private final int code;


    private SymbolType(String value, int code) {
        this.value = value;
        this.code = code;

    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }


}
