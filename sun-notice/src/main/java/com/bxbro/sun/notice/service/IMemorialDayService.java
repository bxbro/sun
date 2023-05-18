package com.bxbro.sun.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bxbro.sun.common.base.domain.entity.MemorialDay;
import com.bxbro.sun.notice.domain.dto.MemorialDayDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-11
 */
public interface IMemorialDayService extends IService<MemorialDay> {
    /**
     * 查询所有纪念日
     * @return
     */
    List<MemorialDayDTO> listAll();
}
