package com.itjava.sell.util;

import java.util.Random;

public class KeyUtils {
    /**
     * 自动生成唯一主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
