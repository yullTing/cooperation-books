package com.service;

import com.dao.BookTypeDAO;
import com.dao.BorrowReturnDAO;
import com.utils.DateUtils;
import com.utils.TSUtility;
import com.dao.BooksInfoDAO;
import com.utils.InputLimit;
import com.entity.BooksInfo;

import java.text.ParseException;
import java.util.Date;

public class BooksService {

    private final BooksInfoDAO daoBooks = new BooksInfoDAO();

    //添加图书信息
    public void AddBooksInfo(String s) {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要添加的图书名称:");
                String bookname = TSUtility.readKeyBoard(30, false);

                BooksInfo instance = daoBooks.QueryBookByBookName(bookname);
                if (instance != null) {
                    InputLimit.Warn("存在该数据！请重新输入！");
                    break;
                } else {
                    System.out.println("请输入你要添加的图书的ISBN（长度小于10，如tu23454）:");
                    String isbn = TSUtility.readKeyBoard(10, false);

                    //仅查询所有的图书类型编号+图书名称
                    new BookTypeDAO().QueryBookTypeIdName();
                    System.out.println("请选择上述图书类型编号进行添加:");
                    int typeid = InputLimit.InputNumber();//sc.nextInt();

                    boolean b1 = new BookTypeDAO().HasBookTypeId(typeid);
                    if (b1) {
                        System.out.println("请输入你要添加的图书作者:");
                        String author = TSUtility.readKeyBoard(30, false);

                        System.out.println("请输入你要添加的图书的出版社:");
                        String publish = TSUtility.readKeyBoard(30, false);
                        System.out.println("请输入你要添加的图书的出版日期(形式：yyyy-MM-dd):");
                        //String b = TSUtility.readKeyBoard(9,false);
                        String b = InputLimit.InputDate();

                        Date publishdate = DateUtils.stringToDate(b, "yyyy-MM-dd");
                        System.out.println("请输入你要添加的图书的印刷次数:");
                        int publishtime = InputLimit.InputNumber();//sc.nextInt();
                        System.out.println("请输入你要添加的图书的单价:");
                        double unitprice = InputLimit.InputDouble();//sc.nextDouble();

                        daoBooks.AddBooks(s, isbn, typeid, bookname, author, publish, publishdate, publishtime, unitprice);
                        flag = false;
                    } else {
                        InputLimit.Warn("图书类型编号输入错误，请重新输入！");
                        break;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //根据图书名称查询单个图书信息
    public void QueryBookInfoByBookName() {
        //显示所有图书信息
        daoBooks.QueryAllBooks();
        System.out.println("是否根据图书名称查询单个信息？（Y/N）");
        char c = TSUtility.readConfirmSelection();
        if (c == 'Y') {
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要查询的图书信息的名称:");
                String name = TSUtility.readKeyBoard(30, false);

                BooksInfo booksInfo = daoBooks.QueryBookByBookName(name);

                if (booksInfo == null) {
                    InputLimit.Warn("不存在该图书信息！请确认输入的图书名称是否正确！");
                } else {
                    System.out.println("你要查询的图书的信息为:");
                    System.out.println("ISBN\t\t" + "图书名称\t\t" + "图书类型编号\t\t" + "作者\t\t" + "出版社\t\t" + "出版日期\t\t" + "印刷次数\t\t" + "单价");
                    System.out.println(booksInfo.toString());
                }
                flag = false;
            }
        }
    }


    //修改图书信息
    public void ModifyBooksInfo(String s) {
        try {
            boolean flag = true;
            while (flag) {
                //显示所有图书信息
                daoBooks.QueryAllBooks();
                System.out.println("请输入你要修改的图书的名称:");
                String name = TSUtility.readKeyBoard(30, false);

                BooksInfo booksInfo = daoBooks.QueryBookByBookName(name);

                if (booksInfo == null) {
                    InputLimit.Warn("不存在该图书信息！请确认输入的图书名称是否正确！");
                    break;
                } else {
                    //仅查询所有的图书类型编号+图书名称
                    new BookTypeDAO().QueryBookTypeIdName();
                    System.out.println("请输入图书类型的编号（" + booksInfo.getTypeid() + "）:");
                    int type = InputLimit.ModifyInt(booksInfo.getTypeid());

                    boolean b1 = new BookTypeDAO().HasBookTypeId(type);
                    if (b1) {
                        System.out.println("请输入图书ISBN（" + booksInfo.getISBN() + "）:");
                        String isbn = TSUtility.readString(8, booksInfo.getISBN());

                        System.out.println("请输入图书的作者（" + booksInfo.getAuthor() + "）:");
                        String author = TSUtility.readString(30, booksInfo.getAuthor());

                        System.out.println("请输入图书的出版社（" + booksInfo.getPublish() + "）:");
                        String publish = TSUtility.readString(30, booksInfo.getPublish());

                        Date date = booksInfo.getPublishdate();
                        String s1 = DateUtils.dateToString(date, "yyyy-MM-dd");
                        System.out.println("请输入图书的出版日期（" + s1 + "）:");
                        //String d = TSUtility.readString(9, s1);
                        String d = InputLimit.ModifyDate(s1);
                        Date date1 = DateUtils.stringToDate(d, "yyyy-MM-dd");

                        System.out.println("请输入图书的印刷次数（" + booksInfo.getPublishtime() + "）:");
                        String dept = TSUtility.readString(5, String.valueOf(booksInfo.getPublishtime()));
                        int publishtime = Integer.parseInt(dept);
                        System.out.println("请输入图书的单价（" + booksInfo.getUnitprice() + "）:");
                        String dt = TSUtility.readString(10, String.valueOf(booksInfo.getUnitprice()));
                        double untiprice = Double.parseDouble(dt);

                        daoBooks.ModifyBooks(s, isbn, type, author, publish, date1, publishtime, untiprice, name);
                        flag = false;
                    } else {
                        InputLimit.Warn("图书类型编号输入错误，请重新输入！");
                        break;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //删除图书信息
    public void DeleteBooksInfo(String s) {
        //显示所有图书信息
        daoBooks.QueryAllBooks();
        System.out.println("请输入你要删除的图书名称:");
        String name = TSUtility.readKeyBoard(30, false);
        //根据图书名查询是否存在该图书信息
        BooksInfo booksInfo = daoBooks.QueryBookByBookName(name);
        if (booksInfo == null) {
            InputLimit.Warn("不存在该图书信息！");
        } else {
            boolean noISBN = new BorrowReturnDAO().HasBookByISBN(booksInfo.getISBN());

            if (noISBN) {
                daoBooks.DeleteBooks(s, name);
            } else {
                InputLimit.Warn("该读者有图书未归还，无法删除！");
            }
        }
    }
}
