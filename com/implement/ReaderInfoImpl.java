package com.implement;

import com.entity.ReaderInfo;

import java.util.Date;

public interface ReaderInfoImpl {

    /**
     * 添加读者信息
     * @param s 操作员姓名
     * @param number 读者编号
     * @param type 读者类别
     * @param name 读者姓名
     * @param age 读者年龄
     * @param sex 读者性别
     * @param phone 联系电话
     * @param dept 所在系部
     * @param date 注册时间
     */
    void AddReader(String s, String number, int type, String name, int age, String sex, String phone, String dept, Date date);

    /**
     * 根据Id查询读者信息
     * @param number 读者编号
     * @return 返回一条读者信息
     */
    ReaderInfo QueryReaderByReaderId(String number);

    /**
     * 查询所有读者信息
     */
    void QueryAllReader();

    /**
     * 修改读者信息
     * @param s 操作员姓名
     * @param type 读者类别
     * @param name 读者姓名
     * @param age 读者年龄
     * @param sex 读者性别
     * @param phone 联系电话
     * @param dept 所在系部
     * @param date 注册时间
     * @param number 读者编号
     */
    void ModifyReader(String s, int type, String name, int age, String sex, String phone, String dept, Date date, String number);

    /**
     * 删除读者信息
     * @param s 操作员姓名
     * @param number 读者编号
     */
    void DeleteReader(String s, String number);

    /**
     * 查询读者编号、类型、名称
     */
    void QueryReaderIdTypeName();
}
