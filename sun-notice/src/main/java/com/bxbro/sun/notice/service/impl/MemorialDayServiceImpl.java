package com.bxbro.sun.notice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bxbro.sun.common.base.domain.entity.MemorialDay;
import com.bxbro.sun.common.base.enums.LogicEnum;
import com.bxbro.sun.notice.domain.dto.MemorialDayDto;
import com.bxbro.sun.notice.mapper.MemorialDayMapper;
import com.bxbro.sun.notice.service.IMemorialDayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  纪念日 服务实现类
 * </p>
 *
 * @author auto-generator
 * @since 2023-05-11
 */
@Service
public class MemorialDayServiceImpl extends ServiceImpl<MemorialDayMapper, MemorialDay> implements IMemorialDayService {

    @Resource
    private MemorialDayMapper memorialDayMapper;


    @Override
    public List<MemorialDayDto> listAll() {
        LambdaQueryWrapper<MemorialDay> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(MemorialDay::getDelFlag, LogicEnum.UNDELETE);
        List<MemorialDay> memorialDayList = memorialDayMapper.selectList(queryWrapper);
        return BeanUtil.copyToList(memorialDayList, MemorialDayDto.class);
    }
}
