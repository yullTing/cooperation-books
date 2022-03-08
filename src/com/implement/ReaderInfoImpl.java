package com.implement;

import com.entity.ReaderInfo;

import java.util.Date;

public interface ReaderInfoImpl {

    //添加读者信息
    void AddReader(String s, String number, int type, String name, int age, String sex, String phone, String dept, Date date);

    //根据Id查询读者信息
    ReaderInfo QueryReaderByReaderId(String number);

    //查询所有读者信息
    void QueryAllReader();

    //修改读者信息
    void ModifyReader(String s, int type, String name, int age, String sex, String phone, String dept, Date date, String number);

    //删除读者信息
    void DeleteReader(String s, String number);
}
