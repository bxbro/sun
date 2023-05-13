package com.bxbro.sun.common.tools.utils;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/5/11 22:36
 * @since: 1.0
 */
public class PageUtils {

    public static <T> Page<T> convertPage(Page pageDto, T voClazz) {
        List<T> recordList = new ArrayList<>();
        Page<T> resultPage = new Page<>(pageDto.getCurrent(), pageDto.getSize(), pageDto.getTotal());
        pageDto.getRecords().forEach(e->{
            // new一个voClazz实例
//            T o = (T) voClazz.getClass().newInstance();

            // BeanUtil.copyProperties(e, );
            // recordList.add()
        });
        resultPage.setRecords(recordList);
        return resultPage;
    }
}
