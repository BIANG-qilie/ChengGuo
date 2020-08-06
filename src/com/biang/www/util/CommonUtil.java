package com.biang.www.util;

public class CommonUtil {
    public static String getRandomNum()
    {
        int ran = (int)( Math.random()*9000) +1000 ;
        return String.valueOf(ran) ;
    }
}
