package com.bxbro.sun.notice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bxbro.sun.common.base.domain.entity.MemorialDayUserRel;
import com.bxbro.sun.notice.domain.dto.MemorialDayUserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-13
 */
@Repository
public interface MemorialDayUserRelMapper extends BaseMapper<MemorialDayUserRel> {

    /**
     * 根据纪念日id查询该纪念日应该通知哪些用户
     * @param dayId
     * @return
     */
    List<MemorialDayUserDto> listUsersByDayId(@Param("dayId") Long dayId);
}
