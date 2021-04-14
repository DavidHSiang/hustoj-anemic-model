package com.zjc.hustoj.problem.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/14/2:45 下午
 */
@Data
public class SolutionDetailVo {

    /**
     * 提交编号
     */
    private Integer solutionId;

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 题目编号
     */
    private Integer problemId;

    /**
     * 结果
     */
    private Integer result;

    /**
     * 内存
     */
    private Integer memory;

    /**
     * 耗时
     */
    private Integer time;

    /**
     * 语言
     */
    private Integer language;

    /**
     * 代码长度
     */
    private Integer codeLength;

    /**
     * 提交时间
     */
    private Date inDate;

    /**
     * 判题机
     */
    private String judger;

    /**
     * 通过率
     */
    private BigDecimal passRate;

    /**
     * 提交代码
     */
    private String source;
}
