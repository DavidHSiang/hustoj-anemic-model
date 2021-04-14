package com.zjc.hustoj.privilege.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author David Hsiang
 * @date 2021/04/07/5:21 下午
 */
@Data
public class PrivilegePageRespVo {

    /**
     * id
     */
    private String userId;

    /**
     * 用户角色
     */
    private String rightStr;

}
