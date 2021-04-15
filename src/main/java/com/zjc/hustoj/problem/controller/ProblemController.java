package com.zjc.hustoj.problem.controller;

import com.zjc.hustoj.core.constant.MemoryFileOutputStream;
import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.xml.JAXBUtils;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import com.zjc.hustoj.problem.service.ProblemService;
import com.zjc.hustoj.problem.vo.*;
import com.zjc.hustoj.problem.xml.element.ProblemXmlBody;
import com.zjc.hustoj.problem.xml.element.ProblemXmlEntity;
import com.zjc.hustoj.problem.xml.element.testcase.TestCase;
import com.zjc.hustoj.problem.xml.element.testcase.TestCaseList;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
            ByteArrayOutputStream outputStream = problemService.exportByIds(ids);

//            File outputStream = new File(("/Users/david/Documents/ABC.xml"));
            return ServerResponse.file(outputStream);
//            return download("test.xml", outputStream);
        } catch (Exception e) {
            log.error("题目导出失败！", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/exportByRange")
    public ResponseEntity exportByRange(RangeInfo rangeInfo) {
        try {
            ByteArrayOutputStream outputStream = problemService.exportByRange(rangeInfo);
            return ServerResponse.file(outputStream);
        } catch (Exception e) {
            log.error("题目导出失败！", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    public static ResponseEntity<byte[]> download(String fileName, ByteArrayOutputStream byteOutPutStream) {
        //下载文件
        try {
            new ByteArrayOutputStream();
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = MediaType.APPLICATION_OCTET_STREAM;
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            // 文件名称
            headers.setContentDispositionFormData("attachment",
                    new String(fileName.getBytes("GBK"), "ISO8859-1"));
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(byteOutPutStream.toByteArray(), headers, HttpStatus.OK);
            return responseEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
