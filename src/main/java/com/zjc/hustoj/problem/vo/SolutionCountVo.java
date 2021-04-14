package com.zjc.hustoj.problem.vo;

import lombok.Data;

import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/12/4:31 下午
 */
@Data
public class SolutionCountVo {

    /**
     * 提交正确数
     */
    private Integer successCount;

    /**
     * 答案错误数
     */
    private Integer answerError;

    /**
     * 编译错误数
     */
    private Integer compileError;

    /**
     * 提交正确的题目列表
     */
    private List<SolvedProblemVo> solvedProblemsList;

    /**
     * 提交统计 (按半月为一组)
     */
    private List<CountByHalfMonthVo> countByHalfMonth;

}
