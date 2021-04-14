package com.zjc.hustoj.core;

import lombok.Data;

/**
 * @author David Hsiang
 * @date 2021/04/07/2:34 下午
 */
@Data
public class PageQueryVo {

    private Long currPage = 1L;

    private Long pageSize = 10L;

    private String orderField;

    private String order;

}
