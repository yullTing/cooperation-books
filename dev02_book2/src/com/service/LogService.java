package com.service;

import com.entity.Loginlog;
import com.entity.Operlog;
import com.utils.InputLimit;
import com.utils.JDBCUtils;
import com.utils.GetTime;

import java.util.ArrayList;

public class LogService {
    /*颜色特效*/
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    /*
    * 日志
    * */
    //添加登录日志
    public static void AddLoginLog(String details){
        String s = GetTime.preciseTime();
        String sql = "INSERT INTO loginlog VALUES(NULL,?,?)";

        int i = JDBCUtils.ExecuteData(sql, s, details);
        if (i>0){
        } else {
            InputLimit.Warn("添加登录日志操作执行失败！");
        }
    }

    //查看登录日志
    public static void ShowLoginLog(){
        System.out.println("登录日志如下：");
        String sql = "SELECT * FROM loginLog ORDER BY llogTime ASC";
        ArrayList<Loginlog> loginlogs = JDBCUtils.QueryMultiple(Loginlog.class, sql);
        loginlogs.forEach(System.out::println);
    }

    //添加员工工作日志
    public static void AddOperLog(String details){
        String s = GetTime.preciseTime();
        String sql = "INSERT INTO operlog VALUES(NULL,?,?)";

        int i = JDBCUtils.ExecuteData(sql, s, details);
        if (i>0){
        } else {
            InputLimit.Warn("添加员工工作日志操作执行失败！");
        }
    }

    //查看员工工作日志
    public static void ShowOperLog() {
        System.out.println("员工工作日志如下：");
        String sql = "SELECT * FROM operlog ORDER BY ologTime ASC";
        ArrayList<Operlog> operlogs = JDBCUtils.QueryMultiple(Operlog.class, sql);
        operlogs.forEach(System.out::println);
    }
}
