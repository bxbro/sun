package com.bxbro.sun.notice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bxbro.sun.common.base.domain.entity.MemorialDayUserRel;
import com.bxbro.sun.notice.domain.dto.MemorialDayUserDto;
import com.bxbro.sun.notice.mapper.MemorialDayUserRelMapper;
import com.bxbro.sun.notice.service.IMemorialDayUserRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-13
 */
@Service
public class MemorialDayUserRelServiceImpl extends ServiceImpl<MemorialDayUserRelMapper, MemorialDayUserRel> implements IMemorialDayUserRelService {

    @Resource
    private MemorialDayUserRelMapper memorialDayUserRelMapper;

    @Override
    public List<MemorialDayUserDto> listUsersByDayId(Long dayId) {
        return memorialDayUserRelMapper.listUsersByDayId(dayId);
    }
}
