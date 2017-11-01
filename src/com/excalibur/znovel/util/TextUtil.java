package com.excalibur.znovel.util;

public class TextUtil {

    public static boolean isEmpty(String value){
        return value == null || value.equals("");
    }

    public static String fillValue(String value){
        if(isEmpty(value))
            return null;
        return "'" + value + "'";
    }
}
