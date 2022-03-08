package com.dao;

import com.entity.OperateLog;
import com.implement.OperateLogDAOImpl;
import com.utils.GetTime;
import com.utils.InputLimit;

import java.util.ArrayList;

public class OperateLogDAO extends BaseDAO<OperateLog> implements OperateLogDAOImpl {

    //添加员工工作日志
    @Override
    public void AddOperLog(String details) {
        String s = GetTime.preciseTime();
        String sql = "INSERT INTO operlog VALUES(NULL,?,?)";

        int i = doUpdate(sql, s, details);
        if (i < 0) {
            InputLimit.Warn("添加员工工作日志操作执行失败！");
        }
    }

    //查看员工工作日志
    @Override
    public void ShowOperLog(String s) {
        String sql = "SELECT * FROM operlog ORDER BY ologTime ASC";
        ArrayList<OperateLog> operlogs = doQueryResultList(sql);
        operlogs.forEach(System.out::println);
        AddOperLog("管理员[" + s + "]查看员工工作日志");
    }
}
