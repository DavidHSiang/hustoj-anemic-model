package com.zjc.hustoj.news.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/14/5:35 下午
 */
@Data
public class NewsDetailVo {
    /**
     * 新闻 id
     */
    private Integer newsId;

    /**
     * 新闻标题
     */
    @NotEmpty(message = "新闻标题不能为空")
    private String title;

    /**
     * 新闻内容
     */
    @NotEmpty(message = "新闻内容不能为空")
    private String content;
}
