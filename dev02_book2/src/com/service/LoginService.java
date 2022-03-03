package com.service;

import com.entity.Admininfo;
import com.entity.Operinfo;
import com.utils.InputLimit;
import com.utils.JDBCUtils;
import com.views.AdminView;


public class LoginService {
    /*
    * 登录
    * */
    //管理员登录
    public static void LoginAdmin(String s1, String s2){
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";

        Admininfo admininfo = JDBCUtils.QuerySingle(Admininfo.class, sql, s1);
        if (admininfo!=null) {
            String adminPwd = admininfo.getAdminPwd();
            if (adminPwd.equals(s2)) {
                InputLimit.Notice("登录成功!");
                // 登录日志
                LogService.AddLoginLog("管理员[" + s1 + "]登录系统");
                AdminView.AdminViewIndex(s1);
            } else {
                InputLimit.Warn("登录失败，密码输入错误！");
            }
        } else {
            InputLimit.Warn("登录失败，该管理员不存在！");
        }
    }

    //操作员登录
    public static void LoginOper(String s1, String s2){
        String sql = "SELECT * FROM operinfo WHERE operName = ?";

        Operinfo operinfo = JDBCUtils.QuerySingle(Operinfo.class, sql, s1);
        if (operinfo!=null) {
            String adminPwd = operinfo.getOperPwd();
            if (adminPwd.equals(s2)) {
                InputLimit.Notice("登录成功!");
                // 登录日志
                LogService.AddLoginLog("操作员[" + s1 + "]登录系统");
                System.out.println("操作员界面");
            } else {
                InputLimit.Warn("登录失败，密码输入错误！");
            }
        } else {
            InputLimit.Warn("登录失败，该操作员不存在！");
        }
    }
}
