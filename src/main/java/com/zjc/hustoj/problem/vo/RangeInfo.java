package com.zjc.hustoj.problem.vo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.hustoj.problem.entity.ProblemEntity;
import lombok.Data;

/**
 * @author David Hsiang
 * @date 2021/04/14/8:06 下午
 */
@Data
public class RangeInfo<T> {
    private Object geInfo;

    private Object leInfo;

    public QueryWrapper<T> getWrapper(String column) {
        return new QueryWrapper<T>().ge(column, geInfo).le(column, leInfo);
    }
}
