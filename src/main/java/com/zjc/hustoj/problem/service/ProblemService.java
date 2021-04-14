package com.zjc.hustoj.problem.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import com.zjc.hustoj.problem.vo.*;

import java.io.File;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:43 上午
 */
public interface ProblemService extends IService<ProblemEntity> {

    PageUtils queryPage(ProblemPageReqVo problemPageReqVo);

    ProblemPageRespVo updateUsable(Integer problemId);

    void delete(Integer problemId);

    ProblemDetailVo detail(Integer problemId);

    Integer saveOrUpdate(ProblemSaveVo problemDetailVo, String currentUserId);

    File exportByIds(List<Integer> ids);

    File exportByRange(RangeInfo<ProblemEntity> rangeInfo);
}
