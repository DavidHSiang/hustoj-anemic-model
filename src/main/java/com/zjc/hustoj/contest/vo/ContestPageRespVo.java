package com.zjc.hustoj.contest.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/5:05 下午
 */
@Data
public class ContestPageRespVo {

    /**
     * 竞赛 id
     */
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
     * 公开/内部 (0/1)
     */
    @TableField("private")
    private Integer privation;

}
