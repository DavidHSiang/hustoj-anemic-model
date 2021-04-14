package com.zjc.hustoj.user.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.user.service.LoginLogService;
import com.zjc.hustoj.user.service.UserService;
import com.zjc.hustoj.user.vo.LoginLogPageReqVo;
import com.zjc.hustoj.user.vo.UserPageReqVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author David Hsiang
 * @date 2021/04/12/2:59 下午
 */
@RestController
@RequestMapping("/log/login/")
@Slf4j
public class LoginLogController extends BaseController {

    @Resource
    private LoginLogService loginLogService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid LoginLogPageReqVo loginLogPageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = loginLogService.queryPage(loginLogPageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

}
