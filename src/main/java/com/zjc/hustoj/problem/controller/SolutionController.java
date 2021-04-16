package com.zjc.hustoj.problem.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.problem.constant.CodeLanguage;
import com.zjc.hustoj.problem.constant.RunResult;
import com.zjc.hustoj.problem.service.SolutionService;
import com.zjc.hustoj.problem.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:55 下午
 */
@RestController
@RequestMapping("/solution")
@Slf4j
public class SolutionController extends BaseController {

    @Resource
    private SolutionService solutionService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid SolutionPageReqVo solutionPageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = solutionService.queryPage(solutionPageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/language-list")
    public ResponseEntity getCodeLanguageList(){
        return ServerResponse.ok(Arrays.asList(CodeLanguage.values()));
    }

    @GetMapping("/result-list")
    public ResponseEntity getRunResultList(){
        return ServerResponse.ok(Arrays.asList(RunResult.values()));
    }

    @GetMapping("/source/{solution-id}")
    public ResponseEntity getDetailWithSource(@PathVariable("solution-id") Integer solutionId){
        try {
            SolutionDetailVo solutionDetailVo = solutionService.getDetailWithSource(solutionId);
            return ServerResponse.ok(solutionDetailVo);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }
}
