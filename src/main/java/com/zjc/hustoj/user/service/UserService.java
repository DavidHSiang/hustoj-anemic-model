package com.zjc.hustoj.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.user.entity.UserEntity;
import com.zjc.hustoj.user.vo.ResetPasswordVo;
import com.zjc.hustoj.user.vo.UserDetailVo;
import com.zjc.hustoj.user.vo.UserPageReqVo;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:43 上午
 */
public interface UserService extends IService<UserEntity> {

    PageUtils queryPage(UserPageReqVo userPageReqVo);

    UserPageRespVo updateUsable(String userId);

    void resetPassword(ResetPasswordVo resetPasswordVo);

    void registerBatch(MultipartFile file, String requestIp);

    UserDetailVo detail(String userId);
}
