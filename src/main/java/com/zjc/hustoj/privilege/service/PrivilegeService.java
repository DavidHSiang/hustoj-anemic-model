package com.zjc.hustoj.privilege.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.privilege.entity.PrivilegeEntity;
import com.zjc.hustoj.privilege.vo.PrivilegeReqVo;
import com.zjc.hustoj.privilege.vo.PrivilegePageReqVo;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:48 上午
 */
public interface PrivilegeService extends IService<PrivilegeEntity> {
    PageUtils queryPage(PrivilegePageReqVo privilegePageReqVo);

    void delete(PrivilegeReqVo privilegeReqVo);

    void addRolePrivilege(PrivilegeReqVo privilegeReqVo);

    void addContestPrivilege(PrivilegeReqVo privilegeReqVo);

    void addProblemPrivilege(PrivilegeReqVo privilegeReqVo);

    String getProblemEditor(Integer problemId);

    void setProblemEditor(String userId, Integer problemId);

    PrivilegeEntity getPrivilege(String userId,String rightStr);

    void savePrivilege(String userId,String rightStr);
}
