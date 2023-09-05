package com.example.demo.common;

import java.util.UUID;

/**
 * UUID生成类
 */
public class UUIDUtils {

    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }
}
