package com.zjc.hustoj.user.vo;

import com.zjc.hustoj.core.utils.excel.annotation.ExcelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author David Hsiang
 * @date 2021/04/08/4:47 下午
 */
@Data
public class RegisterInfoDTO{

    @NotBlank(message = "用户名为空")
    @ExcelProperty(rowIndex = 0, title = "学号")
    private String userId ;

    @NotBlank(message = "昵称为空")
    @ExcelProperty(rowIndex = 1, title = "昵称")
    private String nick ;

    @NotBlank(message = "密码为空")
    @ExcelProperty(rowIndex = 2, title = "密码")
    private String password ;

    @NotBlank(message = "学校为空")
    @ExcelProperty(rowIndex = 3, title = "学校")
    private String school ;

    @NotBlank(message = "邮箱为空")
    @ExcelProperty(rowIndex = 4, title = "邮箱")
    private String email ;
}
