package com.bxbro.sun.core.template;

import com.bxbro.sun.common.domain.BaseRequest;
import com.bxbro.sun.common.domain.BaseResult;
import com.bxbro.sun.common.enums.SystemEnum;
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
    public static <T extends BaseRequest, R extends BaseResult> R doService(T req, ServiceDelegator<T, R> delegator) {
        // 1.初始化
        R result;
        try {
            // 2.校验入参
            delegator.paramValidate(req);
            // 3.执行service层逻辑
            result = delegator.doService(req);
        } catch (SunException ex) {
            result = delegator.initResult();
            // 构建失败的返回结果
            delegator.buildFailedResult(result, ex.getCode(), ex.getMsg());
        } catch (Throwable throwable) {
            result = delegator.initResult();
            delegator.buildFailedResult(result, SystemEnum.FAIL.getCode(), "System Error.");
        } finally {
            // todo 清除上下文
        }
        return result;
    }
}
