package com.zjc.hustoj.problem.constant;

import com.google.common.collect.Lists;
import com.zjc.hustoj.privilege.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author David Hsiang
 * @date 2021/04/14/3:08 下午
 */
@Getter
@AllArgsConstructor
public class RunResult {

    public static RunResult ALL                 = new RunResult(-1 ,"All"                 , "全部"    );
    public static RunResult PENDING             = new RunResult(0  ,"Pending"             , "等待"    );
    public static RunResult PENDING_REJUDGING   = new RunResult(1  ,"Pending Rejudging"   , "等待重判"  );
    public static RunResult COMPILING           = new RunResult(2  ,"Compiling"           , "编译中"   );
    public static RunResult RUNNING_JUDGING     = new RunResult(3  ,"Running & Judging"   , "运行并评判" );
    public static RunResult ACCEPTED            = new RunResult(4  ,"Accepted"            , "正确"    );
    public static RunResult PRESENTATION_ERROR  = new RunResult(5  ,"Presentation Error"  , "格式错误"  );
    public static RunResult WRONG_ANSWER        = new RunResult(6  ,"Wrong Answer"        , "答案错误"  );
    public static RunResult TIME_LIMIT_EXCEED   = new RunResult(7  ,"Time Limit Exceed"   , "时间超限"  );
    public static RunResult MEMORY_LIMIT_EXCEED = new RunResult(8  ,"Memory Limit Exceed" , "内存超限"  );
    public static RunResult OUTPUT_LIMIT_EXCEED = new RunResult(9  ,"Output Limit Exceed" , "输出超限"  );
    public static RunResult RUNTIME_ERROR       = new RunResult(10 ,"Runtime Error"       , "运行错误"  );
    public static RunResult COMPILE_ERROR       = new RunResult(11 ,"Compile Error"       , "编译错误"  );

    private Integer resultCode;
    private String resultName;
    private String resultNameZH;

    public static RunResult[] values(){
        RunResult[] results = new RunResult[]{
                ALL,
                PENDING,
                PENDING_REJUDGING,
                COMPILING,
                RUNNING_JUDGING,
                ACCEPTED,
                PRESENTATION_ERROR,
                WRONG_ANSWER,
                TIME_LIMIT_EXCEED,
                MEMORY_LIMIT_EXCEED,
                OUTPUT_LIMIT_EXCEED,
                RUNTIME_ERROR,
                COMPILE_ERROR
        };
        return results;
    }
}
