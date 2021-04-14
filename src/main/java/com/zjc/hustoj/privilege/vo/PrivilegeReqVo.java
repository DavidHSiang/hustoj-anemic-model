package com.zjc.hustoj.privilege.vo;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author David Hsiang
 * @date 2021/04/07/7:48 下午
 */
@Data
public class PrivilegeReqVo {
    /**
     * id
     */
    @NotEmpty
    private String userId;

    /**
     * 用户角色
     */
    @NotEmpty
    private String rightStr;
}
