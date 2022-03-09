package com.implement;

import com.entity.BooksInfo;

import java.util.Date;

/*
* 图书信息管理
* */
public interface BooksInfoImpl {

    //添加图书信息
    void AddBooks(String s, String isbn, int typeid, String bookname, String author, String publish, Date publishdate, int publishtime, double unitprice);

    //查询所有的图书信息
    void QueryAllBooks();

    //根据图书名称查询单个图书信息
    BooksInfo QueryBookByBookName(String name);

    //查询所有图书的ISBN+名称
    void ShowBooksISBNName();

    //查询输入的ISBN是否正确，是否存在
    boolean HasBookInfoByISBN(String isbn);

    //根据ISBN获取图书价格
    double getBookPrice(String isbn);

    //修改图书信息
    void ModifyBooks(String s, String isbn, int type, String author,String publish, Date date1, int publishtime, double untiprice, String name);

    //删除图书信息
    void DeleteBooks (String s, String name);
}
