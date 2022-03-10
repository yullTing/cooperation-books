package com.implement;

import com.entity.ReaderType;

import java.util.List;

/*
* 读者类别设置
* */
public interface ReaderTypeImpl {

    //查看读者类别
    void QueryBookType();

    //添加读者类别
    void AddReaderType(String s, String readerType);

    //删除读者类别
    void DeleteReaderType(String s, String type1);

    //修改读者类别
    void ModifyReaderType(String s, String type, int limit, int maxNum, int readInt);

    //检查是否存在该读者类别
    boolean HasBookTypeName(String typeName);

    //检查读者类别Id是否输入错误，是否存在
    boolean HasBookTypeId(int readerTypeId);

    //查询读者类型，仅显示类型编号、类型名称
    void ShowReaderTypeIdName();

    //获取罚金设置信息
    List<ReaderType>  showPenaltyInfo();

    //查询或者修改罚金设置
    void QueryModifyPenaltySetting(double aDouble, String name);

    //根据读者类型编号 查询可借阅的最多图书数量
    ReaderType getRTByReaderTypeId(int typeId);

}
