package com.bxbro.sun.common.tools.utils;

import cn.hutool.core.text.CharSequenceUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/5/13 21:31
 * @since: 1.0
 */
public class ListUtils {

    /**
     * 将String数组转换成 Long型列表
     * @param strArray
     * @return
     */
    public static List<Long> convertStr2Long(String[] strArray) {
        List<Long> resList = new ArrayList<>();
        for (String value : strArray) {
            if (CharSequenceUtil.isEmpty(value)) {
                continue;
            }
            Long valueLong = Long.valueOf(value);
            resList.add(valueLong);
        }
        return resList;
    }
}
