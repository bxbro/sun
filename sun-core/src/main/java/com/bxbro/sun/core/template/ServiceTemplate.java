package com.bxbro.sun.core.template;

import com.bxbro.sun.common.domain.BaseRequest;
import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.exception.SunException;

/**
 * Service层 模板类
 *
 * @author: dong
 * @date: 2023/2/11 22:55
 * @since: 1.0
 */
public final class ServiceTemplate {

    /**
     * 模板方法
     * @param req 通用请求
     * @param delegator 委托器
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T extends BaseRequest, R extends BaseResult> R doService(T req, ServiceDelegator<T,R> delegator) {
        R result;
        try {
            // 1.初始化
            result = delegator.initResult();

            // 2.校验入参
            delegator.paramValidate(req);

            // 3.执行service层逻辑
            result = delegator.doService(req);

        } catch (SunException ex) {

            result = delegator.outFailedResult(ex.getCode(), ex.getMsg());

        } finally {
            // todo 清除上下文
        }
        return result;
    }
}
