package com.billy.demo.open.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MoneyUtil {

    /** 
     * double 元转成分
     */
    public static String yuan2fen(double d1) {
        BigDecimal bd1 = new BigDecimal(Double.toString(d1)); 
        BigDecimal bd2 = new BigDecimal(Double.toString(100));
        DecimalFormat df=new DecimalFormat("0");
        return df.format(bd1.multiply(bd2).doubleValue()); 
    } 
}
