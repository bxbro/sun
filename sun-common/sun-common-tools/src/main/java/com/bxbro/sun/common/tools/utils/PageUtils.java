package com.bxbro.sun.common.tools.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * @author: dong
 * @date: 2023/5/11 22:36
 * @since: 1.0
 */
public class PageUtils {

    public static <T> Page<T> convertPage(Page<?> pageDto, Class<T> voClazz) throws InstantiationException, IllegalAccessException {
        List<T> recordList = new ArrayList<>();
        Page<T> resultPage = new Page<>(pageDto.getCurrent(), pageDto.getSize(), pageDto.getTotal());
        for (Object obj : pageDto.getRecords()) {
            T vo = voClazz.newInstance();
            BeanUtil.copyProperties(obj, vo, true);
            recordList.add(vo);
        }
        resultPage.setRecords(recordList);
        return resultPage;
    }
}
