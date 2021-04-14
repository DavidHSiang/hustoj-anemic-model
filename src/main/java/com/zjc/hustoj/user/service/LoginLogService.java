package com.zjc.hustoj.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.user.entity.LoginLogEntity;
import com.zjc.hustoj.user.vo.LoginLogPageReqVo;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:00 下午
 */
public interface LoginLogService extends IService<LoginLogEntity> {
    PageUtils queryPage(LoginLogPageReqVo loginLogPageReqVo);
}
