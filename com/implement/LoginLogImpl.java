package com.implement;

/*
 * 日志
 * */
public interface LoginLogImpl {

    /**
     * 添加登录日志
     * @param details 日志具体内容
     */
    void AddLoginLog(String details);

    /**
     * 查看登录日志
     */
    void ShowLoginLog();
}
