package com.zjc.hustoj.auth.controller;

import com.zjc.hustoj.auth.service.AuthService;
import com.zjc.hustoj.privilege.service.PrivilegeService;
import com.zjc.hustoj.auth.utils.TokenProvider;
import com.zjc.hustoj.auth.vo.LoginReqVo;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author David Hsiang
 * @date 2021/04/06/10:02 下午
 */
@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController extends BaseController {

    @Resource
    private AuthService authService;

    @Resource
    private PrivilegeService privilegeService;

    @Resource
    public TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginReqVo loginReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            UserPageRespVo userPageRespVo = authService.login(loginReqVo, getVerifyCode());
            String token = tokenProvider.createUserIdToken(userPageRespVo.getUserId());
            return ServerResponse.header(AUTHORIZATION_HEADER, token).ok("登录成功", userPageRespVo);
        } catch (Exception e) {
            log.error("登录失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/test")
    public ResponseEntity test(){

        String userId = getCurrentUserId();
        return ServerResponse.ok("登录成功", userId);
    }



}
