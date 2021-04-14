package com.zjc.hustoj.problem.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author David Hsiang
 * @date 2021/04/12/5:26 下午
 */
@Data
public class ProblemDetailVo {
    /**
     * 问题 id
     */
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
     * 提示
     */
    private String hint;

    /**
     * 分类
     */
    private String source;

    /**
     * 限时 ( 秒 )
     */
    private BigDecimal timeLimit;

    /**
     * 空间限制 (MByte)
     */
    private Integer memoryLimit;

    /**
     * 提交成功数
     */
    private Integer accepted;

    /**
     * 提交数
     */
    private Integer submit;

    /**
     * 创建人
     */
    private String problemEditor;

    /**
     * 是否为特别题目 0 -> 非特别题目
     */
    private String spj;

}
