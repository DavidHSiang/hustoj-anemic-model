package com.zjc.hustoj.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.auth.utils.CipherUtils;
import com.zjc.hustoj.auth.utils.MD5Util;
import com.zjc.hustoj.core.constant.Const;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.excel.ExcelUtilsBak;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.problem.service.SolutionService;
import com.zjc.hustoj.problem.vo.SolutionCountVo;
import com.zjc.hustoj.user.dao.UserDao;
import com.zjc.hustoj.user.entity.UserEntity;
import com.zjc.hustoj.user.service.UserService;
import com.zjc.hustoj.user.vo.*;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:44 上午
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserEntity> implements UserService {

    @Resource
    private SolutionService solutionService;

    @Override
    public PageUtils queryPage(UserPageReqVo userPageReqVo) {

        IPage<UserEntity> page = this.page(
                Query.getPage(userPageReqVo),
                new QueryWrapper<UserEntity>()
        );

        return new PageUtils(page, UserPageRespVo.class);
    }

    @Override
    public UserPageRespVo updateUsable(String userId) {
        if (StringUtils.isEmpty(userId)){
            throw new ServiceException("userId 为空");
        }

        UserEntity userEntity = this.getById(userId);

        if (userEntity == null){
            throw new ServiceException("用户不存在");
        }

        String isDeleted = userEntity.getDefunct();
        if (Const.IS_DELETE.equals(isDeleted)){
            userEntity.setDefunct(Const.NOT_DELETE);
        }else {
            userEntity.setDefunct(Const.IS_DELETE);
        }

        this.updateById(userEntity);
        return BeanUtils.copyProperties(userEntity, UserPageRespVo.class);
    }

    @Override
    public void resetPassword(ResetPasswordVo resetPasswordVo) {
        UserEntity userEntity = this.getById(resetPasswordVo.getUserId());
        if (userEntity == null){
            throw new ServiceException("用户名不存在");
        }
        String cipherText = CipherUtils.getCipherTextWithMD5(resetPasswordVo.getPassword());
        userEntity.setPassword(cipherText);
        this.updateById(userEntity);
    }

    @Override
    public void registerBatch(MultipartFile file, String requestIp) {
        //| userId | nick | password | school | email |
        //|--------|------|----------|--------|-------|
        InputStream fileInputStream = null;
        try {
            fileInputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            List<RegisterInfoDTO> list = ExcelUtilsBak.readExcelContent(fileInputStream,fileName,RegisterInfoDTO.class);
            List<UserEntity> userEntities = BeanUtils.convertList(list, UserEntity.class);
            this.saveBatch(userEntities);
        } catch (Exception e) {
            throw new ServiceException("导入失败", e);
        }finally {
            if (fileInputStream != null){
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new ServiceException("系统异常");
                }
            }
        }

    }

    @Override
    public UserDetailVo detail(String userId) {
        UserEntity userEntity = this.getById(userId);
        UserDetailVo userDetailVo = BeanUtils.copyProperties(userEntity, UserDetailVo.class);

        Integer ranking = count(new QueryWrapper<UserEntity>().ge("solved", userDetailVo.getSolved()));
        userDetailVo.setRanking(ranking);

        SolutionCountVo solutionCountVo = solutionService.getSolutionCountByUserId(userId);
        BeanUtils.copyProperties(solutionCountVo, userDetailVo);

        return userDetailVo;
    }


}
