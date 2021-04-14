package com.zjc.hustoj.problem.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.xml.JAXBUtils;
import com.zjc.hustoj.problem.service.ProblemService;
import com.zjc.hustoj.problem.vo.ProblemDetailVo;
import com.zjc.hustoj.problem.vo.ProblemPageReqVo;
import com.zjc.hustoj.problem.vo.ProblemPageRespVo;
import com.zjc.hustoj.problem.vo.ProblemSaveVo;
import com.zjc.hustoj.problem.xml.element.ProblemXmlBody;
import com.zjc.hustoj.problem.xml.element.ProblemXmlEntity;
import com.zjc.hustoj.problem.xml.element.testcase.TestCase;
import com.zjc.hustoj.problem.xml.element.testcase.TestCaseList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @GetMapping(value = "/export")
    public void export(HttpServletResponse response) {
        try {
            response.setHeader("Content-Disposition", "attachment; filename=" + "test.xml" + ".bpmn20.xml");
            response.setContentType("application/octet-stream");
            JAXBUtils.generateXML(testData(), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            log.info("Error writing file to output stream.", ex);
            throw new RuntimeException("IOError writing file to output stream");
        }

    }

    private ProblemXmlBody testData(){
        List<ProblemXmlEntity> problemXmlEntities = new ArrayList<>();

        ProblemXmlEntity problem_1 = new ProblemXmlEntity();
        problem_1.setTitle("大整数减法（高精度-低精度）111");
        problem_1.setTimeLimit(new BigDecimal(1.000));
        problem_1.setMemoryLimit(128);
        problem_1.setDescription("<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">求一个大的正整数减另一个小的正整数的差。</span>");
        problem_1.setInput("<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">共2行，第1行是被减数a，第2行是减数b(a &gt; b)。大整数不超过200位，小<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">整数不超过10位，</span>不会有多余的前导零。</span>");
        problem_1.setOutput("<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">一行，即所求的差。</span>");
        problem_1.setSampleInput("9999999999999999999999999999999999999\n99999");
        problem_1.setSampleOutput("9999999999999999999999999999999900000");

        TestCaseList testDataElements = new TestCaseList();

        TestCase testData1 = new TestCase("testInputValue111", "testOutputValue111");
        TestCase testData2 = new TestCase("testInputValue222", "testOutputValue222");
        TestCase testData3 = new TestCase("test\nInputValue333", "testOutputValue333");

        testDataElements.add(testData1);
        testDataElements.add(testData2);
        testDataElements.add(testData3);

        problem_1.setDataList(testDataElements);

        problem_1.setHint("123123123hint");

        ProblemXmlEntity problem_2 = new ProblemXmlEntity();
        problem_2.setTitle("大整数减法（高精度-低精度）222");
        problem_2.setTimeLimit(new BigDecimal(1.000));
        problem_2.setMemoryLimit(128);
        problem_2.setDescription("<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">求一个大的正整数减另一个小的正整数的差。</span>");
        problem_2.setInput("<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">共2行，第1行是被减数a，第2行是减数b(a &gt; b)。大整数不超过200位，小<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">整数不超过10位，</span>不会有多余的前导零。</span>");
        problem_2.setOutput("<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">一行，即所求的差。</span>");
        problem_2.setSampleInput("9999999999999999999999999999999999999\n99999");
        problem_2.setSampleOutput("9999999999999999999999999999999900000");
        problem_2.setDataList(testDataElements);
        problem_2.setHint("123123123hint");

        problemXmlEntities.add(problem_1);
        problemXmlEntities.add(problem_2);
        ProblemXmlBody problemXmlBody = new ProblemXmlBody();
        problemXmlBody.setEntities(problemXmlEntities);
        return problemXmlBody;
    }
}
