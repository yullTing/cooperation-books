package com.service;

import com.dao.AdminInfoDAO;
import com.dao.OperManageDAO;
import com.entity.AdminInfo;
import com.utils.InputLimit;

/*
 * 登录
 * */
public class UserInfoService {

    private final AdminInfoDAO daoAdminInfo = new AdminInfoDAO();
    private final OperManageDAO daoOperInfo = new OperManageDAO();

    // 1. 登录（管理员/操作员）
    public void LoginView(){
        System.out.println("********************************");
        System.out.println("1. 登录（管理员/操作员）");
        System.out.println("请选择身份：");
        System.out.println("1.管理员        2.操作员");
        char c2 = InputLimit.InputMenuTwo();
        switch (c2){
            case '1':
                System.out.println("请输入用户名称：");
                String adminName = InputLimit.InputString();
                System.out.println("请输入用户密码：");
                String adminPwd = InputLimit.InputString();
                daoAdminInfo.LoginAdmin(adminName, adminPwd);
                break;
            case '2':
                System.out.println("请输入用户名称：");
                String operName = InputLimit.InputString();
                System.out.println("请输入用户密码：");
                String operPwd = InputLimit.InputString();
                daoOperInfo.LoginOper(operName, operPwd);
                break;
        }
    }

    //2. 管理员注册
    public void RegisterAdmin(){
        System.out.println("********************************");
        System.out.println("2. 管理员注册");
        System.out.println("请输入用户名称：");
        String adminName = InputLimit.InputString();
        System.out.println("请输入用户密码");
        String adminPwd = InputLimit.InputString();
        daoAdminInfo.RegisterAdmin(adminName, adminPwd);
    }

    //3.忘记密码（修改密码）
    public void RetrievePwd(){
        System.out.println("********************************");
        System.out.println("3. 忘记密码（修改密码）");
        System.out.println("请输入您身为管理员的用户名称：");
        String adminName = InputLimit.InputString();
        System.out.println("请输入新密码：");
        String operPwd = InputLimit.InputString();
        System.out.println("请确认新密码：");
        String surePwd = InputLimit.InputString();
        daoAdminInfo.RetrievePwd(adminName, operPwd, surePwd);
    }

    //查询所有的管理员
    public void QueryAllAdmin(){
        daoAdminInfo.AllAdmin();
    }

    //根据Id查询所有管理员
    public AdminInfo QueryAdminById(int i){
        AdminInfo adminInfo = null;
        adminInfo = daoAdminInfo.AllAdminById(i);
        return adminInfo;
    }
}
