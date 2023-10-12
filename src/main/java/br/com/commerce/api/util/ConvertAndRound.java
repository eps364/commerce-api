package br.com.commerce.api.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ConvertAndRound {

    private ConvertAndRound() {
        throw new IllegalStateException("ConvertAndRound class cannot be instantiated");
    }

    public static double convert(Double value) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double convert(Double value, int precision) {
        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(precision, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
