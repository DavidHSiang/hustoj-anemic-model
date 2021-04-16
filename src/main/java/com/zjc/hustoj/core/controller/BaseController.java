package com.zjc.hustoj.core.controller;

import com.zjc.hustoj.auth.utils.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Resource;

/**
 * @author David Hsiang
 * @date 2021/04/06/10:24 下午
 */
@Slf4j
public class BaseController extends AbstractBaseController {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Resource
    private TokenProvider tokenProvider;

    /**
     * 从 token 中解析出当前登录的用户 id
     */
    public String getCurrentUserId() {
        // 得到 token
        String token = getRequestToken();
        // 验证 token 是否有效
        return tokenProvider.getUserId(token);
    }

    /**
     * 从 token 中解析出验证码
     */
    public String getVerifyCode() {
        // 得到 token
        String token = getRequestToken();
        // 验证 token 是否有效
        return tokenProvider.getVerifyCode(token);
    }


    /**
     * 从 header 中获取token
     */
    public String getRequestToken() {
        // 得到请求头 ProxyAuthorization
        String token = getRequestHeader(AUTHORIZATION_HEADER);
        // 判断，若 token 为空，或者为开发环境，则返回空字符串
        if (StringUtils.isBlank(token)) {
            token = "";
        }
        return token;
    }
}
