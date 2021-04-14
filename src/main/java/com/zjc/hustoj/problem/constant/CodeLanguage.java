package com.zjc.hustoj.problem.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/14/3:26 下午
 */
@Getter
@AllArgsConstructor
public class CodeLanguage {
    public static CodeLanguage ALL        =  new CodeLanguage(-1 ,"all");
    public static CodeLanguage C          =  new CodeLanguage(0  ,"c");
    public static CodeLanguage CPP        =  new CodeLanguage(1  ,"c++");
    public static CodeLanguage JAVA       =  new CodeLanguage(3  ,"java");
    public static CodeLanguage PYTHON     =  new CodeLanguage(6  ,"python");
    public static CodeLanguage PHP        =  new CodeLanguage(7  ,"php");
    public static CodeLanguage CSHARP     =  new CodeLanguage(9  ,"c#");
    public static CodeLanguage JAVASCRIPT =  new CodeLanguage(16 ,"javascript");
    public static CodeLanguage GO         =  new CodeLanguage(17 ,"go");
    public static CodeLanguage SQL        =  new CodeLanguage(18 ,"sql");

    private Integer languageId;
    private String languageName;

    public static CodeLanguage[] values(){
        CodeLanguage[] codeLanguages = new CodeLanguage[]{
                ALL,
                C,
                CPP,
                JAVA,
                PYTHON,
                PHP,
                CSHARP,
                JAVASCRIPT,
                GO,
                SQL,
        };
        return codeLanguages;
    }

}
