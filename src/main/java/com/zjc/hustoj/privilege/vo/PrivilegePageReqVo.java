package com.zjc.hustoj.privilege.vo;

import com.zjc.hustoj.core.PageQueryVo;
import lombok.Data;

/**
 * @author David Hsiang
 * @date 2021/04/07/5:19 下午
 */
@Data
public class PrivilegePageReqVo extends PageQueryVo {

    private String userId = "";

    private String rightStr = "";

    public String getRightStr() {
        return rightStr.trim();
    }

    public String getUserId() {
        return userId.trim();
    }
}
