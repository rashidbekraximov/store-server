package uz.cluster.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class Converter {

    public static Double formatter(double num){
        double number = roundUsingBigDecimal(num,2);
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setGroupingSeparator(' ');
        DecimalFormat format = new DecimalFormat("###,###.00", decimalFormatSymbols);
        return Double.parseDouble(format.format(number));
    }

    public static double roundUsingBigDecimal(double value, int places) {
        if (places < 0) throw new IllegalArgumentException("Decimal places must be non-negative");
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
