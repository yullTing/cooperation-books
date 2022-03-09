package com.dao;

import com.entity.OperInfo;
import com.implement.OperManageImpl;
import com.service.LogService;
import com.utils.InputLimit;
import com.views.OperatorView;

import java.util.ArrayList;

public class OperManageDAO extends BaseDAO<OperInfo> implements OperManageImpl {
    @Override
    public void LoginOper(String operName, String operPwd) {
        String sql = "SELECT * FROM operinfo WHERE operName = ?";

        OperInfo operinfo = doQueryOneData(sql, operName);
        if (operinfo!=null) {
            String adminPwd = operinfo.getOperPwd();
            if (adminPwd.equals(operPwd)) {
                InputLimit.Notice("登录成功!");
                // 登录日志
                new LogService().ALL("操作员[" + operName + "]登录系统");
                //System.out.println("操作员界面");

                OperatorView.operator(operName);
            } else {
                InputLimit.Warn("登录失败，密码输入错误！");
            }
        } else {
            InputLimit.Warn("登录失败，该操作员不存在！");
        }
    }

    @Override
    public void AddOper(String s1, String s2, String s3) {
        String sql = "SELECT * FROM operinfo WHERE operName = ?";
        OperInfo operinfo = doQueryOneData(sql, s1);
        if (operinfo == null) {
            //获取管理员ID
            String sql2 = "SELECT adminId FROM admininfo WHERE adminName = ?";
            OperInfo admininfo = doQueryOneData(sql2, s3);
            int adminId = admininfo.getAdminId();
            //添加操作员
            String sql3 = "INSERT INTO operinfo VALUES(null,?,?,?)";
            int i = doUpdate(sql3, s1, s2, adminId);
            if (i > 0) {
                InputLimit.Notice("操作员添加成功！");
                new LogService().AOL("管理员[" + s3 + "]添加操作员信息[" + s1 + "]");
            } else {
                InputLimit.Warn("操作员添加失败！");
            }
        } else {
            InputLimit.Warn("该操作员已存在，请勿重复添加！");
        }
    }

    @Override
    public ArrayList<OperInfo> QueryOper() {
        ArrayList<OperInfo> operinfos;
        String sql = "SELECT * FROM operinfo";
        operinfos = doQueryResultList(sql);
        if (operinfos.size() != 0) {
            System.out.println("操作员编号\t 操作员姓名\t 归属管理员Id" );
            operinfos.forEach(System.out::println);
        } else {
            InputLimit.Warn("无任何操作员信息！");
        }
        return operinfos;
    }

    @Override
    public void UpdateOper(int i1, String s1, String s2, int i2, String s) {
        String sql = "UPDATE operinfo SET operName = ?, operPwd = ?, adminId = ? WHERE operId = ?";
        int i = doUpdate(sql, s1, s2, i2, i1);
        if (i < 0) {
            InputLimit.Warn("操作员信息修改失败！");
        } else {
            InputLimit.Notice("操作员信息修改成功！");
            new LogService().AOL("管理员[" + s + "]修改操作员信息[" + s1 + "]");
        }
    }

    @Override
    public void DeleteOper(int i, String s1, String s) {
        String sql = "DELETE FROM operinfo WHERE operId = ?";
        int i1 = doUpdate(sql, i);
        if (i1 > 0) {
            InputLimit.Notice("该操作员信息删除成功！");
            new LogService().AOL("管理员[" + s + "]添加操作员信息[" + s1 + "]");
        } else {
            InputLimit.Warn("该操作员信息删除失败！");
        }
    }

    @Override
    public OperInfo QueryOperById(int i) {
        OperInfo operinfo;
        String sql = "SELECT * FROM operinfo where operId = ?";
        operinfo = doQueryOneData(sql, i);

        return operinfo;
    }
}
