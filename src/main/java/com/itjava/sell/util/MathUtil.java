package com.itjava.sell.util;

public class MathUtil {
    private static final Double MONEY_RANGE=0.01;
    /**
     * 判断2个金额是否相等
     * @param d1
     * @param d2
     * @return
     */
    public static boolean equals(Double d1,Double d2){
        double result = Math.abs(d1 - d2);
        if (result<MONEY_RANGE){
            return true;
        }else {
            return false;
        }
    }
}
