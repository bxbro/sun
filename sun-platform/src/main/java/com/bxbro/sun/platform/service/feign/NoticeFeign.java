package com.bxbro.sun.platform.service.feign;

import com.bxbro.sun.common.base.domain.dto.CommonMessageDTO;
import com.bxbro.sun.common.base.domain.dto.MailDTO;
import com.bxbro.sun.core.model.BaseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "sun-notice")
@Component
public interface NoticeFeign {

    /**
     * 发送通知
     * @param commonMessageDTO
     * @return
     */
    @PostMapping("/notice/api/v1")
    BaseResult sendMessage(@RequestBody CommonMessageDTO commonMessageDTO);

}
