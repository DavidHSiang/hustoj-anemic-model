package com.zjc.hustoj.core.utils.excel.writer;

import com.google.common.collect.Maps;
import com.zjc.hustoj.core.utils.excel.writer.CellWriter;
import com.zjc.hustoj.core.utils.excel.writer.impl.*;

import java.util.Date;
import java.util.Map;

/**
 * @author David Hsiang
 * @date 2021/04/11/9:03 下午
 */
public class WriterFactory {

    private static final CellWriter<Date> DEFAULT_DATE_WRITER    = new DefaultDateWriter()   ;
    private static final CellWriter<Number>  DEFAULT_DOUBLE_WRITER  = new DefaultNumberWriter() ;
    private static final CellWriter<String>  DEFAULT_STRING_WRITER  = new DefaultStringWriter() ;
    private static final CellWriter<Boolean> DEFAULT_BOOLEAN_WRITER = new DefaultBooleanWriter();
    private static final CellWriter<Object>  DEFAULT_BLANK_WRITER   = new DefaultBlankWriter()  ;

    private static final Map<Class, CellWriter> cellWriters = Maps.newHashMap();

    static {
        setCellWriter(Date.class,    DEFAULT_DATE_WRITER);
        setCellWriter(Double.class,  DEFAULT_DOUBLE_WRITER);
        setCellWriter(String.class,  DEFAULT_STRING_WRITER);
        setCellWriter(Number.class,  DEFAULT_DOUBLE_WRITER);
        setCellWriter(Boolean.class, DEFAULT_BOOLEAN_WRITER);
        setCellWriter(Object.class,  DEFAULT_BLANK_WRITER);
    }

    public static CellWriter getByData(Object data) {
        Class clazz = data.getClass();
        CellWriter cellWriter = cellWriters.get(data.getClass());
        while(cellWriter == null){
            clazz = clazz.getSuperclass();
            cellWriter = cellWriters.get(data.getClass());
        }
        return cellWriter;
    }

    public static <T> void setCellWriter (Class<T> writeType, CellWriter<? super T> cellWriter){
        cellWriters.put(writeType, cellWriter);
    }
}
