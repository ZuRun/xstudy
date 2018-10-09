package cn.zull.study.basis.utils;

import java.util.UUID;

/**
 * @author zurun
 * @date 2018/10/6 01:34:29
 */
public class UuidUtils {
    public static String simpleUuid() {
        return UUID.randomUUID().toString();
    }
}
