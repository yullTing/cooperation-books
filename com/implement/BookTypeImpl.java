package com.implement;

/*
* 图书类型设置
* */
public interface BookTypeImpl {

    //查看图书类别
    void QueryBookType();

    //添加图书类别
    void AddBookType(String s, String bookType);

    //删除图书类别
    void DeleteBookType(String s, String type1);

    //修改图书类别
    void ModifyBookType(String s, String type, int readInt);

    //检查该图书类别名称是否存在
    boolean HasBookTypeName(String name);

    //遍历图书类别，找到输入编号的图书类别
    String GetBookTypeName(int BookTypeId);

    //仅查询所有的图书编号+图书名称
    void QueryBookTypeIdName();

    //根据图书类别Id 检查是否存在该图书类别
    boolean HasBookTypeId(int bookTypeId);

}
