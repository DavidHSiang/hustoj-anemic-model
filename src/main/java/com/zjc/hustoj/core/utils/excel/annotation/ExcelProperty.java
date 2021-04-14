package com.zjc.hustoj.core.utils.excel.annotation;

import java.lang.annotation.*;

/**
 * @author David Hsiang
 * @date 2021/04/07/10:58 下午
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelProperty {

    /**
     * 列顺序，越小越靠前
     * @return 列顺序
     */
    int rowIndex();

    /**
     * 标题
     */
    String title();

}