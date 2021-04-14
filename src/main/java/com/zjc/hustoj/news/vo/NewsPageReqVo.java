package com.zjc.hustoj.news.vo;

import com.zjc.hustoj.core.PageQueryVo;
import lombok.Data;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:35 下午
 */
@Data
public class NewsPageReqVo extends PageQueryVo {

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String content;
}
