package com.zjc.hustoj.news.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjc.hustoj.core.constant.Const;
import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.BeanUtils;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.core.utils.Query;
import com.zjc.hustoj.news.dao.NewsDao;
import com.zjc.hustoj.news.entity.NewsEntity;
import com.zjc.hustoj.news.service.NewsService;
import com.zjc.hustoj.news.vo.NewsDetailVo;
import com.zjc.hustoj.news.vo.NewsPageRespVo;
import com.zjc.hustoj.news.vo.NewsPageReqVo;
import com.zjc.hustoj.user.entity.UserEntity;
import com.zjc.hustoj.user.vo.UserPageRespVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:44 上午
 */
@Service
public class NewsServiceImpl extends ServiceImpl<NewsDao, NewsEntity> implements NewsService {
    @Override
    public PageUtils queryPage(NewsPageReqVo newsPageReqVo) {

        QueryWrapper queryWrapper = new QueryWrapper<NewsEntity>();
        String title = newsPageReqVo.getTitle();
        String content = newsPageReqVo.getContent();

        if (StringUtils.isNotEmpty(title)){
            queryWrapper.like("title", title);
        }

        if (StringUtils.isNotEmpty(content)){
            queryWrapper.like("content", content);
        }

        IPage<NewsEntity> page = this.page(
                Query.getPage(newsPageReqVo),
                queryWrapper
        );

        return new PageUtils(page, NewsPageRespVo.class);
    }

    @Override
    public NewsPageRespVo updateUsable(Integer newsId) {
        if (newsId == null){
            throw new ServiceException("newsId 为空");
        }

        NewsEntity newsEntity = this.getById(newsId);

        if (newsEntity == null){
            throw new ServiceException("新闻不存在");
        }

        String isDeleted = newsEntity.getDefunct();
        if (Const.IS_DELETE.equals(isDeleted)){
            newsEntity.setDefunct(Const.NOT_DELETE);
        }else {
            newsEntity.setDefunct(Const.IS_DELETE);
        }

        this.updateById(newsEntity);
        return BeanUtils.copyProperties(newsEntity, NewsPageRespVo.class);
    }

    @Override
    public NewsDetailVo detail(Integer newsId) {
        NewsEntity newsEntity = this.getById(newsId);
        return BeanUtils.copyProperties(newsEntity, NewsDetailVo.class);
    }

    @Override
    public Integer saveOrUpdate(NewsDetailVo detailVo, String userId) {
        NewsEntity newsEntity = BeanUtils.copyProperties(detailVo, NewsEntity.class);
        if (newsEntity.getNewsId() == null){
            // save
            newsEntity.setUserId(userId);
            newsEntity.setImportance(0);
            newsEntity.setDefunct(Const.NOT_DELETE);
        }
        newsEntity.setTime(new Date());
        this.saveOrUpdate(newsEntity);
        return newsEntity.getNewsId();
    }

}
