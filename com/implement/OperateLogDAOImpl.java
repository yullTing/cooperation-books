package com.implement;

public interface OperateLogDAOImpl {

    //添加员工工作日志
    void AddOperLog(String details);

    //查看员工工作日志
    void ShowOperLog(String s);
}
