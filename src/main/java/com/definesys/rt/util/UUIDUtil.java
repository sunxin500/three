package com.definesys.rt.util;

import java.util.UUID;

/**
 * UUID 工具类
 */
public class UUIDUtil {

    /**
     * 获取指定位数的随机字符串
     * @param size
     * @return
     */
    public static String getStringUUID(int size) {
        return UUID.randomUUID().toString().substring(0,size);
    }

    /**
     * 获取指定位数的纯数字
     * @param size
     * @return
     */
    public static String getIntegerUUID(int size) {
        Integer hashCode = UUID.randomUUID().toString().hashCode();
        String code = String.valueOf(hashCode < 0 ? -hashCode : hashCode).substring(0,size);
        return code;
    }
}
