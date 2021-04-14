package com.zjc.hustoj.problem.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.hustoj.problem.entity.SolutionEntity;
import com.zjc.hustoj.problem.vo.CountByHalfMonthVo;
import com.zjc.hustoj.problem.vo.SolutionCountVo;
import com.zjc.hustoj.problem.vo.SolvedProblemVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/12/4:18 下午
 */
@Mapper
public interface SolutionDao extends BaseMapper<SolutionEntity> {

    SolutionCountVo getSolutionCountByUserId(String userId);

    List<CountByHalfMonthVo> getCountByHalfMonth(String userId);

    List<SolvedProblemVo> getSolvedProblemByUserId(String userId);
}
