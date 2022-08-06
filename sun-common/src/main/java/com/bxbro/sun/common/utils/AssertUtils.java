package com.bxbro.sun.common.utils;

import com.bxbro.sun.common.enums.SystemEnum;
import com.bxbro.sun.common.exception.SunException;

/**
 * @author dong
 * @description TODO
 * @date 2022/7/31
 */
public class AssertUtils {

    public static void notNull(Object object) {
        if (object == null) {
            throw new SunException(SystemEnum.PARAMETER_EXCEPTION);
        }
    }

    public static void notNull(Object object, String msg) {
        if (object == null) {
            throw new SunException(msg);
        }
    }
}
