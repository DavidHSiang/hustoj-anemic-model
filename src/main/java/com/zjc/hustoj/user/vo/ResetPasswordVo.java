package com.zjc.hustoj.user.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * @author David Hsiang
 * @date 2021/04/07/9:29 下午
 */
@Data
public class ResetPasswordVo {

    @NotBlank(message = "用户名不能为空")
    private String userId;

    @Min(value = 6,message = "密码至少需要6位")
    private String password;
}
