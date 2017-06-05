package com.amsystem.bifaces.util;

/**
 * Created by Naldo on 24/07/2016.
 */
public enum SymbolType {

    COMMA(","),
    DOT("."),
    SEMI_COLON(";"),
    COLON(":"),
    SPACE(" "),
    LEFT_PARENTHESIS("("),
    RIGHT_PARENTHESIS(")"),
    UNDERSCORE("_"),
    PIPE("|"),
    AMPERSAND_SYMBOL("&"),
    PLUS("+"),
    MINUS("-"),
    BACKSLASH("\\"),
    AT_SING("@");

    private final String value;

    private SymbolType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
