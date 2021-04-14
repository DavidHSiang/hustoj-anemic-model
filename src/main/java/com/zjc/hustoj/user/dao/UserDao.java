package com.zjc.hustoj.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjc.hustoj.user.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:46 上午
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {
}
