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

    /**
     * 将分页结果集转换成VO对象的结果集
     * @param pageDto 分页结果集
     * @param voClazz 目标VO类
     * @param <T>
     * @return
     * @throws ReflectiveOperationException
     */
    public static <T> Page<T> convertPage(Page<?> pageDto, Class<T> voClazz)
            throws ReflectiveOperationException {
        List<T> recordList = new ArrayList<>();
        Page<T> resultPage = new Page<>(pageDto.getCurrent(), pageDto.getSize(), pageDto.getTotal());
        for (Object obj : pageDto.getRecords()) {
            T vo = voClazz.newInstance();
            BeanUtil.copyProperties(obj, vo);
            recordList.add(vo);
        }
        resultPage.setRecords(recordList);
        return resultPage;
    }
}
