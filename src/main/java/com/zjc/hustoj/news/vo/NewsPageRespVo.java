package com.zjc.hustoj.news.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:37 下午
 */
@Data
public class NewsPageRespVo {
    /**
     * 新闻 id
     */
    private Integer newsId;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 更新时间
     */
    private Date time;


    /**
     * 是否失效 Y -> 已失效，N -> 未失效
     */
    private String defunct;
}
