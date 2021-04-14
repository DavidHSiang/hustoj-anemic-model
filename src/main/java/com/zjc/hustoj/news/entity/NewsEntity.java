package com.zjc.hustoj.news.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:21 下午
 */
@Data
@TableName("news")
public class NewsEntity implements Serializable {
    private static final long serialVersionUID = 3195338791744750768L;

    /**
     * 新闻 id
     */
    @TableId
    private Integer newsId;

    /**
     * 作者 id
     */
    private String userId;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 新闻内容
     */
    private String content;

    /**
     * 更新时间
     */
    private Date time;

    /**
     * 关键字
     */
    private Integer importance;

    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;
}
