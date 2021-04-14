package com.zjc.hustoj.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/06/10:51 下午
 */
@Data
public class UserPageRespVo implements Serializable {

    private static final long serialVersionUID = -3398896500237983394L;

    /**
     * id
     */
    private String userId;
    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;
    /**
     * 上次登录时间
     */
    private Date accessTime;
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
