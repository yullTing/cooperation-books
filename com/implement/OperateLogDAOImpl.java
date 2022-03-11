package com.implement;

public interface OperateLogDAOImpl {

    /**
     * 添加员工工作日志
     * @param details 日志具体内容
     */
    void AddOperLog(String details);

    /**
     * 查看员工工作日志
     * @param s 管理员姓名
     */
    void ShowOperLog(String s);
}
