package com.factory;

import com.dao.BaseDAO;
import com.entity.OperatorInfo;
import com.implement.Identity;
import com.service.LogService;
import com.utils.InputLimit;
import com.views.OperatorView;

public class LoginByOperator extends BaseDAO<OperatorInfo> implements Identity {

    @Override
    public void LoginByIdentity(String inputName, String inputPwd) {
        String sql = "SELECT * FROM operinfo WHERE operName = ?";

        OperatorInfo operinfo = doQueryOneData(sql, inputName);
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
    }
}
