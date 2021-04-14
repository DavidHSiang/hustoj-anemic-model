package com.zjc.hustoj.user.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.excel.ExcelFile;
import com.zjc.hustoj.user.service.UserService;
import com.zjc.hustoj.user.vo.ResetPasswordVo;
import com.zjc.hustoj.user.vo.UserDetailVo;
import com.zjc.hustoj.user.vo.UserPageReqVo;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author David Hsiang
 * @date 2021/04/07/1:41 下午
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid UserPageReqVo userPageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = userService.queryPage(userPageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/updateUsable/{id}")
    public ResponseEntity updateUsable(@PathVariable("id") String userId){
        try {
            UserPageRespVo userPageRespVo = userService.updateUsable(userId);
            return ServerResponse.ok("更新成功", userPageRespVo);
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/resetPassword")
    public ResponseEntity resetPassword(@RequestBody @Valid ResetPasswordVo resetPasswordVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            userService.resetPassword(resetPasswordVo);
            return ServerResponse.okMsg("设置成功");
        } catch (Exception e) {
            log.error("设置失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/registerByExcel")
    public ResponseEntity registerBatchByExcel(ExcelFile file) {
        try {
            userService.registerBatch(file, getRequestIp());
            return ServerResponse.okMsg("批量注册成功");
        } catch (Exception e) {
            log.error("批量注册失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable("id") String userId){
        try {
            UserDetailVo userDetailVo = userService.detail(userId);
            return ServerResponse.ok("更新成功", userDetailVo);
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

}
