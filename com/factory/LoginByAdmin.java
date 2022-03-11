package com.factory;

import com.dao.BaseDAO;
import com.entity.AdminInfo;
import com.implement.Identity;
import com.service.LogService;
import com.utils.InputLimit;
import com.views.AdminView;

public class LoginByAdmin extends BaseDAO<AdminInfo> implements Identity {

    @Override
    public void LoginByIdentity() {
        System.out.println("请输入管理员名称：");
        String inputName = InputLimit.InputString();

        String sql = "SELECT * FROM admininfo WHERE adminName = ?";
        AdminInfo adminInfo = doQueryOneData(sql, inputName);
        if (adminInfo==null){
            InputLimit.Warn("管理员名称输入错误，请重新输入！");
        } else {
            System.out.println("请输入登录密码：");
            String inputPwd = InputLimit.InputString();

            String adminPwd = adminInfo.getAdminPwd();
            if (adminPwd.equals(inputPwd)){
                // 登录日志
                new LogService().ALL("管理员[" + inputName + "]登录系统");
                AdminView.AdminViewIndex(inputName);
            } else {
                InputLimit.Warn("密码输入错误，请重新输入！");
            }
        }
    }
}
