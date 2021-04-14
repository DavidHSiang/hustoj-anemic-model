package com.zjc.hustoj.user.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:39 上午
 */
@Data
@TableName("users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 935756475996618960L;
    /**
     * id
     */
    @TableId
    private String userId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 提交数
     */
    private Integer submit;
    /**
     * 提交正确数
     */
    private Integer solved;
    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;
    /**
     * 用户上次登录 IP
     */
    private String ip;
    /**
     * 上次登录时间
     */
    @TableField(value = "accesstime")
    private Date accessTime;
    /**
     * todo ？
     */
    private Integer volume;
    /**
     * 语言
     */
    private Integer language;
    /**
     * 密码
     */
    private String password;
    /**
     * 注册时间
     */
    private Date regTime;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 学校
     */
    private String school;
}
