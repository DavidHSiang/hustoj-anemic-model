package com.zjc.hustoj.problem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.problem.entity.SolutionEntity;
import com.zjc.hustoj.problem.vo.SolutionCountVo;
import com.zjc.hustoj.problem.vo.SolutionDetailVo;
import com.zjc.hustoj.problem.vo.SolutionPageReqVo;

/**
 * @author David Hsiang
 * @date 2021/04/12/4:18 下午
 */
public interface SolutionService extends IService<SolutionEntity> {
    SolutionCountVo getSolutionCountByUserId(String userId);

    PageUtils queryPage(SolutionPageReqVo solutionPageReqVo);

    SolutionDetailVo getDetailWithSource(Integer solutionId);
}
