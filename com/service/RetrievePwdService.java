package com.service;

import com.entity.Admininfo;
import com.utils.InputLimit;
import com.utils.JDBCUtils;

public class RetrievePwdService {
    /*
    * 忘记密码（修改密码）
    * */
    public static void RetrievePwd(String s1, String s2, String s3){
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";
        Admininfo admininfo = JDBCUtils.QuerySingle(Admininfo.class, sql, s1);
        if (admininfo==null){
            InputLimit.Warn("密码修改失败，该管理员不存在！");
        } else {
            if (s2.equals(s3)){
                String sql2 = "UPDATE admininfo SET adminPwd = ? WHERE adminName = ?";
                int i = JDBCUtils.ExecuteData(sql2, s2, s1);
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
