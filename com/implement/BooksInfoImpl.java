package com.implement;

import com.entity.BooksInfo;

import java.util.Date;

/*
* 图书信息管理
* */
public interface BooksInfoImpl {

    /**
     * 添加图书信息
     * @param s 操作员姓名
     * @param isbn 图书ISBN
     * @param typeid 图书类别编号
     * @param bookname 图书名称
     * @param author 作者
     * @param publish 出版社
     * @param publishdate 出版时间
     * @param publishtime 印刷次数
     * @param unitprice 价格
     */
    void AddBooks(String s, String isbn, int typeid, String bookname, String author, String publish, Date publishdate, int publishtime, double unitprice);

    /**
     * 查询所有的图书信息
     */
    void QueryAllBooks();

    /**
     * 根据图书名称查询单个图书信息
     * @param name 图书名称，根据该参数查询
     * @return 返回一条图书信息
     */
    BooksInfo QueryBookByBookName(String name);

    /**
     * 查询所有图书的ISBN+名称
     */
    void ShowBooksISBNName();

    /**
     * 查询输入的ISBN是否正确，是否存在
     * @param isbn 图书ISBN，根据该参数查询
     * @return 返回boolean值
     */
    boolean HasBookInfoByISBN(String isbn);

    /**
     * 根据ISBN获取图书价格
     * @param isbn 图书ISBN，根据该参数查询
     * @return 返回double值
     */
    double getBookPrice(String isbn);

    /**
     * 修改图书信息
     * @param s 操作员姓名
     * @param isbn 图书ISBN
     * @param type 图书类别编号
     * @param author 作者
     * @param publish 出版社
     * @param date1 出版时间
     * @param publishtime 印刷次数
     * @param untiprice 价格
     * @param name 图书名称，根据该参数修改
     */
    void ModifyBooks(String s, String isbn, int type, String author,String publish, Date date1, int publishtime, double untiprice, String name);

    /**
     * 删除图书信息
     * @param s 操作员姓名
     * @param name 图书名称，根据该参数删除
     */
    void DeleteBooks (String s, String name);

    /**
     * 借阅图书时改变图书状态，将图书变为不可借阅状态（false）
     * @param s 操作员姓名
     * @param isbn 图书ISBN，根据该参数修改
     */
    void SetStateFalse(String s, String isbn);

    /**
     * 归还图书时改变图书状态，将图书变为可以借阅状态（true）
     * @param s 操作员姓名
     * @param isbn 图书ISBN，根据该参数修改
     */
    void SetStateTrue(String s, String isbn);

    /**
     * 确定图书状态，并返回
     * @param isbn 图书ISBN，根据该参数查询
     * @return 返回boolean值
     */
    boolean ConfirmBookState(String isbn);
}
