package com.views;

import com.service.*;
import com.utils.InputLimit;

public class AdminView {
    /*
     * 管理员功能
     * */
    public static void AdminViewIndex(String s) {
        boolean flagAVI = true;
        while (flagAVI) {
            InputLimit.BlueFont("******** 管理员操作界面 *********");
            InputLimit.BlueFont("1. 员工工作日志记录");
            InputLimit.BlueFont("2. 图书借阅金额设定");
            InputLimit.BlueFont("3. 员工信息管理");
            InputLimit.BlueFont("4. 图书逾期罚金账单查询");
            InputLimit.BlueFont("5. 返回上一页（退出登录）");
            char cAVI = InputLimit.InputMenuFive();
            switch (cAVI) {
                case '1':
                    new LogService().SOL(s);
                    break;
                case '2':
                    new BorrowMoneyService().BorrowMoney(s);
                    break;
                case '3':
                    OperManageView(s);
                    break;
                case '4':
                    new PenalBillService().QPP(s);
                    break;
                case '5':
                    InputLimit.BlueFont("********************************");
                    InputLimit.Warn("请确认是否退出管理员身份登录？（Y/N）");
                    char ynAVI = InputLimit.InputChoice();
                    if (ynAVI == 'Y') {
                        new LogService().ALL("管理员[" + s + "]退出系统");
                        flagAVI = false;
                    }
                    break;
            }
        }
    }


    //3. 员工信息管理
    public static void OperManageView(String s) {
        boolean flagOM = true;
        while (flagOM) {
            InputLimit.BlueFont("********* 员工信息管理 *********");
            InputLimit.BlueFont("1. 操作员信息添加");
            InputLimit.BlueFont("2. 操作员信息查询");
            InputLimit.BlueFont("3. 操作员信息修改");
            InputLimit.BlueFont("4. 操作员信息删除");
            InputLimit.BlueFont("5. 返回上一页");
            char cOM = InputLimit.InputMenuFive();
            switch (cOM) {
                case '1':
                    new OperManageService().AddOperService(s);
                    break;
                case '2':
                    new OperManageService().QueryOperService();
                    break;
                case '3':
                    new OperManageService().UpdateOperService(s);
                    break;
                case '4':
                   new OperManageService().DeleteOperService(s);
                    break;
                case '5':
                    InputLimit.BlueFont("********************************");
                    InputLimit.Warn("请确认是否返回上一页？（Y/N）");
                    char ynOM = InputLimit.InputChoice();
                    if (ynOM == 'Y') {
                        flagOM = false;
                    }
                    break;
            }
        }
    }

}
