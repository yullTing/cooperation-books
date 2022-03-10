package com.implement;

import com.entity.OperatorInfo;

import java.util.ArrayList;

/*
 * 员工信息管理
 * */
public interface OperatorManageImpl {

    //操作员登录
    /*void LoginOper(String operName, String operPwd);*/

    //操作员信息添加
    void AddOper(String s1, String s2, String s3);

    //操作员信息查询
    ArrayList<OperatorInfo> QueryOper();

    //操作员信息修改
    void UpdateOper(int i1, String s1, String s2, int i2, String s);

    //操作员信息删除
    void DeleteOper(int i, String s1, String s);

    //精确查询：根据此时员工列表的编号查询员工信息
    OperatorInfo QueryOperById(int i);
}

