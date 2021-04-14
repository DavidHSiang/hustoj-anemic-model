package com.zjc.hustoj.problem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.core.constant.Const;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.core.utils.xml.JAXBUtils;
import com.zjc.hustoj.privilege.service.PrivilegeService;
import com.zjc.hustoj.problem.dao.ProblemDao;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import com.zjc.hustoj.problem.service.ProblemService;
import com.zjc.hustoj.problem.vo.*;
import com.zjc.hustoj.problem.xml.element.ProblemXmlBody;
import com.zjc.hustoj.problem.xml.element.ProblemXmlEntity;
import com.zjc.hustoj.problem.xml.element.testcase.TestCase;
import com.zjc.hustoj.problem.xml.element.testcase.TestCaseList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:44 上午
 */
@Service
public class ProblemServiceImpl extends ServiceImpl<ProblemDao, ProblemEntity> implements ProblemService {

    @Resource
    private PrivilegeService privilegeService;

    @Override
    public PageUtils queryPage(ProblemPageReqVo problemPageReqVo) {

        IPage<ProblemEntity> page = this.page(
                Query.getPage(problemPageReqVo),
                new QueryWrapper<ProblemEntity>()
        );

        return new PageUtils(page, ProblemPageRespVo.class);
    }

    @Override
    public ProblemPageRespVo updateUsable(Integer problemId) {
        if (problemId == null){
            throw new ServiceException("problemId 为空");
        }

        ProblemEntity problemEntity = this.getById(problemId);

        if (problemEntity == null){
            throw new ServiceException("问题不存在");
        }

        String isDeleted = problemEntity.getDefunct();
        if (Const.IS_DELETE.equals(isDeleted)){
            problemEntity.setDefunct(Const.NOT_DELETE);
        }else {
            problemEntity.setDefunct(Const.IS_DELETE);
        }

        this.updateById(problemEntity);
        return BeanUtils.copyProperties(problemEntity, ProblemPageRespVo.class);
    }

    @Override
    public void delete(Integer problemId) {
        this.removeById(problemId);
    }

    @Override
    public ProblemDetailVo detail(Integer problemId) {
        ProblemEntity problemEntity = this.getById(problemId);
        ProblemDetailVo problemDetailVo = BeanUtils.copyProperties(problemEntity, ProblemDetailVo.class);

        String problemEditor = privilegeService.getProblemEditor(problemId);
        problemDetailVo.setProblemEditor(problemEditor);

        return problemDetailVo;
    }

    @Override
    public Integer saveOrUpdate(ProblemSaveVo problemDetailVo, String currentUserId) {
        ProblemEntity problemEntity = BeanUtils.copyProperties(problemDetailVo, ProblemEntity.class);
        if (problemEntity.getProblemId() == null){
            problemEntity.setInDate(new Date());
            problemEntity.setDefunct(Const.NOT_DELETE);
        }
        this.saveOrUpdate(problemEntity);
        privilegeService.setProblemEditor(currentUserId, problemEntity.getProblemId());
        return problemEntity.getProblemId();
    }

    @Override
    public File exportByIds(List<Integer> ids) {
        String fileName = StringUtils.join(ids.toArray(), "+") + ".xml";
        List<ProblemEntity> problemEntities = this.list(new QueryWrapper<ProblemEntity>().in("problem_id", ids));

        List<ProblemXmlEntity> problemXmlEntities = BeanUtils.convertList(problemEntities, ProblemXmlEntity.class);

        //todo 添加测试用例

        ProblemXmlBody problemXmlBody = ProblemXmlBody.create(problemXmlEntities);

        return JAXBUtils.generateXML(problemXmlBody, fileName);
    }

    @Override
    public File exportByRange(RangeInfo<ProblemEntity> rangeInfo) {
        String fileName = rangeInfo.getGeInfo() + "-" + rangeInfo.getLeInfo() + ".xml";
        List<ProblemEntity> problemEntities = this.list(rangeInfo.getWrapper("problem_id"));

        List<ProblemXmlEntity> problemXmlEntities = BeanUtils.convertList(problemEntities, ProblemXmlEntity.class);

        //todo 添加测试用例

        ProblemXmlBody problemXmlBody = ProblemXmlBody.create(problemXmlEntities);

        return JAXBUtils.generateXML(problemXmlBody, fileName);
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
