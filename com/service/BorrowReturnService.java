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

    /*public static void main(String[] args) {

        BorrowReturnImpl host = new BorrowReturnDAO();
        BorrowReturnImpl intermediary = (BorrowReturnImpl)new Intermediary(host).getProxyInstance();
        intermediary.NumberAlreadyBorrow("111");

        new BorrowReturnService().Borrow("员工2");

        //new BorrowReturnService().Return("员工2");
    }*/


    public void Borrow(String s){
        new ReaderInfoDAO().QueryReaderIdTypeName();
        System.out.println("请输入读者编号:");
        String number = TSUtility.readKeyBoard(8, false);
        ReaderInfo readerInfo = new ReaderInfoDAO().QueryReaderByReaderId(number);
        if (readerInfo == null) {
            InputLimit.Warn("编号输入有误，不存在该读者的信息！");
        }else {
            //检查该读者是否超过图书最大借阅数量
            List<BorrowReturn> borrowBooks = intermediary.whetherBorrowBook(number);//daoBorrowReturn.whetherBorrowBook(number);
            int alreadyBorrowNum = borrowBooks.size();//已经借阅的图书数量
            //该读者有借阅记录
            //根据读者类型编号 查询可借阅的最多图书数量
            int type = readerInfo.getType();
            ReaderType readerType = new ReaderTypeDAO().getRTByReaderTypeId(type);
            int maxBorrowNum = readerType.getMaxborrownum();
            int alreadyBorrowNum1 = alreadyBorrowNum+1;
            if (maxBorrowNum < alreadyBorrowNum1) {
                InputLimit.Warn("该读者最多可借阅图书 " + maxBorrowNum + " 本，请归还后再借！");
            }else {
                new BooksInfoDAO().ShowBooksISBNName();
                System.out.println("输入你要借阅图书的ISBN:");
                String isbn = TSUtility.readKeyBoard(10, false);
                boolean b = new BooksInfoDAO().HasBookInfoByISBN(isbn);
                if (b) {
                    boolean bookState = new BooksInfoDAO().ConfirmBookState(isbn);
                    if (bookState){
                        if (alreadyBorrowNum == 0){//该读者没有借阅图书
                            //条件都满足后，正式添加借阅信息
                            boolean flag = true;
                            while (flag) {
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
                                        new BooksInfoDAO().SetStateFalse(s,isbn);
                                        flag = false;
                                    }
                                } else {
                                    InputLimit.Warn("图书ISBN输入错误！请重新输入！");
                                }
                            }
                        }
                        else {
                            //条件都满足后，正式添加借阅信息
                            boolean flag = true;
                            while (flag) {
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
                                        new BooksInfoDAO().SetStateFalse(s,isbn);
                                        flag = false;
                                    }
                                } else {
                                    InputLimit.Warn("输入错误！请重新输入！");
                                    break;
                                }
                            }
                        }
                    }else {
                        InputLimit.Warn("该图书已被借阅！");
                    }
                }else {
                    InputLimit.Warn("图书ISBN输入错误！请重新输入！");
                }
            }
        }
    }
    /*public void Borrow(String s) {
        new ReaderInfoDAO().QueryReaderIdTypeName();
        InputLimit.PurpleFont("请输入读者编号:");
        String number = TSUtility.readKeyBoard(8, false);

        ReaderInfo readerInfo = new ReaderInfoDAO().QueryReaderByReaderId(number);

        if (readerInfo == null) {
            InputLimit.Warn("编号输入有误，不存在该读者的信息！");
        } else {
            new BooksInfoDAO().ShowBooksISBNName();
            InputLimit.PurpleFont("输入你要借阅图书的ISBN:");
            String isbn = TSUtility.readKeyBoard(10, false);

            boolean b = new BooksInfoDAO().HasBookInfoByISBN(isbn);
            if (b) {
                //确定图书状态是否可以借阅
                boolean bookState = new BooksInfoDAO().ConfirmBookState(isbn);
                if (bookState){
                    //可以借阅
                    int type = readerInfo.getType();


                    //根据读者类型编号 查询可借阅的最多图书数量
                    ReaderType readerType = new ReaderTypeDAO().getRTByReaderTypeId(type);
                    int maxBorrowNum = readerType.getMaxborrownum();

                    if (maxBorrowNum == 1) {
                        //该类读者只能借阅一本图书
                        //查找对应isbn的价格
                        double bookPrice = new BooksInfoDAO().getBookPrice(isbn);

                        //检查该类读者的图书借阅总金额是否超过设定

                        double alreadyUserBM = intermediary.AlreadyUserBM(number);//daoBorrowReturn.AlreadyUserBM(number);//已经借阅且未归还图书的总金额
                        double bookPrice2 = bookPrice + alreadyUserBM;

                        //该类读者设定的可借阅图书总金额
                        double maxBorrowMoney = new BorrowMoneyDAO().QueryBMByReaderTypeId(type);// new BorrowMoneyService().QueryBMByRTId(type);
                        if (bookPrice2 > maxBorrowMoney) {
                            InputLimit.Warn("该类读者的图书借阅总金额不能超过" + maxBorrowMoney + "元，请归还部分图书后再借！");
                        } else {
                            Date d = new Date();
                            intermediary.BorrowBook(s, number, isbn, d);//daoBorrowReturn.BorrowBook(s, number, isbn, d);
                            new BooksInfoDAO().SetStateFalse(s,isbn);
                        }
                    } else {
                        //该类读者可以借阅多本图书，所以要检查该读者是否超过图书最大借阅数量
                        //目前该读者的可借阅的图书数量 = 当前将要借阅的一本 + 正在借阅中的图书数量
                        int alreadyBorrowNum = 1 + intermediary.NumberAlreadyBorrow(number);

                        if (alreadyBorrowNum > maxBorrowNum) {
                            InputLimit.Warn("该读者最多可借阅图书 " + maxBorrowNum + " 本，请归还后再借！");
                        } else {
                            //查找对应isbn的价格
                            double bookPrice = new BooksInfoDAO().getBookPrice(isbn);

                            //检查该类读者的图书借阅总金额是否超过设定
                            double alreadyUserBM = intermediary.AlreadyUserBM(number); //daoBorrowReturn.AlreadyUserBM(number);//已经借阅且未归还图书的总金额
                            double bookPrice2 = bookPrice + alreadyUserBM;

                            //该类读者设定的可借阅图书总金额
                            double maxBorrowMoney = new BorrowMoneyDAO().QueryBMByReaderTypeId(type);// new BorrowMoneyService().QueryBMByRTId(type);
                            if (bookPrice2 > maxBorrowMoney) {
                                InputLimit.Warn("该类读者的图书借阅总金额不能超过" + maxBorrowMoney + "元，请归还部分图书后再借！");
                            } else {
                                Date d = new Date();
                                intermediary.BorrowBook(s, number, isbn, d);//daoBorrowReturn.BorrowBook(s, number, isbn, d);
                                new BooksInfoDAO().SetStateFalse(s,isbn);
                            }
                        }
                    }
                }
            }else {
                InputLimit.Warn("图书ISBN输入错误！请重新输入！");
            }
        }
    }*/

    //归还图书
    public void Return(String s) {
        try {
            QBorrowReturn();//查看所有借阅信息
            InputLimit.PurpleFont("请输入你要归还图书的读者编号:");
            String number = TSUtility.readKeyBoard(8, false);

            boolean alreadyReturn = intermediary.whetherReturnBook(number);
            if (alreadyReturn) {
                InputLimit.Warn("该读者任何没有正在借阅的书籍！");
            } else {
                intermediary.ShowReaderIdISBN(number);//daoBorrowReturn.ShowReaderIdISBN(number);//查看该读者所有的借阅记录
                boolean flag = true;
                while (flag) {
                    InputLimit.PurpleFont("请输入你要归还图书ISBN:");
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
                                double money = 0;
                                InputLimit.Notice("未逾期！");

                                //归还图书：设置罚金
                                intermediary.ModifyFine(s, money, isbn, number);//daoBorrowReturn.ModifyFine(s, money, isbn, number);
                                new BooksInfoDAO().SetStateTrue(s,isbn);
                            } else {
                                int i = dayDiffer - maxDay;
                                double money = readerType.getPenalMoney() * i;
                                InputLimit.Warn("逾期" + i + "天，共交：" + money + "元。");

                                //归还图书：设置罚金
                                intermediary.ModifyFine(s, money, isbn, number);//daoBorrowReturn.ModifyFine(s, money, isbn, number);
                                new BooksInfoDAO().SetStateTrue(s,isbn);
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
