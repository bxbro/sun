package com.bxbro.sun.core.template;

import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.exception.SunException;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/2/13 18:44
 * @since: 1.0
 */
public interface ServiceDelegatorNoRequest<R extends BaseResult> {

    /**
     * 初始化返回结果
     * @return
     */
    R initResult();

    /**
     * 执行服务
     * @return
     * @throws SunException
     */
    R doService() throws SunException;

    /**
     * 构建失败的返回结果
     * @param errorMsg
     * @return
     */
    default void buildFailedResult(R result, Integer errorCode, String errorMsg) {
        result.setCode(errorCode);
        result.setMsg(errorMsg);
        result.setData(null);
    }
}
