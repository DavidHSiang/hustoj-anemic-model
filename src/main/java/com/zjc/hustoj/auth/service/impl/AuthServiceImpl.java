package com.zjc.hustoj.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjc.hustoj.privilege.entity.PrivilegeEntity;
import com.zjc.hustoj.auth.service.AuthService;
import com.zjc.hustoj.privilege.service.PrivilegeService;
import com.zjc.hustoj.auth.utils.CipherUtils;
import com.zjc.hustoj.auth.vo.LoginReqVo;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.user.entity.UserEntity;
import com.zjc.hustoj.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author David Hsiang
 * @date 2021/04/06/10:45 下午
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private PrivilegeService privilegeService;

    @Resource
    private UserService userService;

    @Override
    public UserPageRespVo login(LoginReqVo loginReqVo, String verifyCode) {
//        if (StringUtils.isEmpty(verifyCode)){ //todo 将来开启
//            throw new ServiceException("验证码失效");
//        }
//        if (!verifyCode.equals(loginReqVo.getVerifyCode())){
//            throw new ServiceException("验证码不匹配");
//        }

        String userId = loginReqVo.getUserId();
        String password = loginReqVo.getPassword();

        QueryWrapper<PrivilegeEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        int count = privilegeService.count(queryWrapper);
        if(count == 0){
            throw new ServiceException("没有管理员权限");
        }
        UserEntity userEntity = userService.getById(userId);

        if(userEntity == null) {
            throw new ServiceException("用户名/密码错误");
        }

        if (!CipherUtils.equals(password,userEntity.getPassword())) {
            throw new ServiceException("用户名/密码错误");
        }

        return BeanUtils.copyProperties(userEntity, UserPageRespVo.class);
    }

}
