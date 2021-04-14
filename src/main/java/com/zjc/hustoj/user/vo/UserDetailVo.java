package com.zjc.hustoj.user.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:19 下午
 */
@Data
public class UserDetailVo {

    /**
     * 用户 id (学号)
     */
    private String userId;

    /**
     * 昵称 (姓名)
     */
    private String nick;

    /**
     * 已解决的问题
     */
    private Integer solved;

    /**
     * 问题提交次数
     */
    private Integer submit;

    /**
     * 学校
     */
    private String school;

    /**
     * 排名
     */
    private Integer ranking;

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
    private List solvedProblemsList;

    /**
     * 提交统计
     */
    private List countByHalfMonth;

}
