package com.zjc.hustoj.problem.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/12/4:18 下午
 */
@Data
@TableName("solution")
public class SolutionEntity implements Serializable {
    private static final long serialVersionUID = 55428326860362859L;

    @TableId
    private Integer solutionId;

    private Integer problemId;

    private String userId;

    private String nick;

    private Integer time;

    private Integer memory;

    private Date inDate;

    private Integer result;

    private Integer language;

    private String ip;

    private Integer contestId;

    private Integer valid;

    private Integer num;

    private Integer codeLength;

    @TableField("judgetime")
    private Date judgeTime;

    private BigDecimal passRate;

    private Integer lintError;

    private String judger;
}
