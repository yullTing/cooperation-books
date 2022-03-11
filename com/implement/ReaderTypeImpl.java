package com.implement;

import com.entity.ReaderType;

import java.util.List;

/*
* 读者类别设置
* */
public interface ReaderTypeImpl {

    /**
     * 查看读者类别
     */
    void QueryBookType();

    /**
     * 添加读者类别
     * @param s 操作员姓名
     * @param readerTypeName 读者类别名称
     */
    void AddReaderType(String s, String readerTypeName, int maxBorrowNum, int limitDays);

    /**
     * 删除读者类别
     * @param s 操作员姓名
     * @param type1 读者类别名称
     */
    void DeleteReaderType(String s, String type1);

    /**
     * 修改读者类别
     * @param s 操作员姓名
     * @param type 读者类别名称
     * @param limit 最多借书天数
     * @param maxNum 最多借书数量
     * @param readInt 读者类别编号
     */
    void ModifyReaderType(String s, String type, int limit, int maxNum, int readInt);

    /**
     * 检查是否存在该读者类别
     * @param typeName 读者类别名称
     * @return 返回boolean值
     */
    boolean HasBookTypeName(String typeName);

    /**
     * 检查读者类别Id是否输入错误，是否存在
     * @param readerTypeId 读者类别编号
     * @return 返回boolean值
     */
    boolean HasBookTypeId(int readerTypeId);

    /**
     * 查询读者类型，仅显示类型编号、类型名称
     */
    void ShowReaderTypeIdName();

    /**
     * 获取罚金设置信息
     * @return 返回读者类别信息集合
     */
    List<ReaderType>  showPenaltyInfo();

    /**
     * 查询或者修改罚金设置
     * @param aDouble 该类读者的逾期每天罚款金额
     * @param name 读者类别名称
     */
    void QueryModifyPenaltySetting(double aDouble, String name);

    /**
     * 根据读者类型编号 查询可借阅的最多图书数量
     * @param typeId 读者类别编号
     * @return 返回一条读者类别信息
     */
    ReaderType getRTByReaderTypeId(int typeId);

}
