package com.zjc.hustoj.contest.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.contest.entity.ContestEntity;
import com.zjc.hustoj.contest.vo.ContestPageReqVo;
import com.zjc.hustoj.contest.vo.ContestPageRespVo;
import com.zjc.hustoj.core.utils.PageUtils;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:43 上午
 */
public interface ContestService extends IService<ContestEntity> {

    PageUtils queryPage(ContestPageReqVo contestPageReqVo);

    ContestPageRespVo updateUsable(Integer contestId);

    ContestPageRespVo updateOpenable(Integer contestId);
}
