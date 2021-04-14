package com.zjc.hustoj.auth.service;

import com.zjc.hustoj.auth.vo.LoginReqVo;
import com.zjc.hustoj.user.vo.UserPageRespVo;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:49 上午
 */
public interface AuthService {
    UserPageRespVo login(LoginReqVo loginReqVo, String verifyCode);
}
