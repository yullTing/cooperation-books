package com.service;

import com.dao.AdminInfoDAO;
import com.entity.AdminInfo;
import com.factory.IdentityFactory;
import com.utils.InputLimit;

import java.util.Objects;

/*
 * 登录
 * */
public class UserInfoService {

    private final AdminInfoDAO daoAdminInfo = new AdminInfoDAO();
    //private final OperManageDAO daoOperInfo = new OperManageDAO();

    // 1. 登录（管理员/操作员）
    public void LoginView(){
        System.out.println("********************************");
        System.out.println("1. 登录（管理员/操作员）");
        System.out.println("请选择身份：");
        System.out.println("1.管理员        2.操作员");
        char c2 = InputLimit.InputMenuTwo();

        Objects.requireNonNull(IdentityFactory.sureIdentity(c2)).LoginByIdentity();
        /*switch (c2){
            case '1':
                System.out.println("请输入管理员名称：");
                String inputName = InputLimit.InputString();

                Objects.requireNonNull(IdentityFactory.sureIdentity("管理员")).LoginByIdentity(inputName);
                break;
            case '2':
                System.out.println("请输入操作员名称：");
                String operaName = InputLimit.InputString();

                Objects.requireNonNull(IdentityFactory.sureIdentity("操作员")).LoginByIdentity(operaName);
                break;
        }*/
    }

    //2. 管理员注册
    public void RegisterAdmin(){
        System.out.println("********************************");
        System.out.println("2. 管理员注册");
        System.out.println("请输入用户名称：");
        String inputName = InputLimit.InputString();

        AdminInfo adminInfo = daoAdminInfo.getAdminInfo(inputName);
        if (adminInfo==null) {
            System.out.println("请输入用户密码");
            String inputPwd = InputLimit.InputString();
            System.out.println("请输入联系电话（11位数字）：");
            String inputTel = InputLimit.InputTel();

            daoAdminInfo.RegisterAdmin(inputName, inputPwd, inputTel);
        } else {
            InputLimit.Warn("该管理员已存在，请勿重复注册！");
        }
    }

    //3.忘记密码（修改密码）
    public void RetrievePwd(){
        System.out.println("********************************");
        System.out.println("3. 忘记密码（修改密码）");
        System.out.println("请输入您身为管理员的用户名称：");
        String inputName = InputLimit.InputString();

        AdminInfo adminInfo = daoAdminInfo.getAdminInfo(inputName);
        if (adminInfo==null){
            InputLimit.Warn("管理员名称输入错误，请重新输入！");
        } else {
            System.out.println("请输入该管理员的联系电话：");
            String inputTel = InputLimit.InputTel();

            String adminTel = adminInfo.getAdminTel();
            if (inputTel.equals(adminTel)){
                System.out.println("请输入新密码：");
                String operaPwd = InputLimit.InputString();

                System.out.println("请确认新密码：");
                String surePwd = InputLimit.InputString();

                if (operaPwd.equals(surePwd)){
                    daoAdminInfo.RetrievePwd(inputName, surePwd);
                } else {
                    InputLimit.Warn("密码修改失败，两次密码输入不一致！");
                }
            } else {
                InputLimit.Warn("联系电话输入错误，请重新输入！");
            }
        }

    }

    //查询所有的管理员
    /*public void QueryAllAdmin(){
        daoAdminInfo.AllAdmin();
    }*/

    //根据Id查询所有管理员
    /*public AdminInfo QueryAdminById(int i){
        AdminInfo adminInfo;
        adminInfo = daoAdminInfo.AllAdminById(i);
        return adminInfo;
    }*/
}
