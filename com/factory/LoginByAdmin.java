package com.factory;

import com.dao.BaseDAO;
import com.entity.AdminInfo;
import com.implement.Identity;
import com.service.LogService;
import com.utils.InputLimit;
import com.views.AdminView;

public class LoginByAdmin extends BaseDAO<AdminInfo> implements Identity {

    @Override
    public void LoginByIdentity(String inputName, String inputPwd) {
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
    }
}
