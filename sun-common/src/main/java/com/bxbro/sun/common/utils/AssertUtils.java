package com.bxbro.sun.common.utils;

import com.bxbro.sun.common.enums.SystemEnum;
import com.bxbro.sun.common.exception.SunException;

import java.util.Collection;

/**
 * @author dong
 * @description 断言工具类
 * @date 2022/7/31
 */
public class AssertUtils {

    private static final String DEFAULT_MSG = "系统内部错误!";

    public static void notNull(Object object) {
        notNull(object, DEFAULT_MSG);
    }

    public static void notNull(Object object, String msg) {
        if (object == null) {
            throw new SunException(SystemEnum.FAIL.getCode(), msg);
        }
    }

    public static void condition(boolean condition, String msg) {
        if (condition) {
            throw new SunException(SystemEnum.FAIL.getCode(), msg);
        }
    }

    public static void hasElement(Collection collection, String msg) {
        if (collection.isEmpty()) {
            throw new SunException(SystemEnum.FAIL.getCode(), msg);
        }
    }
}
