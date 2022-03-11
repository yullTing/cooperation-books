package com.dao;

import com.entity.AdminInfo;
import com.implement.AdminInfoImpl;
import com.utils.InputLimit;

import java.util.ArrayList;

public class AdminInfoDAO extends BaseDAO<AdminInfo> implements AdminInfoImpl {

    @Override
    public AdminInfo getAdminInfo(String inputName) {
        AdminInfo admininfo;
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";
        admininfo = doQueryOneData(sql, inputName);

        return admininfo;
    }

    @Override
    public void RegisterAdmin(String inputName, String inputPwd, String inputTel) {
        String sql = "INSERT INTO admininfo VALUES(null,?,?,?)";
        int i = doUpdate(sql, inputName, inputPwd, inputTel);
        if (i > 0) {
            InputLimit.Notice("管理员注册成功！");
        } else {
            InputLimit.Warn("管理员注册失败！");
        }
    }

    @Override
    public void AllAdmin() {
        String sql = "SELECT * FROM admininfo";
        ArrayList<AdminInfo> adminInfos = doQueryResultList(sql);

        adminInfos.forEach(System.out::println);
    }

    @Override
    public AdminInfo AllAdminById(int i) {
        AdminInfo admininfo = null;
        String sql = "SELECT * FROM admininfo where adminId = ?";
        admininfo = doQueryOneData(sql, i);

        return admininfo;
    }

    @Override
    public void RetrievePwd(String adminName, String newPwd) {
        String sql2 = "UPDATE admininfo SET adminPwd = ? WHERE adminName = ?";
        int i = doUpdate(sql2, newPwd, adminName);
        if (i > 0){
            InputLimit.Notice("密码修改成功！");
        } else {
            InputLimit.Warn("密码修改失败！");
        }
    }
}
