package com.dao;

import com.entity.LoginLog;
import com.implement.LoginLogImpl;
import com.utils.GetTime;
import com.utils.InputLimit;
import com.utils.JDBCUtils;

import java.util.ArrayList;

public class LoginLogDAO extends BaseDAO<LoginLog> implements LoginLogImpl {

    //查看登录日志
    @Override
    public void ShowLoginLog() {
        ArrayList<LoginLog> loginlogs = null;
        String sql = "SELECT * FROM loginLog ORDER BY llogTime ASC";
        loginlogs = doQueryResultList(sql);

        loginlogs.forEach(System.out::println);
    }



    //添加登录日志
    @Override
    public void AddLoginLog(String details) {
        String s = GetTime.preciseTime();
        String sql = "INSERT INTO loginlog VALUES(NULL,?,?)";

        int i = doUpdate(sql, s, details);
        if (i > 0) {
        } else {
            InputLimit.Warn("添加登录日志操作执行失败！");
        }
    }
}
