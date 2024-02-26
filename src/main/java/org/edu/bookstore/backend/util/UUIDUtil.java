package org.edu.bookstore.backend.util;

import java.util.UUID;

/**
 * 全局UUID生成器，返回32字节的UUID。
 */
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
