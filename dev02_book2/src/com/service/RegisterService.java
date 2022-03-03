package com.service;

import com.entity.Admininfo;
import com.utils.GetTime;
import com.utils.InputLimit;
import com.utils.JDBCUtils;

import java.util.ArrayList;

public class RegisterService {
    /*
    * 管理员注册
    * */
    public static void AdminRegister(String s1, String s2){
        String sql = "SELECT * FROM admininfo WHERE adminName = ?";
        Admininfo admininfo = JDBCUtils.QuerySingle(Admininfo.class, sql, s1);
        if (admininfo==null) {
            /*String timeAsID = GetTime.getTimeAsID();
            String sql2 = "INSERT INTO admininfo VALUES(?,?,?)";
            int i = JDBCUtils.AddSingle(sql2, timeAsID, s1, s2);*/

            String sql2 = "INSERT INTO admininfo VALUES(null,?,?)";
            int i = JDBCUtils.ExecuteData(sql2, s1, s2);
            if (i > 0) {
                InputLimit.Notice("管理员注册成功！");
            } else {
                InputLimit.Warn("管理员注册失败！");
            }
        } else {
            InputLimit.Warn("该管理员已存在，请勿重复注册！");
        }
    }

    //查询所有管理员
    public static void AllAdmin(){
        String sql = "SELECT * FROM admininfo";
        ArrayList<Admininfo> admininfos = JDBCUtils.QueryMultiple(Admininfo.class, sql);
        admininfos.forEach(System.out::println);
    }

    //根据管理员Id查询管理员信息
    public static Admininfo AllAdminById(int i){
        Admininfo admininfo = null;
        String sql = "SELECT * FROM admininfo where adminId = ?";
        admininfo = JDBCUtils.QuerySingle(Admininfo.class, sql, i);

        return admininfo;
    }
}
