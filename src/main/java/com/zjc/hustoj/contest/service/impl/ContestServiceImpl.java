package com.zjc.hustoj.contest.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.contest.dao.ContestDao;
import com.zjc.hustoj.contest.entity.ContestEntity;
import com.zjc.hustoj.contest.service.ContestService;
import com.zjc.hustoj.contest.vo.ContestPageReqVo;
import com.zjc.hustoj.contest.vo.ContestPageRespVo;
import com.zjc.hustoj.core.constant.Const;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.user.entity.UserEntity;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:44 上午
 */
@Service
public class ContestServiceImpl extends ServiceImpl<ContestDao, ContestEntity> implements ContestService {
    @Override
    public PageUtils queryPage(ContestPageReqVo contestPageReqVo) {

        IPage<ContestEntity> page = this.page(
                Query.getPage(contestPageReqVo),
                new QueryWrapper<ContestEntity>()
        );

        return new PageUtils(page, ContestPageRespVo.class);
    }

    @Override
    public ContestPageRespVo updateUsable(Integer contestId) {
        if (contestId == null){
            throw new ServiceException("contestId 为空");
        }

        ContestEntity contestEntity = this.getById(contestId);

        if (contestEntity == null){
            throw new ServiceException("竞赛不存在");
        }

        String isDeleted = contestEntity.getDefunct();
        if (Const.IS_DELETE.equals(isDeleted)){
            contestEntity.setDefunct(Const.NOT_DELETE);
        }else {
            contestEntity.setDefunct(Const.IS_DELETE);
        }

        this.updateById(contestEntity);
        return BeanUtils.copyProperties(contestEntity, ContestPageRespVo.class);
    }

    @Override
    public ContestPageRespVo updateOpenable(Integer contestId) {
        if (contestId == null){
            throw new ServiceException("contestId 为空");
        }

        ContestEntity contestEntity = this.getById(contestId);

        if (contestEntity == null){
            throw new ServiceException("竞赛不存在");
        }

        Integer isPrivate = contestEntity.getPrivation();
        if (Const.IS_PRIVATE.equals(isPrivate)){
            contestEntity.setPrivation(Const.IS_PUBLIC);
        }else {
            contestEntity.setPrivation(Const.IS_PRIVATE);
        }

        this.updateById(contestEntity);
        return BeanUtils.copyProperties(contestEntity, ContestPageRespVo.class);
    }


}
