package com.zjc.hustoj.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:00 下午
 */
@Data
@TableName("loginlog")
public class LoginLogEntity implements Serializable {

    private static final long serialVersionUID = 8396367143533044716L;

    private String userId;

    @TableField("password")
    private String loginInfo;

    private String ip;

    @TableField("time")
    private Date loginTime;
}
