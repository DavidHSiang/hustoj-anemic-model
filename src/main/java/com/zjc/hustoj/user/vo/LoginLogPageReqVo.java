package com.zjc.hustoj.user.vo;

import com.zjc.hustoj.core.PageQueryVo;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author David Hsiang
 * @date 2021/04/12/3:03 下午
 */
@Data
public class LoginLogPageReqVo extends PageQueryVo {
    @NotEmpty
    private String userId;
}
