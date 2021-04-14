package com.zjc.hustoj.problem.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/4:25 下午
 */
@Data
public class ProblemPageRespVo {
    /**
     * 问题 id
     */
    private Integer problemId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 创建时间
     */
    private Date inDate;

    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;

    /**
     * 提交成功数
     */
    private Integer accepted;
}
