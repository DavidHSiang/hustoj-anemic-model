package com.zjc.hustoj.problem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:59 下午
 */
@Data
@TableName("problem")
public class ProblemEntity implements Serializable {
    private static final long serialVersionUID = 7022081879711398328L;

    /**
     * 问题 id
     */
    @TableId
    private Integer problemId;

    /**
     * 问题标题
     */
    private String title;

    /**
     * 问题描述
     */
    private String description;

    /**
     * 输入描述
     */
    private String input;

    /**
     * 输出描述
     */
    private String output;

    /**
     * 输入示例
     */
    private String sampleInput;

    /**
     * 输出示例
     */
    private String sampleOutput;

    /**
     * 是否为特别题目 0 -> 非特别题目
     */
    private String spj;

    /**
     * 提示
     */
    private String hint;

    /**
     * 分类
     */
    private String source;

    /**
     * 创建时间
     */
    private Date inDate;

    /**
     * 限时 ( 秒 )
     */
    private BigDecimal timeLimit;

    /**
     * 空间限制 (MByte)
     */
    private Integer memoryLimit;

    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;

    /**
     * 提交成功数
     */
    private Integer accepted;

    /**
     * 提交数
     */
    private Integer submit;

    /**
     * 解决数（无用）
     */
    private Integer solved;
}
