package com.zjc.hustoj.privilege.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.contest.entity.ContestEntity;
import com.zjc.hustoj.contest.service.ContestService;
import com.zjc.hustoj.core.constant.Const;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.privilege.constant.Role;
import com.zjc.hustoj.privilege.dao.PrivilegeDao;
import com.zjc.hustoj.privilege.entity.PrivilegeEntity;
import com.zjc.hustoj.privilege.service.PrivilegeService;
import com.zjc.hustoj.privilege.vo.PrivilegeReqVo;
import com.zjc.hustoj.privilege.vo.PrivilegePageReqVo;
import com.zjc.hustoj.privilege.vo.PrivilegePageRespVo;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import com.zjc.hustoj.problem.service.ProblemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:48 上午
 */
@Service
public class PrivilegeServiceImpl extends ServiceImpl<PrivilegeDao, PrivilegeEntity> implements PrivilegeService {

    @Resource
    private ContestService contestService;

    @Resource
    private ProblemService problemService;

    @Override
    public PageUtils queryPage(PrivilegePageReqVo privilegePageReqVo) {
        IPage<PrivilegeEntity> page = this.page(
                Query.getPage(privilegePageReqVo),
                new QueryWrapper<PrivilegeEntity>()
                        .in("rightstr", Role.getRoleNameList())
                        .eq("defunct","N")
                        .like("user_id", privilegePageReqVo.getUserId())
                        .like("rightstr", privilegePageReqVo.getRightStr())
        );

        return new PageUtils(page, PrivilegePageRespVo.class);
    }

    @Override
    public void delete(PrivilegeReqVo privilegeReqVo) {
        UpdateWrapper updateWrapper = new UpdateWrapper<PrivilegeEntity>()
                .set("defunct", Const.IS_DELETE)
                .eq("user_id", privilegeReqVo.getUserId())
                .eq("rightstr", privilegeReqVo.getRightStr())
                .eq("defunct", Const.NOT_DELETE);
        this.update(updateWrapper);
    }

    @Override
    public void addRolePrivilege(PrivilegeReqVo privilegeReqVo) {
        if (!Role.getRoleNameList().contains(privilegeReqVo.getRightStr())){
            throw new ServiceException("不存在该权限类型");
        }

        String userId = privilegeReqVo.getUserId();
        String rightStr = privilegeReqVo.getRightStr();

        PrivilegeEntity entity = getPrivilege(userId, rightStr);
        if (entity != null){
            throw new ServiceException("权限已存在");
        }
        savePrivilege(userId, rightStr);
    }

    @Override
    public void addContestPrivilege(PrivilegeReqVo privilegeReqVo) {

        String userId = privilegeReqVo.getUserId();
        String rightStr = privilegeReqVo.getRightStr();

        ContestEntity contestEntity = contestService.getById(rightStr);
        if (contestEntity == null){
            throw new ServiceException("不存在该竞赛");
        }

        rightStr = "c" + rightStr;
        PrivilegeEntity entity = getPrivilege(userId, rightStr);
        if (entity != null){
            throw new ServiceException("权限已存在");
        }
        savePrivilege(userId, rightStr);
    }

    @Override
    public void addProblemPrivilege(PrivilegeReqVo privilegeReqVo) {
        String userId = privilegeReqVo.getUserId();
        String rightStr = privilegeReqVo.getRightStr();

        ProblemEntity problemEntity = problemService.getById(rightStr);
        if (problemEntity == null){
            throw new ServiceException("不存在该题目");
        }

        rightStr = "s" + rightStr;
        PrivilegeEntity entity = getPrivilege(userId, rightStr);
        if (entity != null){
            throw new ServiceException("权限已存在");
        }


        entity = new PrivilegeEntity();
        entity.setUserId(userId);
        entity.setRightStr(rightStr);
        entity.setDefunct(Const.NOT_DELETE);
        this.save(entity);
    }

    @Override
    public String getProblemEditor(Integer problemId) {
        QueryWrapper queryWrapper = new QueryWrapper<PrivilegeEntity>()
                .eq("rightstr", "p" + problemId)
                .eq("defunct", Const.NOT_DELETE);
        PrivilegeEntity entity = this.getOne(queryWrapper);
        if (entity == null){
            return null;
        }
        return entity.getUserId();
    }

    @Override
    public void setProblemEditor(String userId, Integer problemId) {
        if (this.getProblemEditor(problemId) == null && StringUtils.isNotEmpty(userId)){
            this.savePrivilege(userId, "p" + problemId);
        }
    }

    @Override
    public PrivilegeEntity getPrivilege(String userId,String rightStr){
        QueryWrapper queryWrapper = new QueryWrapper<PrivilegeEntity>()
                .eq("user_id", userId)
                .eq("rightstr", rightStr)
                .eq("defunct", Const.NOT_DELETE);
        return this.getOne(queryWrapper);
    }

    @Override
    public void savePrivilege(String userId,String rightStr){
        PrivilegeEntity entity = new PrivilegeEntity();
        entity.setUserId(userId);
        entity.setRightStr(rightStr);
        entity.setDefunct(Const.NOT_DELETE);
        this.save(entity);
    }


}
