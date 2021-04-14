package com.zjc.hustoj.privilege.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:50 上午
 */
@Data
@TableName("privilege")
public class PrivilegeEntity implements Serializable {

    private static final long serialVersionUID = -8785321907571105645L;

    /**
     * id
     */
    private String userId;

    /**
     * 用户角色
     */
    @TableField(value = "rightstr")
    private String rightStr;

    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;
}
