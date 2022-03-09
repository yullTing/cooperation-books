package com.dao;

import com.entity.BookType;
import com.implement.BookTypeImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.ArrayList;
import java.util.List;

public class BookTypeDAO extends BaseDAO<BookType> implements BookTypeImpl {
    @Override
    public void QueryBookType() {
        String sql = "select * from `booktype` ";
        List<BookType> bookTypeList = doQueryResultList(sql);

        System.out.println("图书类型编号" + "\t\t图书类型名称");
        bookTypeList.forEach(System.out::println);
    }

    @Override
    public void AddBookType(String s, String bookType) {
        String sql1 = "insert into `booktype` set typename=?";
        doUpdate(sql1, bookType);
        InputLimit.Notice("添加成功！");
        new LogService().AOL("操作员[" + s + "]添加图书类型" + bookType);
    }

    @Override
    public void DeleteBookType(String s, String type1) {
        String sql1 = "delete from booktype where typename=?";
        doUpdate(sql1, type1);
        InputLimit.Notice("删除成功！");
        new LogService().AOL("操作员[" + s + "]删除图书类型" + type1);
    }

    @Override
    public void ModifyBookType(String s, String type, int readInt) {
        String sql1 = "update booktype set typename=? where id=?";
        doUpdate(sql1, type, readInt);
        InputLimit.Notice("修改成功！");
        new LogService().AOL("操作员[" + s + "]修改图书类型" + type);
    }

    @Override
    public boolean HasBookTypeName(String name) {
        boolean flag = true;
        String sql = "select typename from `booktype` ";
        List<BookType> bookTypeList = doQueryResultList(sql);

        for (BookType bt : bookTypeList) {
            if (bt.getTypename().equals(name)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public String GetBookTypeName(int BookTypeId) {
        String result = null;
        String sql = "select * from `booktype` ";
        List<BookType> bookTypeList = doQueryResultList(sql);
        for (BookType bt : bookTypeList) {
            if (bt.getId() == BookTypeId) {
                result = bt.getTypename();
            }
        }
        return result;
    }

    @Override
    public void QueryBookTypeIdName() {
        String sql1 = "select id,typename from booktype";
        ArrayList<BookType> bookTypeList = doQueryResultList(sql1);

        if (bookTypeList.size() != 0) {
            System.out.println("图书编号\t\t" + "图书名称");
            bookTypeList.forEach(System.out::println);
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    //根据图书类别Id 检查是否存在该图书类别
    @Override
    public boolean HasBookTypeId(int bookTypeId) {
        boolean hasBookTypeId = false;
        String sql = "select * from booktype where id = ?";
        BookType bookType = doQueryOneData(sql, bookTypeId);

        if (bookType != null) {
            hasBookTypeId = true;
        }
        return hasBookTypeId;
    }
}
