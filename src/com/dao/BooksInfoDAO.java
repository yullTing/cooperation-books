package com.dao;

import com.entity.BooksInfo;
import com.implement.BooksInfoImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BooksInfoDAO extends BaseDAO<BooksInfo> implements BooksInfoImpl {

    @Override
    public void AddBooks(String s, String isbn, int typeid, String bookname, String author, String publish, Date publishdate, int publishtime,double unitprice) {
        String sql2 = "insert into `bookinformation` values (?,?,?,?,?,?,?,?)";
        doUpdate(sql2, isbn, typeid, bookname, author, publish, publishdate, publishtime, unitprice);
        InputLimit.Notice("图书信息添加成功！");
        new LogService().AOL("操作员[" + s + "]添加图书" + bookname + "");
    }

    @Override
    public void QueryAllBooks() {
        String sql = "select * from bookinformation ";
        List<BooksInfo> booksInfoList = doQueryResultList(sql);

        if (booksInfoList.size() != 0) {
            System.out.println("ISBN\t\t" + "图书名称\t\t" + "图书类型编号\t\t" + "作者\t\t" + "出版社\t\t" + "出版日期\t\t" + "印刷次数\t\t" + "单价");
            booksInfoList.forEach(System.out::println);
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public BooksInfo QueryBookByBookName(String name) {
        BooksInfo instance = null;
        String sql = "select * from `bookinformation` where `bookname`=?";
        instance = doQueryOneData(sql, name);

        return instance;
    }

    @Override
    public void ShowBooksISBNName() {
        String sql = "select ISBN,bookname from `bookinformation` ";
        List<BooksInfo> booksInfoList = doQueryResultList(sql);

        if (booksInfoList.size() != 0) {
            System.out.println("图书名称\t\t" + "ISBN");
            for (BooksInfo bl : booksInfoList) {
                System.out.println(bl.getBookname() + " \t\t" + bl.getISBN());
            }
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public boolean HasBookInfoByISBN(String isbn) {
        boolean flag = true;
        String sql = "select ISBN,bookname from `bookinformation` where ISBN=?";
        BooksInfo instance = doQueryOneData(sql, isbn);
        if (instance == null) {
            flag = false;
        }
        return flag;
    }

    @Override
    public double getBookPrice(String isbn) {
        double bookPrice;
        String sql = "SELECT unitprice FROM bookinformation WHERE ISBN = ?";
        BooksInfo instance = doQueryOneData(sql, isbn);
        bookPrice = instance.getUnitprice();

        return bookPrice;
    }

    @Override
    public void ModifyBooks(String s, String isbn, int type, String author,String publish, Date date1, int publishtime, double untiprice, String name) {
        String sql1 = "update `bookinformation` set ISBN = ?,typeid=?,author=?,publish=?,publishdate=?,publishtime=?,unitprice=? where `bookname`=?";
        doUpdate(sql1, isbn, type, author, publish, date1, publishtime, untiprice, name);
        new LogService().AOL("操作员[" + s + "]修改图书" + name + "");
    }

    @Override
    public void DeleteBooks(String s, String name) {
        String sql2 = "delete from bookinformation where `bookname`=?";
        doUpdate(sql2, name);
        new LogService().AOL("操作员[" + s + "]删除图书" + name + "");
    }
}
