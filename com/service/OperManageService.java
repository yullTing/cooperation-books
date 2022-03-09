package com.service;

import com.dao.OperManageDAO;
import com.entity.AdminInfo;
import com.entity.OperInfo;
import com.utils.InputLimit;

import java.util.ArrayList;

/*
 * 员工信息管理
 * */
public class OperManageService {

    private final OperManageDAO daoOperManage = new OperManageDAO();

    public void AddOperService(String s) {
        InputLimit.BlueFont("请输入员工名称：");
        String operName = InputLimit.InputString();
        InputLimit.BlueFont("请输入登录密码：");
        String operPwd = InputLimit.InputString();
        /*System.out.println("请确认登录密码：");
        String surePwd = InputLimit.InputString();*/
        daoOperManage.AddOper(operName, operPwd, s);
    }

    public void QueryOperService() {
        daoOperManage.QueryOper();
    }

    public void UpdateOperService(String s) {
        ArrayList<OperInfo> operinfos = daoOperManage.QueryOper();
        if (operinfos!=null) {//存在操作员信息
            for (; ;) {//该循环用来确保输入的编号正确且存在
                InputLimit.BlueFont("请输入列表左侧的编号：");
                int id = InputLimit.InputNumber();
                OperInfo operinfo = daoOperManage.QueryOperById(id);
                if (operinfo == null) {
                    InputLimit.Warn("编号输入错误！");
                } else {
                    InputLimit.BlueFont("修改员工名称（" + operinfo.getOperName() + "）：");
                    String operNamee = InputLimit.ModifyString(operinfo.getOperName());
                    InputLimit.BlueFont("修改员工密码(******)：");
                    String operPwdd = InputLimit.ModifyString(operinfo.getOperPwd());

                    new UserInfoService().QueryAllAdmin(); //查询所有管理员
                    for (; ;) {//该循环用来确保输入的管理员Id正确且存在
                        InputLimit.BlueFont("修改所属管理员Id（" + operinfo.getAdminId() + "）：");
                        int adminId = InputLimit.ModifyInt(operinfo.getAdminId());

                        AdminInfo admininfo = new UserInfoService().QueryAdminById(adminId);
                        if (admininfo==null){
                            InputLimit.Warn("管理员Id输入错误！");
                        } else {
                            daoOperManage.UpdateOper(id, operNamee, operPwdd, adminId, s);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    public void DeleteOperService(String s){
        ArrayList<OperInfo> operinfos1 = daoOperManage.QueryOper();
        if (operinfos1 == null) {
            InputLimit.Warn("目前没有任何操作员信息，无法进行删除操作！");
        } else {
            InputLimit.BlueFont("请输入列表左侧的编号：");
            int idDelete = InputLimit.InputNumber();
            //查询该编号下是否存在操作员信息
            OperInfo operinfo = daoOperManage.QueryOperById(idDelete);
            if (operinfo != null) {
                InputLimit.BlueFont(operinfo.toString());
                String delOperName = operinfo.getOperName();
                InputLimit.Warn("请确认是否删除该操作员？（Y/N）");
                char ynDO = InputLimit.InputChoice();
                if (ynDO == 'Y') {
                    //确定删除
                    daoOperManage.DeleteOper(idDelete, delOperName, s);
                }
            } else {
                InputLimit.Warn("操作员编号输入错误！");
            }
        }
    }
}
