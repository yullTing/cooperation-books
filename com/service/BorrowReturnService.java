package com.service;

import com.dao.*;
import com.entity.BorrowReturn;
import com.entity.ReaderInfo;
import com.entity.ReaderType;
import com.implement.BorrowReturnImpl;
import com.intermediary.Intermediary;
import com.utils.DateUtils;
import com.utils.InputLimit;
import com.utils.TSUtility;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BorrowReturnService {

    //private final BorrowReturnDAO daoBorrowReturn = new BorrowReturnDAO();

    BorrowReturnImpl host = new BorrowReturnDAO();
    BorrowReturnImpl intermediary = (BorrowReturnImpl)new Intermediary(host).getProxyInstance();


    public void Borrow(String s) {
        System.out.println("请输入读者编号:");
        String number = TSUtility.readKeyBoard(8, false);

        ReaderInfo readerInfo = new ReaderInfoDAO().QueryReaderByReaderId(number);

        if (readerInfo == null) {
            InputLimit.Warn("编号输入有误，不存在该读者的信息！");
        } else {
            int type = readerInfo.getType();

            //检查该读者是否超过图书最大借阅数量
            int alreadyBorrowNum = 0;//已经借阅的图书数量

            List<BorrowReturn> borrowBooks = intermediary.whetherBorrowBook(number);//daoBorrowReturn.whetherBorrowBook(number);
            if (borrowBooks.size() != 0){//该读者有借阅记录
                for (BorrowReturn list : borrowBooks) {
                    if(list.getReturndate() == null) {
                        //如果该读者有未归还的图书，则已经借阅的图书数量+1
                        alreadyBorrowNum = alreadyBorrowNum + 1;
                    }
                }
            }
            if (alreadyBorrowNum == 0){//该读者没有借阅图书
                //条件都满足后，正式添加借阅信息
                new BooksInfoDAO().ShowBooksISBNName();
                boolean flag = true;
                while (flag) {
                    System.out.println("输入你要借阅图书的ISBN:");
                    String isbn = TSUtility.readKeyBoard(10, false);

                    boolean b = new BooksInfoDAO().HasBookInfoByISBN(isbn);
                    if (b) {
                        //查找对应isbn的价格
                        double bookPrice = new BooksInfoDAO().getBookPrice(isbn);

                        //检查该类读者的图书借阅总金额是否超过设定

                        double alreadyUserBM = intermediary.AlreadyUserBM(number);//daoBorrowReturn.AlreadyUserBM(number);//已经借阅且未归还图书的总金额
                        double bookPrice2 = bookPrice + alreadyUserBM;

                        //该类读者设定的可借阅图书总金额
                        double maxBorrowMoney = new BorrowMoneyDAO().QueryBMByReaderTypeId(type);// new BorrowMoneyService().QueryBMByRTId(type);
                        if (bookPrice2 > maxBorrowMoney) {
                            InputLimit.Warn("该类读者的图书借阅总金额不能超过" + maxBorrowMoney + "元，请归还部分图书后再借！");
                            break;
                        } else {
                            Date d = new Date();
                            intermediary.BorrowBook(s, number, isbn, d);//daoBorrowReturn.BorrowBook(s, number, isbn, d);
                            flag = false;
                        }
                    } else {
                        InputLimit.Warn("图书ISBN输入错误！请重新输入！");
                    }
                }
            } else {
                //该读者有借阅记录
                //根据读者类型编号 查询可借阅的最多图书数量
                ReaderType readerType = new ReaderTypeDAO().getRTByReaderTypeId(type);
                int maxBorrowNum = readerType.getMaxborrownum();

                if (maxBorrowNum < alreadyBorrowNum) {
                    InputLimit.Warn("该读者最多可借阅图书 " + maxBorrowNum + " 本，请归还后再借！");
                } else {
                    //条件都满足后，正式添加借阅信息
                    new BooksInfoDAO().ShowBooksISBNName();
                    boolean flag = true;
                    while (flag) {
                        System.out.println("输入你要借阅图书的ISBN:");
                        String isbn = TSUtility.readKeyBoard(10, false);
                        boolean b = new BooksInfoDAO().HasBookInfoByISBN(isbn);
                        if (b) {
                            //查找对应isbn的价格
                            double bookPrice = new BooksInfoDAO().getBookPrice(isbn);

                            //检查该类读者的图书借阅总金额是否超过设定
                            double alreadyUserBM = intermediary.AlreadyUserBM(number); //daoBorrowReturn.AlreadyUserBM(number);//已经借阅且未归还图书的总金额
                            double bookPrice2 = bookPrice + alreadyUserBM;

                            //该类读者设定的可借阅图书总金额
                            double maxBorrowMoney = new BorrowMoneyDAO().QueryBMByReaderTypeId(type);// new BorrowMoneyService().QueryBMByRTId(type);
                            if (bookPrice2 > maxBorrowMoney) {
                                InputLimit.Warn("该类读者的图书借阅总金额不能超过" + maxBorrowMoney + "元，请归还部分图书后再借！");
                                break;
                            } else {
                                Date d = new Date();
                                intermediary.BorrowBook(s, number, isbn, d);//daoBorrowReturn.BorrowBook(s, number, isbn, d);
                                flag = false;
                            }
                        } else {
                            InputLimit.Warn("输入错误！请重新输入！");
                            break;
                        }
                    }
                }
            }
        }
    }

    //归还图书
    public void Return(String s) {
        try {
            QBorrowReturn();//查看所有借阅信息
            System.out.println("请输入你要归还图书的读者编号:");
            String number = TSUtility.readKeyBoard(8, false);

            boolean alreadyReturn = new BorrowReturnDAO().whetherReturnBook(number);
            if (alreadyReturn) {
                InputLimit.Warn("该读者任何没有正在借阅的书籍！");
            } else {
                intermediary.ShowReaderIdISBN(number);//daoBorrowReturn.ShowReaderIdISBN(number);//查看该读者所有的借阅记录
                boolean flag = true;
                while (flag) {
                    System.out.println("请输入你要归还图书ISBN:");
                    String isbn = TSUtility.readKeyBoard(10, false);
                    boolean b = new BooksInfoDAO().HasBookInfoByISBN(isbn); //检查ISBN是否输入正确，是否存在
                    if (b) {
                        //根据读者Id查询借书时间
                        List<BorrowReturn> borrowReturns = intermediary.CheckDuplicate(number);//daoBorrowReturn.CheckDuplicate(number);
                        boolean flag1 = true;
                        Date borrowdate = null;
                        if (borrowReturns != null) {
                            for (BorrowReturn borrowReturn : borrowReturns) {
                                if (borrowReturn.getISBN().equals(isbn)) {
                                    flag1 = false;
                                    borrowdate = borrowReturn.getBorrowdate();
                                }
                            }
                        }
                        if (!flag1) {
                            Date d = new Date();
                            //归还图书：修改还书日期
                            intermediary.ReturnBook(d, isbn);//daoBorrowReturn.ReturnBook(d, isbn);
                            //根据Id查询读者信息
                            ReaderInfo instance = new ReaderInfoDAO().QueryReaderByReaderId(number);
                            //查询规定可借阅的最多图书数量
                            ReaderType readerType = new ReaderTypeDAO().getRTByReaderTypeId(instance.getType());

                            int maxDay = readerType.getLimit();
                            int dayDiffer = DateUtils.getDayDiffer(borrowdate, d);
                            if (dayDiffer <= maxDay) {
                                InputLimit.Notice("未逾期！");
                                double money = 0;
                                //归还图书：设置罚金
                                intermediary.ModifyFine(s, money, isbn, number);//daoBorrowReturn.ModifyFine(s, money, isbn, number);
                            } else {
                                int i = dayDiffer - maxDay;
                                double money = readerType.getPenalMoney() * i;
                                //归还图书：设置罚金
                                intermediary.ModifyFine(s, money, isbn, number);//daoBorrowReturn.ModifyFine(s, money, isbn, number);
                                InputLimit.Warn("逾期" + i + "天，共交：" + money + "元。");
                            }
                        }
                        flag = false;
                    } else {
                        InputLimit.Warn("图书ISBN输入错误！请重新输入！");
                    }
                }
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    //借阅信息查看
    public void QBorrowReturn() {
        intermediary.QueryBorrowReturn();
        //daoBorrowReturn.QueryBorrowReturn();
    }

}
