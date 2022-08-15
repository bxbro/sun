package com.bxbro.sun.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

/**
 * @Description: IO工具类
 * @Author: dong
 * @Date 2022/8/14 18:46
 * @Since 1.0
 */
public class IoUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(IoUtils.class);

    /**
     * 关闭多个流
     */
    public static void close(Closeable... closeableList) {
        for (Closeable closeable : closeableList) {
            try {
                if(null != closeable) {
                    closeable.close();
                }
            } catch (Exception e) {
                LOGGER.error("关闭流发生异常:{}", e);
            }
        }
    }
}
