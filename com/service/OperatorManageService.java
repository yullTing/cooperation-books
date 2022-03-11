package com.service;

import com.dao.AdminInfoDAO;
import com.dao.OperatorManageDAO;
import com.entity.AdminInfo;
import com.entity.OperatorInfo;
import com.utils.InputLimit;

import java.util.ArrayList;

/*
 * 员工信息管理
 * */
public class OperatorManageService {

    private final OperatorManageDAO daoOperaManage = new OperatorManageDAO();

    public void AddOperaService(String s) {
        InputLimit.BlueFont("请输入员工名称：");
        String operaName = InputLimit.InputString();
        InputLimit.BlueFont("请输入登录密码：");
        String operaPwd = InputLimit.InputString();
        /*System.out.println("请确认登录密码：");
        String surePwd = InputLimit.InputString();*/
        daoOperaManage.AddOpera(operaName, operaPwd, s);
    }

    public void QueryOperaService() {
        daoOperaManage.QueryOpera();
    }

    public void UpdateOperaService(String s) {
        ArrayList<OperatorInfo> operaInfos = daoOperaManage.QueryOpera();
        if (operaInfos!=null) {//存在操作员信息
            for (; ;) {//该循环用来确保输入的编号正确且存在
                InputLimit.BlueFont("请输入列表左侧的编号：");
                int operaId = InputLimit.InputNumber();
                OperatorInfo operaInfo = daoOperaManage.QueryOperaById(operaId);
                if (operaInfo == null) {
                    InputLimit.Warn("编号输入错误！");
                } else {
                    InputLimit.BlueFont("修改员工名称（" + operaInfo.getOperName() + "）：");
                    String operaNamee = InputLimit.ModifyString(operaInfo.getOperName());
                    InputLimit.BlueFont("修改员工密码(******)：");
                    String operaPwdd = InputLimit.ModifyString(operaInfo.getOperPwd());

                    //new UserInfoService().QueryAllAdmin(); //查询所有管理员
                    new AdminInfoDAO().AllAdmin();
                    for (; ;) {//该循环用来确保输入的管理员Id正确且存在
                        InputLimit.BlueFont("修改所属管理员Id（" + operaInfo.getAdminId() + "）：");
                        int adminId = InputLimit.ModifyInt(operaInfo.getAdminId());

                        //AdminInfo adminInfo = new UserInfoService().QueryAdminById(adminId);
                        AdminInfo adminInfo = new AdminInfoDAO().AllAdminById(adminId);
                        if (adminInfo==null){
                            InputLimit.Warn("管理员Id输入错误！");
                        } else {
                            daoOperaManage.UpdateOpera(operaId, operaNamee, operaPwdd, adminId, s);
                            break;
                        }
                    }
                }
                break;
            }
        }
    }

    public void DeleteOperService(String s){
        ArrayList<OperatorInfo> operaInfos = daoOperaManage.QueryOpera();
        if (operaInfos == null) {
            InputLimit.Warn("目前没有任何操作员信息，无法进行删除操作！");
        } else {
            InputLimit.BlueFont("请输入列表左侧的编号：");
            int idDelete = InputLimit.InputNumber();
            //查询该编号下是否存在操作员信息
            OperatorInfo opera = daoOperaManage.QueryOperaById(idDelete);
            if (opera != null) {
                InputLimit.BlueFont(opera.toString());
                String delOperName = opera.getOperName();
                InputLimit.Warn("请确认是否删除该操作员？（Y/N）");
                char ynDO = InputLimit.InputChoice();
                if (ynDO == 'Y') {
                    //确定删除
                    daoOperaManage.DeleteOpera(idDelete, delOperName, s);
                }
            } else {
                InputLimit.Warn("操作员编号输入错误！");
            }
        }
    }
}
