package com.amsystem.bifaces.util;

/**
 * Created by Naldo on 24/07/2016.
 */
public class StringUtil {

    /**
     * @param string String
     * @return boolean
     */
    public static boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    /**
     * @param string String
     * @return boolean
     */
    public static boolean isEmptyOrNullValue(String string) {
        return (StringUtil.isEmpty(string) || (string.trim().equalsIgnoreCase("null")));
    }

    public static boolean isNumber(String s) {
        return !isEmpty(s) && s.matches("-?[0-9]+");
    }

    public static boolean isDecimalNumber(String s) {
        return !isEmpty(s) && s.matches("-?([0-9]+).[0-9]+");
    }

    /**
     * Identifies whether or not the alphanumeric String
     * @param s
     * @return true or false
     */
    public static boolean isAlphanumeric(String s){
        return  !isEmpty(s) && s.matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$");
    }



}
