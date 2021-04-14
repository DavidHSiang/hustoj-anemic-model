/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.zjc.hustoj.core.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjc.hustoj.core.PageQueryVo;
import com.zjc.hustoj.core.xss.SQLFilter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 查询参数
 *
 * @author Mark sunlightcs@gmail.com
 */
public class Query {

    public static <T> IPage<T> getPage(PageQueryVo pageQueryVo){
        return getPage(pageQueryVo, null, false);
    }

    public static <T> IPage<T> getPage(PageQueryVo pageQueryVo, String defaultOrderField, boolean isAsc) {

        //分页对象
        Page<T> page = new Page<T>(pageQueryVo.getCurrPage(), pageQueryVo.getPageSize());

        //排序字段
        //防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SQLFilter.sqlInject(pageQueryVo.getOrderField());
        String order = pageQueryVo.getOrder();


        //前端字段排序
        if(StringUtils.isNotEmpty(orderField) && StringUtils.isNotEmpty(order)){
            if(Constant.ASC.equalsIgnoreCase(order)) {
                return  page.addOrder(OrderItem.asc(orderField));
            }else {
                return page.addOrder(OrderItem.desc(orderField));
            }
        }

        //没有排序字段，则不排序
        if(StringUtils.isBlank(defaultOrderField)){
            return page;
        }

        //默认排序
        if(isAsc) {
            page.addOrder(OrderItem.asc(defaultOrderField));
        }else {
            page.addOrder(OrderItem.desc(defaultOrderField));
        }

        return page;
    }
}
