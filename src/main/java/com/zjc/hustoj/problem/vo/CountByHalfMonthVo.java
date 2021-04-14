package com.zjc.hustoj.problem.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/12/4:57 下午
 */
@Data
public class CountByHalfMonthVo {

    private Date halfMonth;

    private Integer submitCount;

    private Integer successCount;
}
