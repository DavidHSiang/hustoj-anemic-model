package com.zjc.hustoj.problem.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author David Hsiang
 * @date 2021/04/14/4:30 下午
 */
@Data
@TableName("source_code")
public class SourceCodeEntity implements Serializable {
    private static final long serialVersionUID = 7653371253073631674L;

    @TableId
    private Integer solutionId;

    private String source;
}
