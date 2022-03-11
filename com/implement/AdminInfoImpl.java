package com.implement;

import com.entity.AdminInfo;

/*
 * 管理员信息
 * */
public interface AdminInfoImpl {

    /**
     * 获取管理员信息
     * @param s1 管理员姓名
     * @return 返回一条管理员信息
     */
    AdminInfo getAdminInfo(String s1);

    /**
     * 注册管理员
     * @param inputName 管理员姓名
     * @param inputPwd 管理员登录密码
     * @param inputTel 管理员联系电话
     */
    void RegisterAdmin(String inputName, String inputPwd, String inputTel);

    /**
     * 忘记密码，也是修改密码
     * @param adminName 管理员姓名
     * @param newPwd 新密码
     */
    void RetrievePwd(String adminName, String newPwd);

    /**
     * 查询所有管理员
     */
    void AllAdmin();

    /**
     * 根据管理员Id查询管理员信息
     * @param i 管理员编号Id
     * @return 返回一条管理员信息
     */
    AdminInfo AllAdminById(int i);
}
