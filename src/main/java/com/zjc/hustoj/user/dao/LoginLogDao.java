package com.zjc.hustoj.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.hustoj.user.entity.LoginLogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:00 下午
 */
@Mapper
public interface LoginLogDao extends BaseMapper<LoginLogEntity> {
}
