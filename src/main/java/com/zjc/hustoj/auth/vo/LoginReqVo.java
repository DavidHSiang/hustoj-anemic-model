package com.zjc.hustoj.auth.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * 用户登录 Vo
 * @author David Hsiang
 * @date 2021/04/06/10:14 下午
 */
@Data
public class LoginReqVo {

    /**
     * 用户名
     */
    @NotEmpty
    private String userId;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 验证码
     */
    @NotEmpty
    private String verifyCode;
}
