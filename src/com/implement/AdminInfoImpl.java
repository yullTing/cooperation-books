package com.implement;

import com.entity.AdminInfo;

/*
 * 管理员信息
 * */
public interface AdminInfoImpl {

    //管理员登录
    void LoginAdmin(String s1, String s2);

    //注册管理员
    void RegisterAdmin(String s1, String s2);

    //忘记密码（修改密码）
    void RetrievePwd(String s1, String s2, String s3);

    //查询所有管理员
    void AllAdmin();

    //根据管理员Id查询管理员信息
    AdminInfo AllAdminById(int i);
}
