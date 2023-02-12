package com.bxbro.sun.core.template;

import com.bxbro.sun.common.domain.BaseRequest;
import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.exception.SunException;

/**
 * TODO
 *
 * @author: dong
 * @date: 2023/2/11 22:43
 * @since: 1.0
 */
public interface ServiceDelegator<T extends BaseRequest, R extends BaseResult> {

    /**
     * 初始化返回结果
     * @return
     */
    R initResult();

    /**
     * 入参校验
     * @param req
     * @throws SunException
     */
    void paramValidate(T req) throws SunException;

    /**
     * 执行服务
     * @param req
     * @return
     * @throws SunException
     */
    R doService(T req) throws SunException;

    /**
     * 输出失败的返回结果
     * @param errorMsg
     * @return
     */
    default R outFailedResult(Integer errorCode, String errorMsg) {
        R r = initResult();
        r.setCode(errorCode);
        r.setMsg(errorMsg);
        r.setData(null);
        return r;
    }
}