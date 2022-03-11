package com.implement;

/*
* 图书类型设置
* */
public interface BookTypeImpl {

    /**
     * 查看图书类别
     */
    void QueryBookType();

    /**
     * 添加图书类别
     * @param s 操作员姓名
     * @param bookType 图书类型
     */
    void AddBookType(String s, String bookType);

    /**
     * 删除图书类别
     * @param s  操作员姓名
     * @param type1 图书类别名称
     */
    void DeleteBookType(String s, String type1);

    /**
     * 修改图书类别
     * @param s 操作员姓名
     * @param type 被修改的图书类别名称
     * @param readInt 图书类别编号，根据该参数修改
     */
    void ModifyBookType(String s, String type, int readInt);

    /**
     * 检查该图书类别名称是否存在
     * @param name 图书类别名称
     * @return 返回boolean值
     */
    boolean HasBookTypeName(String name);

    /**
     * 遍历图书类别，找到输入编号的图书类别
     * @param BookTypeId 图书类别编号，根据该参数查找
     * @return 返回String类型数据
     */
    String GetBookTypeName(int BookTypeId);

    /**
     * 仅查询所有的图书编号+图书名称
     */
    void QueryBookTypeIdName();

    /**
     * 根据图书类别Id 检查是否存在该图书类别
     * @param bookTypeId 图书类别编号，根据该参数查找
     * @return 返回boolean值
     */
    boolean HasBookTypeId(int bookTypeId);

}
