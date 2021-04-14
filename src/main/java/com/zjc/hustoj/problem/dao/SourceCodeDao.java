package com.zjc.hustoj.problem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.hustoj.problem.entity.SolutionEntity;
import com.zjc.hustoj.problem.entity.SourceCodeEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author David Hsiang
 * @date 2021/04/14/4:34 下午
 */
@Mapper
public interface SourceCodeDao extends BaseMapper<SourceCodeEntity> {
}
