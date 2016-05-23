package com.unimagdalena.edu.co.domicilios;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {

    public static String formatoPeso(Double valor) {
        return NumberFormat.getCurrencyInstance(new Locale("es", "CO")).format(valor);
    }
}