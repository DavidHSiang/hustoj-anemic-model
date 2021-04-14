package com.zjc.hustoj.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.user.dao.LoginLogDao;
import com.zjc.hustoj.user.dao.UserDao;
import com.zjc.hustoj.user.entity.LoginLogEntity;
import com.zjc.hustoj.user.entity.UserEntity;
import com.zjc.hustoj.user.service.LoginLogService;
import com.zjc.hustoj.user.service.UserService;
import com.zjc.hustoj.user.vo.LoginLogPageReqVo;
import com.zjc.hustoj.user.vo.LoginLogPageRespVo;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import org.springframework.stereotype.Service;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:00 下午
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogDao, LoginLogEntity> implements LoginLogService {
    @Override
    public PageUtils queryPage(LoginLogPageReqVo loginLogPageReqVo) {
        IPage<LoginLogEntity> page = this.page(
                Query.getPage(loginLogPageReqVo),
                new QueryWrapper<LoginLogEntity>().eq("user_id", loginLogPageReqVo.getUserId())
        );

        return new PageUtils(page, LoginLogPageRespVo.class);
    }
}
