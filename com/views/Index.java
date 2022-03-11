package com.views;

import com.service.LogService;
import com.service.UserInfoService;
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
                    new UserInfoService().LoginView();
                    break;
                case '2':
                    new UserInfoService().RegisterAdmin();
                    break;
                case '3':
                    new UserInfoService().RetrievePwd();
                    break;
                case '4':
                    new LogService().SLL();
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
}
