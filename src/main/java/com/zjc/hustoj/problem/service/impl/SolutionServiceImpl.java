package com.zjc.hustoj.problem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.problem.constant.CodeLanguage;
import com.zjc.hustoj.problem.constant.RunResult;
import com.zjc.hustoj.problem.dao.SolutionDao;
import com.zjc.hustoj.problem.entity.SolutionEntity;
import com.zjc.hustoj.problem.entity.SourceCodeEntity;
import com.zjc.hustoj.problem.service.SolutionService;
import com.zjc.hustoj.problem.service.SourceCodeService;
import com.zjc.hustoj.problem.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/12/4:18 下午
 */
@Service
public class SolutionServiceImpl extends ServiceImpl<SolutionDao, SolutionEntity> implements SolutionService {

    @Resource
    private SourceCodeService sourceCodeService;

    @Override
    public SolutionCountVo getSolutionCountByUserId(String userId) {
        SolutionCountVo solutionCountVo = baseMapper.getSolutionCountByUserId(userId)
                ;
        List<CountByHalfMonthVo> countByHalfMonthList = baseMapper.getCountByHalfMonth(userId);
        solutionCountVo.setCountByHalfMonth(countByHalfMonthList);

        List<SolvedProblemVo> solvedProblemList = baseMapper.getSolvedProblemByUserId(userId);
        solutionCountVo.setSolvedProblemsList(solvedProblemList);


        return solutionCountVo;
    }

    @Override
    public PageUtils queryPage(SolutionPageReqVo solutionPageReqVo) {
        QueryWrapper queryWrapper = new QueryWrapper<SolutionEntity>();

        Integer problemId = solutionPageReqVo.getProblemId();
        Integer language = solutionPageReqVo.getLanguage();
        Integer result = solutionPageReqVo.getResult();
        String userId = solutionPageReqVo.getUserId();

        if (problemId != null){
            queryWrapper.eq("problem_id", problemId);
        }

        if (!CodeLanguage.ALL.getLanguageId().equals(language)){
            queryWrapper.eq("language", language);
        }

        if (!RunResult.ALL.getResultCode().equals(result)){
            queryWrapper.eq("result", result);
        }

        if (StringUtils.isNotEmpty(userId)){
            queryWrapper.like("user_id", solutionPageReqVo.getUserId());
        }

        IPage<SolutionEntity> page = this.page(
                Query.getPage(solutionPageReqVo),
                queryWrapper
        );

        return new PageUtils(page, SolutionDetailVo.class);
    }

    @Override
    public SolutionDetailVo getDetailWithSource(Integer solutionId) {
        SolutionEntity solutionEntity = this.getById(solutionId);
        SolutionDetailVo solutionDetailVo = BeanUtils.copyProperties(solutionEntity, SolutionDetailVo.class);
        SourceCodeEntity sourceCodeEntity = sourceCodeService.getById(solutionId);
        return BeanUtils.copyProperties(sourceCodeEntity, solutionDetailVo);
    }
}
