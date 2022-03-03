package com.views;

import com.entity.Admininfo;
import com.entity.Operinfo;
import com.entity.Readertype;
import com.service.*;
import com.utils.InputLimit;

import java.util.ArrayList;

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
                    LogService.ShowOperLog();
                    break;
                case '2':
                    BorrowMoneyView();
                    break;
                case '3':
                    OperManageView(s);
                    break;
                case '4':
                    PenalPillService.QueryPenalPill();
                    break;
                case '5':
                    InputLimit.BlueFont("********************************");
                    InputLimit.Warn("请确认是否退出管理员身份登录？（Y/N）");
                    char ynAVI = InputLimit.InputChoice();
                    if (ynAVI == 'Y') {
                        LogService.AddLoginLog("管理员[" + s + "]退出系统");
                        flagAVI = false;
                    }
                    break;
            }
        }
    }

    //2. 图书借阅金额设定
    //说明：图书的借阅金额应该根据读者类型确定
    public static void BorrowMoneyView(){
        InputLimit.BlueFont("********************************");
        ArrayList<Readertype> readertypes = BorrowMoneyService.QueryBorrowMoney();
        if (readertypes!=null){
            InputLimit.Warn("请确认是否修改此设定？（Y/N）");
            char ynBMV = InputLimit.InputChoice();
            if (ynBMV == 'Y') {
                BorrowMoneyService.UpdateBorrowMoney(readertypes);
            }
        }else {
            InputLimit.Warn("图书借阅金额还未设定，请先设置读者类型！");
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
                    InputLimit.BlueFont("请输入员工名称：");
                    String operName = InputLimit.InputString();
                    InputLimit.BlueFont("请输入登录密码：");
                    String operPwd = InputLimit.InputString();
                    /*System.out.println("请确认登录密码：");
                    String surePwd = InputLimit.InputString();*/
                    OperManageService.AddOper(operName, operPwd, s);
                    break;
                case '2':
                    OperManageService.QueryOper();
                    break;
                case '3':
                    ArrayList<Operinfo> operinfos = OperManageService.QueryOper();
                    if (operinfos!=null) {//存在操作员信息
                        for (; ;) {//该循环用来确保输入的编号正确且存在
                            InputLimit.BlueFont("请输入列表左侧的编号：");
                            int id = InputLimit.InputNumber();
                            Operinfo operinfo = OperManageService.QueryOperById(id);
                            if (operinfo == null) {
                                InputLimit.Warn("编号输入错误！");
                            } else {
                                InputLimit.BlueFont("修改员工名称（" + operinfo.getOperName() + "）：");
                                String operNamee = InputLimit.ModifyString(operinfo.getOperName());
                                InputLimit.BlueFont("修改员工密码(******)：");
                                String operPwdd = InputLimit.ModifyString(operinfo.getOperPwd());
                                for (; ;) {//该循环用来确保输入的管理员Id正确且存在
                                    InputLimit.BlueFont("修改所属管理员Id（" + operinfo.getAdminId() + "）：");
                                    RegisterService.AllAdmin();//查询所有管理员
                                    int adminId = InputLimit.ModifyInt(operinfo.getAdminId());

                                    Admininfo admininfo = RegisterService.AllAdminById(adminId);
                                    if (admininfo==null){
                                        InputLimit.Warn("管理员Id输入错误！");
                                    } else {
                                        OperManageService.UpdateOper(id, operNamee, operPwdd, adminId);
                                        break;
                                    }
                                }
                            }
                            break;
                        }
                    }
                    break;
                case '4':
                    ArrayList<Operinfo> operinfos1 = OperManageService.QueryOper();
                    if (operinfos1 == null) {
                        InputLimit.Warn("目前没有任何操作员信息，无法进行删除操作！");
                    } else {
                        InputLimit.BlueFont("请输入列表左侧的编号：");
                        int idDelete = InputLimit.InputNumber();
                        //查询该编号下是否存在操作员信息
                        Operinfo operinfo = OperManageService.QueryOperById(idDelete);
                        if (operinfo != null) {
                            InputLimit.BlueFont(operinfo.toString());
                            InputLimit.Warn("请确认是否删除该操作员？（Y/N）");
                            char ynDO = InputLimit.InputChoice();
                            if (ynDO == 'Y') {
                                //确定删除
                                OperManageService.DeleteOper(idDelete);
                            }
                        } else {
                            InputLimit.Warn("编号输入错误！");
                        }
                    }
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
