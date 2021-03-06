package com.zjc.hustoj.problem.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.constant.ServerResponse.ExportInputStream;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.problem.service.ProblemService;
import com.zjc.hustoj.problem.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:55 下午
 */
@RestController
@RequestMapping("/problem")
@Slf4j
public class ProblemController extends BaseController {

    @Resource
    private ProblemService problemService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid ProblemPageReqVo problemPageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = problemService.queryPage(problemPageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/updateUsable/{id}")
    public ResponseEntity updateUsable(@PathVariable("id") Integer problemId){
        try {
            ProblemPageRespVo problemPageRespVo = problemService.updateUsable(problemId);
            return ServerResponse.ok("更新成功", problemPageRespVo);
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer problemId){
        try {
            problemService.delete(problemId);
            return ServerResponse.okMsg("删除成功");
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable("id") Integer problemId){
        try {
            ProblemDetailVo problemDetailVo = problemService.detail(problemId);
            return ServerResponse.ok("查询成功",  problemDetailVo);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity saveOrUpdate(@RequestBody @Valid ProblemSaveVo problemDetailVo){
        try {
            String currentUserId = this.getCurrentUserId();
            Integer problemId = problemService.saveOrUpdate(problemDetailVo, currentUserId);
            return ServerResponse.ok("新增/修改成功", problemId);
        } catch (Exception e) {
            log.error("新增/修改失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/exportByIds")
    public ResponseEntity exportByIds(@RequestBody List<Integer> ids) {
        try {
            ExportInputStream inputStream = problemService.exportByIds(ids);
            return ServerResponse.file(inputStream);
        } catch (Exception e) {
            log.error("题目导出失败！", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/exportByRange")
    public ResponseEntity exportByRange(@RequestBody RangeInfo rangeInfo) {
        try {
            ExportInputStream inputStream = problemService.exportByRange(rangeInfo);
            return ServerResponse.file(inputStream);
        } catch (Exception e) {
            log.error("题目导出失败！", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

}
