package com.zjc.hustoj.core.utils.excel.model;

import com.zjc.hustoj.core.exception.ServiceException;
import com.zjc.hustoj.core.utils.excel.annotation.ExcelProperty;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

/**
 * @author David Hsiang
 * @date 2021/04/11/2:00 下午
 */
public class ExcelRow implements Iterable<ExcelCell>{

    public static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    private Row row;

    public ExcelRow(Row row) {
        this.row = row;
    }

    public static ExcelRow create(Row row) {
        return new ExcelRow(row);
    }

    public ExcelCell getCellAt(int index){
        return ExcelCell.create(row.getCell(index));
    }

    public <T> T read(Class<T> clazz) throws IllegalAccessException, InstantiationException {
        T data = clazz.newInstance();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ExcelProperty.class)) {
                ExcelProperty excelProperties = field.getAnnotation(ExcelProperty.class);
                ExcelCell cell = this.getCellAt(excelProperties.rowIndex());
                field.setAccessible(true);
                field.set(data, cell.read());
            }
        }

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(data);
        if (constraintViolations.size() > 0) {
            String errMsg = constraintViolations.iterator().next().getMessage();
            throw new ValidationException(errMsg);
        }
        return data;
    }


    public <W> void write(W data) {
        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(ExcelProperty.class)) {
                continue;
            }
            ExcelProperty excelProperties = field.getAnnotation(ExcelProperty.class);
            ExcelCell cell = this.createCell(excelProperties.rowIndex());
            try {
                field.setAccessible(true);
                cell.write(field.get(data));
            } catch (Exception e) {
                throw new ServiceException("reflect error",e);
            }
        }
    }

    private ExcelCell createCell(int index) {
        Cell cell = row.createCell(index);
        return ExcelCell.create(cell);
    }

    /**
     * 迭代器部
     */
    @Override
    public Iterator<ExcelCell> iterator() {
        return new RowIterator();
    }

    private class RowIterator implements Iterator<ExcelCell>{

        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < row.getRowNum();
        }

        @Override
        public ExcelCell next() {
            return getCellAt( index ++ );
        }
    }
}
