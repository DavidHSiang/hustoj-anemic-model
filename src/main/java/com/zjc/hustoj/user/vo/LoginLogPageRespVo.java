package com.zjc.hustoj.user.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:09 下午
 */
@Data
public class LoginLogPageRespVo {

    private String userId;

    private String loginInfo;

    private String ip;

    private Date loginTime;
}
