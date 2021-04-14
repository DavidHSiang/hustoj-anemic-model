package com.zjc.hustoj.problem.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.core.constant.Const;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.privilege.service.PrivilegeService;
import com.zjc.hustoj.problem.dao.ProblemDao;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import com.zjc.hustoj.problem.service.ProblemService;
import com.zjc.hustoj.problem.vo.ProblemDetailVo;
import com.zjc.hustoj.problem.vo.ProblemPageReqVo;
import com.zjc.hustoj.problem.vo.ProblemPageRespVo;
import com.zjc.hustoj.problem.vo.ProblemSaveVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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

}
