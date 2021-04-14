package com.zjc.hustoj.core.controller;


import com.zjc.hustoj.core.utils.WebUtils;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * 所有Controller的基类
 */
public abstract class AbstractBaseController {
    /**
     * "验证结果"参数名称
     */
    private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";
    // 请求中中的客户端类型 Key
    private static final String CLIENT_TYPE_KEY = "clienttype";

    @Resource
    private Validator validator;

    /**
     * 获得当前request
     *
     * @return
     */
    public HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        if (requestAttributes != null) {
            return ((ServletRequestAttributes) requestAttributes).getRequest();
        }
        return null;
    }

    /**
     * 设置属性到request中
     *
     * @param name  属性名称
     * @param value 属性值
     */
    public void $attr(String name, Object value) {
        getRequest().setAttribute(name, value);
    }

    /**
     * 数据验证
     *
     * @param target 验证对象
     * @param groups 验证组
     * @return 验证结果
     */
    protected boolean isValid(Object target, Class<?>... groups) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(target, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            requestAttributes
                    .setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
                            RequestAttributes.SCOPE_REQUEST);
            return false;
        }
    }

    /**
     * 数据验证
     *
     * @param type     类型
     * @param property 属性
     * @param value    值
     * @param groups   验证组
     * @return 验证结果
     */
    protected boolean isValid(Class<?> type, String property, Object value, Class<?>... groups) {
        Set<?> constraintViolations = validator.validateValue(type, property, value, groups);
        if (constraintViolations.isEmpty()) {
            return true;
        } else {
            RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
            requestAttributes
                    .setAttribute(CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
                            RequestAttributes.SCOPE_REQUEST);
            return false;
        }
    }


    /**
     * 获取请求头参数
     *
     * @param key key
     */
    protected String getRequestHeader(String key) {
        return getRequest().getHeader(key);
    }


    /**
     * 获取请求客户端ip
     */
    protected String getRequestIp() {
        return WebUtils.getRequestIp(getRequest());
    }

}