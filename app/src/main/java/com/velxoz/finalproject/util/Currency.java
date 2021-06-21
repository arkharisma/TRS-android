package com.velxoz.finalproject.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Currency {
    private static Locale locale;

    public Currency(){
        locale = new Locale("in", "ID");
    }
    public static String getCurrencyFormat(Integer number){
        NumberFormat formatter=NumberFormat.getCurrencyInstance(locale);
        return formatter.format(Double.valueOf(number));
    }
}
