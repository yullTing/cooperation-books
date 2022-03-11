package com.implement;

import com.entity.OperatorInfo;

import java.util.ArrayList;

/*
 * 员工信息管理
 * */
public interface OperatorManageImpl {

    //操作员登录
    /*void LoginOper(String operName, String operPwd);*/

    /**
     * 添加操作员信息
     * @param operaName 添加的操作员姓名
     * @param operaPwd 操作员登录密码
     * @param adminName 管理员姓名
     */
    void AddOpera(String operaName, String operaPwd, String adminName);

    /**
     * 操作员信息查询
     * @return 返回操作员信息集合
     */
    ArrayList<OperatorInfo> QueryOpera();

    /**
     * 操作员信息修改
     * @param operaId 操作员编号
     * @param operaName 操作员姓名
     * @param operaPwd 操作员登录密码
     * @param adminId 所属管理员编号
     * @param s 执行该操作的管理员姓名
     */
    void UpdateOpera(int operaId, String operaName, String operaPwd, int adminId, String s);

    /**
     * 操作员信息删除
     * @param operaId 操作员编号
     * @param operaName 操作员姓名
     * @param s 执行该操作的管理员姓名
     */
    void DeleteOpera(int operaId, String operaName, String s);

    /**
     * 精确查询：根据此时员工列表的编号查询员工信息
     * @param i 操作员编号
     * @return 返回一条操作员信息
     */
    OperatorInfo QueryOperaById(int i);
}

