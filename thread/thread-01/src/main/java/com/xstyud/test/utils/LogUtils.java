package com.xstyud.test.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zurun
 * @date 2018/8/25 23:08:47
 */
public class LogUtils {
    private final static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    public static void log(String message) {
        System.out.println(formatter.format(new Date()) + " - " + message);
    }
}
