package com.system.xysmartassistants.utils;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 获取Druid数据库加密类
 */
public class DruidGetKeyApplication {
    public static void main(String[] args) throws Exception {
        String password = "kyfYtdAQ7HwsynD3p0Y717AQFmB7Exdi";
        String[] arr = ConfigTools.genKeyPair(512);
        System.out.println("password:" + password);
        System.out.println("privateKey:" + arr[0]);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
    }
}
