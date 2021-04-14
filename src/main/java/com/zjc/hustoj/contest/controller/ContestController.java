package com.zjc.hustoj.contest.controller;

import com.zjc.hustoj.contest.service.ContestService;
import com.zjc.hustoj.contest.vo.ContestPageReqVo;
import com.zjc.hustoj.contest.vo.ContestPageRespVo;
import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author David Hsiang
 * @date 2021/04/07/1:41 下午
 */
@RestController
@RequestMapping("/contest")
@Slf4j
public class ContestController extends BaseController {

    @Resource
    private ContestService contestService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid ContestPageReqVo contestPageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = contestService.queryPage(contestPageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/updateUsable/{id}")
    public ResponseEntity updateUsable(@PathVariable("id") Integer contestId){
        try {
            ContestPageRespVo contestPageRespVo = contestService.updateUsable(contestId);
            return ServerResponse.ok("更新成功", contestPageRespVo);
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/updateOpenable/{id}")
    public ResponseEntity updateOpenable(@PathVariable("id") Integer contestId){
        try {
            ContestPageRespVo contestPageRespVo = contestService.updateOpenable(contestId);
            return ServerResponse.ok("更新成功", contestPageRespVo);
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }
}
