package com.factory;

import com.dao.BaseDAO;
import com.entity.OperatorInfo;
import com.implement.Identity;
import com.service.LogService;
import com.utils.InputLimit;
import com.views.OperatorView;

public class LoginByOperator extends BaseDAO<OperatorInfo> implements Identity {

    @Override
    public void LoginByIdentity() {
        System.out.println("请输入操作员名称：");
        String inputName = InputLimit.InputString();

        String sql = "SELECT * FROM operinfo WHERE operName = ?";
        OperatorInfo operaInfo = doQueryOneData(sql, inputName);
        if (operaInfo==null) {
            InputLimit.Warn("登录失败，该操作员不存在！");
        } else {
            System.out.println("请输入登录密码：");
            String inputPwd = InputLimit.InputString();

            String operaPwd = operaInfo.getOperPwd();
            if (operaPwd.equals(inputPwd)) {
                InputLimit.Notice("登录成功!");
                // 登录日志
                new LogService().ALL("操作员[" + inputName + "]登录系统");
                //System.out.println("操作员界面");

                OperatorView.operator(inputName);
            } else {
                InputLimit.Warn("登录失败，密码输入错误！");
            }
        }
    }
}
