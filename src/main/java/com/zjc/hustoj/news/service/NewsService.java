package com.zjc.hustoj.news.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.news.entity.NewsEntity;
import com.zjc.hustoj.news.vo.NewsDetailVo;
import com.zjc.hustoj.news.vo.NewsPageReqVo;
import com.zjc.hustoj.news.vo.NewsPageRespVo;

/**
 * @author David Hsiang
 * @date 2021/04/07/12:43 上午
 */
public interface NewsService extends IService<NewsEntity> {

    PageUtils queryPage(NewsPageReqVo newsPageReqVo);

    NewsPageRespVo updateUsable(Integer newsId);

    NewsDetailVo detail(Integer newsId);

    Integer saveOrUpdate(NewsDetailVo detailVo, String userId);
}
