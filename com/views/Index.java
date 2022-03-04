package com.views;

import com.service.LogService;
import com.service.LoginService;
import com.service.RegisterService;
import com.service.RetrievePwdService;
import com.utils.InputLimit;

public class Index {
    /*
     * 开始页面
     * */
    public static void main(String[] args) {
        boolean flag = true;
        while (flag) {
            System.out.println("****** 欢迎来到图书管理系统 ******");
            System.out.println("1. 登录（管理员/操作员）");
            System.out.println("2. 管理员注册");
            System.out.println("3. 忘记密码（修改密码）");
            System.out.println("4. 登录日志");
            System.out.println("5. 退出系统");
            System.out.println("请选择：");
            char c1 = InputLimit.InputMenuFive();
            switch (c1) {
                case '1':
                    LoginView();
                    break;
                case '2':
                    RegisterView();
                    break;
                case '3':
                    RetrievePwdView();
                    break;
                case '4':
                    LoginLogView();
                    break;
                case '5':
                    System.out.println("********************************");
                    InputLimit.Warn("请确认是否退出系统？（Y/N）");
                    char yn = InputLimit.InputChoice();
                    if (yn == 'Y') {
                        flag = false;
                    }
                    break;
            }
        }
    }

    // 1. 登录（管理员/操作员）
    public static void LoginView(){
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
                LoginService.LoginAdmin(adminName, adminPwd);
                break;
            case '2':
                System.out.println("请输入用户名称：");
                String operName = InputLimit.InputString();
                System.out.println("请输入用户密码：");
                String operPwd = InputLimit.InputString();
                LoginService.LoginOper(operName, operPwd);
                break;
        }
    }

    //2. 管理员注册
    public static void RegisterView(){
        System.out.println("********************************");
        System.out.println("2. 管理员注册");
        System.out.println("请输入用户名称：");
        String adminName = InputLimit.InputString();
        System.out.println("请输入用户密码");
        String adminPwd = InputLimit.InputString();
        RegisterService.AdminRegister(adminName, adminPwd);
    }

    //3. 忘记密码（修改密码）
    public static void RetrievePwdView(){
        System.out.println("********************************");
        System.out.println("3. 忘记密码（修改密码）");
        System.out.println("请输入您身为管理员的用户名称：");
        String adminName = InputLimit.InputString();
        System.out.println("请输入新密码：");
        String operPwd = InputLimit.InputString();
        System.out.println("请确认新密码：");
        String surePwd = InputLimit.InputString();
        RetrievePwdService.RetrievePwd(adminName, operPwd, surePwd);
    }

    //4. 登录日志
    public static void LoginLogView(){
        System.out.println("********************************");
        System.out.println("4. 登录日志");
        // 查看登录日志
        LogService.ShowLoginLog();
    }
}
