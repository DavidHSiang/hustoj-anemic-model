package com.zjc.hustoj.privilege.constant;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/07/5:57 下午
 */
public enum Role {

    ADMINISTRATOR("administrator"),
    PROBLEM_EDITOR("problem_editor"),
    SOURCE_BROWSER("source_browser"),
    CONTEST_CREATOR("contest_creator"),
    HTTP_JUDGE("http_judge"),
    PASSWORD_SETTER("password_setter");

    private String roleName;

    Role(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public static List<String> getRoleNameList(){
        List<String> list = Lists.newArrayList();
        for (Role value : Role.values()) {
            list.add(value.roleName);
        }
        return list;
    }
}
