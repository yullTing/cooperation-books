package com.dao;

import com.entity.AdminInfo;
import com.implement.AdminInfoImpl;
import com.service.LogService;
import com.utils.InputLimit;
import com.views.AdminView;

import java.util.ArrayList;

public class AdminInfoDAO extends BaseDAO<AdminInfo> implements AdminInfoImpl {

    /*@Override
    public void LoginAdmin(String inputName, String inputPwd) {
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";

        AdminInfo admininfo = doQueryOneData(sql, inputName);
        if (admininfo!=null) {
            String adminPwd = admininfo.getAdminPwd();
            if (adminPwd.equals(inputPwd)) {
                InputLimit.Notice("登录成功!");
                // 登录日志
                new LogService().ALL("管理员[" + inputName + "]登录系统");
                AdminView.AdminViewIndex(inputName);
            } else {
                InputLimit.Warn("登录失败，密码输入错误！");
            }
        } else {
            InputLimit.Warn("登录失败，该管理员不存在！");
        }
    }*/

    @Override
    public void RegisterAdmin(String s1, String s2) {
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";
        AdminInfo admininfo = doQueryOneData(sql, s1);
        if (admininfo==null) {

            String sql2 = "INSERT INTO admininfo VALUES(null,?,?)";
            int i = doUpdate(sql2, s1, s2);
            if (i > 0) {
                InputLimit.Notice("管理员注册成功！");
            } else {
                InputLimit.Warn("管理员注册失败！");
            }
        } else {
            InputLimit.Warn("该管理员已存在，请勿重复注册！");
        }
    }

    @Override
    public void AllAdmin() {
        String sql = "SELECT * FROM admininfo";
        ArrayList<AdminInfo> admininfos = doQueryResultList(sql);

        admininfos.forEach(System.out::println);
    }

    @Override
    public AdminInfo AllAdminById(int i) {
        AdminInfo admininfo = null;
        String sql = "SELECT * FROM admininfo where adminId = ?";
        admininfo = doQueryOneData(sql, i);

        return admininfo;
    }

    @Override
    public void RetrievePwd(String s1, String s2, String s3) {
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";
        AdminInfo admininfo = doQueryOneData(sql, s1);
        if (admininfo==null){
            InputLimit.Warn("密码修改失败，该管理员不存在！");
        } else {
            if (s2.equals(s3)){
                String sql2 = "UPDATE admininfo SET adminPwd = ? WHERE adminName = ?";
                int i = doUpdate(sql2, s2, s1);
                if (i > 0){
                    InputLimit.Notice("密码修改成功！");
                } else {
                    InputLimit.Warn("密码修改失败！");
                }
            } else {
                InputLimit.Warn("密码修改失败，两次密码输入不一致！");
            }
        }
    }
}
