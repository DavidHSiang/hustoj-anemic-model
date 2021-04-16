package com.zjc.hustoj.contest.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/4:37 下午
 */
@Data
@TableName("contest")
public class ContestEntity implements Serializable {
    private static final long serialVersionUID = -5888441157021418375L;

    /**
     * 竞赛 id
     */
    @TableId
    private Integer contestId;

    /**
     * 竞赛标题
     */
    private String title;

    /**
     * 竞赛开始时间
     */
    private Date startTime;

    /**
     * 竞赛结束时间
     */
    private Date endTime;

    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;

    /**
     * 竞赛描述
     */
    private String description;

    /**
     * 公开/内部 (0/1)
     */
    @TableField("private")
    private Integer privation;

    /**
     * 编程语言
     */
    @TableField("langmask")
    private Integer langMask;

    /**
     * 密码
     */
    private String password;

    /**
     * 管理员 id
     */
    private String userId;


}
