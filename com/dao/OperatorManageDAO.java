package com.dao;

import com.entity.OperatorInfo;
import com.implement.OperatorManageImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.ArrayList;

public class OperatorManageDAO extends BaseDAO<OperatorInfo> implements OperatorManageImpl {
    /*@Override
    public void LoginOper(String inputName, String inputPwd) {
        String sql = "SELECT * FROM operinfo WHERE operName = ?";

        OperInfo operinfo = doQueryOneData(sql, inputName);
        if (operinfo!=null) {
            String operPwd = operinfo.getOperPwd();
            if (operPwd.equals(inputPwd)) {
                InputLimit.Notice("登录成功!");
                // 登录日志
                new LogService().ALL("操作员[" + inputName + "]登录系统");
                //System.out.println("操作员界面");

                OperatorView.operator(inputName);
            } else {
                InputLimit.Warn("登录失败，密码输入错误！");
            }
        } else {
            InputLimit.Warn("登录失败，该操作员不存在！");
        }
    }*/

    @Override
    public void AddOpera(String operaName, String operaPwd, String adminName) {
        String sql = "SELECT * FROM operinfo WHERE operName = ?";
        OperatorInfo operaInfo = doQueryOneData(sql, operaName);
        if (operaInfo == null) {
            //获取管理员ID
            String sql2 = "SELECT adminId FROM admininfo WHERE adminName = ?";
            OperatorInfo adminInfo = doQueryOneData(sql2, adminName);
            int adminId = adminInfo.getAdminId();
            //添加操作员
            String sql3 = "INSERT INTO operinfo VALUES(null,?,?,?)";
            int i = doUpdate(sql3, operaName, operaPwd, adminId);
            if (i > 0) {
                InputLimit.Notice("操作员添加成功！");
                new LogService().AOL("管理员[" + adminName + "]添加操作员信息[" + operaName + "]");
            } else {
                InputLimit.Warn("操作员添加失败！");
            }
        } else {
            InputLimit.Warn("该操作员已存在，请勿重复添加！");
        }
    }

    @Override
    public ArrayList<OperatorInfo> QueryOpera() {
        ArrayList<OperatorInfo> operaInfo;
        String sql = "SELECT * FROM operinfo";
        operaInfo = doQueryResultList(sql);
        if (operaInfo.size() != 0) {
            System.out.println("操作员编号\t 操作员姓名\t 归属管理员Id" );
            operaInfo.forEach(System.out::println);
        } else {
            InputLimit.Warn("无任何操作员信息！");
        }
        return operaInfo;
    }

    @Override
    public void UpdateOpera(int operaId, String operaName, String operaPwd, int adminId, String s) {
        String sql = "UPDATE operinfo SET operName = ?, operPwd = ?, adminId = ? WHERE operId = ?";
        int i = doUpdate(sql, operaName, operaPwd, adminId, operaId);
        if (i < 0) {
            InputLimit.Warn("操作员信息修改失败！");
        } else {
            InputLimit.Notice("操作员信息修改成功！");
            new LogService().AOL("管理员[" + s + "]修改操作员信息[" + operaName + "]");
        }
    }

    @Override
    public void DeleteOpera(int operaId, String operaName, String s) {
        String sql = "DELETE FROM operinfo WHERE operId = ?";
        int i1 = doUpdate(sql, operaId);
        if (i1 > 0) {
            InputLimit.Notice("该操作员信息删除成功！");
            new LogService().AOL("管理员[" + s + "]添加操作员信息[" + operaName + "]");
        } else {
            InputLimit.Warn("该操作员信息删除失败！");
        }
    }

    @Override
    public OperatorInfo QueryOperaById(int i) {
        OperatorInfo operinfo;
        String sql = "SELECT * FROM operinfo where operId = ?";
        operinfo = doQueryOneData(sql, i);

        return operinfo;
    }
}
