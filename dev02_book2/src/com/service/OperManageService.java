package com.service;

import com.entity.Admininfo;
import com.entity.Operinfo;
import com.utils.InputLimit;
import com.utils.JDBCUtils;

import java.util.ArrayList;

public class OperManageService {
    /*
    * 员工信息管理
    * */
    //操作员信息添加
    public static void AddOper(String s1, String s2, String s3){
        String sql = "SELECT * FROM operinfo WHERE operName = ?";
        Operinfo operinfo = JDBCUtils.QuerySingle(Operinfo.class, sql, s1);
        if (operinfo==null) {
            //获取管理员ID
            String sql2 = "SELECT adminId FROM admininfo WHERE adminName = ?";
            Admininfo admininfo = JDBCUtils.QuerySingle(Admininfo.class, sql2, s3);
            int adminId = admininfo.getAdminId();
            //添加操作员
            String sql3 = "INSERT INTO operinfo VALUES(null,?,?,?)";
            int i = JDBCUtils.ExecuteData(sql3, s1, s2, adminId);
            if (i > 0) {
                InputLimit.Notice("操作员添加成功！");
            } else {
                InputLimit.Warn("操作员添加失败！");
            }
        } else {
            InputLimit.Warn("该操作员已存在，请勿重复添加！");
        }
    }

    //操作员信息查询
    public static ArrayList<Operinfo> QueryOper(){
        ArrayList<Operinfo> operinfos = null;
        String sql = "SELECT * FROM operinfo";
        operinfos = JDBCUtils.QueryMultiple(Operinfo.class, sql);
        if (operinfos!=null) {
            operinfos.forEach(System.out::println);
        } else {
            InputLimit.Warn("无任何操作员信息！");
        }
        return operinfos;
    }

    //操作员信息修改
    public static void UpdateOper(int i1, String s1, String s2, int i2){
        String sql = "UPDATE operinfo SET operName = ?, operPwd = ?, adminId = ? WHERE operId = ?";
        int i = JDBCUtils.ExecuteData(sql, s1, s2, i2, i1);
        if (i < 0) {
            InputLimit.Warn("操作员信息修改失败！");
        } else {
            InputLimit.Notice("操作员信息修改成功！");
        }
    }

    //操作员信息删除
    public static void DeleteOper(int i){
        String sql = "DELETE FROM operinfo WHERE operId = ?";
        int i1 = JDBCUtils.ExecuteData(sql, i);
        if (i1 > 0){
            InputLimit.Notice("该操作员信息删除成功！");
        } else {
            InputLimit.Warn("该操作员信息删除失败！");
        }
    }

    //精确查询：根据此时员工列表的编号查询员工信息
    public static Operinfo QueryOperById(int i){
        Operinfo operinfo = null;
        String sql = "SELECT * FROM operinfo where operId = ?";
        operinfo = JDBCUtils.QuerySingle(Operinfo.class, sql, i);

        return operinfo;
    }
}
