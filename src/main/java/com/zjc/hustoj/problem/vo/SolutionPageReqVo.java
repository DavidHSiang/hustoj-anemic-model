package com.zjc.hustoj.problem.vo;

import com.zjc.hustoj.core.PageQueryVo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author David Hsiang
 * @date 2021/04/14/2:42 下午
 */
@Data
public class SolutionPageReqVo extends PageQueryVo {

    /**
     * 题目编号
     */
    private Integer problemId;

    /**
     * 用户 id
     */
    private String userId;

    /**
     * 编程语言
     */
    @NotNull(message = "编程语言不能为空")
    private Integer language;


    /**
     * 运行结果
     */
    @NotNull(message = "运行结果不能为空")
    private Integer result;

}
