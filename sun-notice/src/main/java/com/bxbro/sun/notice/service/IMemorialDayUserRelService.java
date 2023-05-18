package com.bxbro.sun.notice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bxbro.sun.common.base.domain.entity.MemorialDayUserRel;
import com.bxbro.sun.notice.domain.dto.MemorialDayUserDTO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-13
 */
public interface IMemorialDayUserRelService extends IService<MemorialDayUserRel> {

    /**
     * 根据纪念日id查询该纪念日应该通知哪些用户
     * @param dayId
     * @return
     */
    List<MemorialDayUserDTO> listUsersByDayId(Long dayId);
}
