package cn.sercive;

import cn.domain.*;
import com.service.BorrowMoneyService;
import com.service.LogService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BorrowingService {
    /*public static void main(String[] args) {
//        check();
        //borrow();
        *//*System.out.println("请输入读者编号:");
        String number = TSUtility.readKeyBoard(8, false);
        double v = AlreadyUserBM(number);
        System.out.println(v);*//*
        borrow("员工2");
    }*/

    public static void borrow(String s) {
        System.out.println("请输入读者编号:");
        String number = TSUtility.readKeyBoard(8, false);

        String sql3 = "select * from `readerinformation` where `readerid`=?";
        reader instance = CURRENCY.getInstance(reader.class, sql3, number);
        if (instance == null) {
            System.out.println("编号输入有误，不存在该读者的信息！");
        } else {
            int type = instance.getType();
            //检查该类读者的图书借阅总金额是否超过设定
            double alreadyUserBM = AlreadyUserBM(number);//已经借阅且未归还图书的总金额
            double maxBorrowMoney = BorrowMoneyService.QueryBMByReaderTypeId(type);//该类读者设定的可借阅图书总金额
            if (alreadyUserBM > maxBorrowMoney) {
                System.out.println("该类读者的图书借阅总金额不能超过" + maxBorrowMoney + "元，请归还部分图书后再借！");
            } else {
                //检查该读者是否超过图书最大借阅数量
                int alreadyBorrownum = getBorrowBookNum(number);//已经借阅的图书数量
                if (alreadyBorrownum == 0){//该读者没有借阅图书
                    //条件都满足后，正式添加借阅信息
                    AddBorrowBook(number, s);
                } else {
                    //该读者有借阅记录
                    String sql1 = "select * from readertype where id = ?";
                    readerType readerType = CURRENCY.getInstance(readerType.class, sql1, type);
                    int maxborrownum = readerType.getMaxborrownum();//规定可借阅的最多图书数量

                    if (maxborrownum < alreadyBorrownum) {
                        System.out.println("该读者最多可借阅图书 " + maxborrownum + " 本，请归还后再借！");
                    } else {
                        //条件都满足后，正式添加借阅信息
                        AddBorrowBook(number, s);
                    }
                }
            }
        }


        /*String sql = "select * from `borrowbook` where `readerid`=?";
        List<BorrowBook> forList = CURRENCY.getForList(BorrowBook.class, sql, number);
        if (forList.size() == 0) {
            if (instance == null) {
                System.out.println("不存在该读者的信息！");
            } else {
                int type = instance.getType();
                seeISBN();
                boolean flag = true;
                while (flag) {
                    System.out.println("输入你要借阅图书的ISBN:");
                    String isbn = TSUtility.readKeyBoard(10, false);
                    boolean b = checkisbn(isbn);
                    if (b == true) {
                        Date d = new Date();
                        String sql4 = "insert into `borrowbook` set readerid=?,ISBN=?,borrowdate=?";
                        CURRENCY.update(sql4, number, isbn, d);
                        LogService.AddOperLog("读者编号（" + number + "）借阅图书isbn（" + isbn + "）,操作员[" + s + "]");
                        flag = false;
                    } else {
                        System.out.println("输入错误！请重新输入！");
                    }
                }
            }
        } else {
            int type = instance.getType();
            String sql1 = "select id,typename,maxborrownum,`limit` from readertype where id = ?";
            readerType readerType = CURRENCY.getInstance(readerType.class, sql1, type);
            if (readerType.getMaxborrownum() < forList.size()) {
                System.out.println("该读者超过最多可借阅图书数量！禁止借阅！（请归还后再借）");
            } else {
                seeISBN();
                boolean flag = true;
                while (flag) {
                    System.out.println("请输入你要借阅的图书的ISBN:");
                    String isbn = TSUtility.readKeyBoard(10, false);
                    boolean b = checkisbn(isbn);
                    if (b == true) {
                        Date d = new Date();
                        String sql4 = "insert into `borrowbook` set readerid=?,ISBN=?,borrowdate=?";
                        CURRENCY.update(sql4, number, isbn, d);
                        LogService.AddOperLog("读者编号（" + number + "）借阅图书isbn（" + isbn + "）,操作员[" + s + "]");
                        flag = false;
                    } else {
                        System.out.println("输入错误！请重新输入！");
                    }
                }
            }
        }*/

    }

    private static void AddBorrowBook(String number, String s){
        seeISBN();
        boolean flag = true;
        while (flag) {
            System.out.println("输入你要借阅图书的ISBN:");
            String isbn = TSUtility.readKeyBoard(10, false);
            boolean b = checkisbn(isbn);
            if (b == true) {
                Date d = new Date();
                String sql4 = "insert into `borrowbook` set readerid=?,ISBN=?,borrowdate=?";
                CURRENCY.update(sql4, number, isbn, d);
                System.out.println("借阅操作执行成功！");
                LogService.AddOperLog("读者编号（" + number + "）借阅图书isbn（" + isbn + "）,操作员[" + s + "]");
                flag = false;
            } else {
                System.out.println("输入错误！请重新输入！");
            }
        }
    }


    private static boolean checkisbn(String isbn) {
        boolean flag = true;
        String sql = "select ISBN,bookname from `bookinformation` where ISBN=?";
        book instance = CURRENCY.getInstance(book.class, sql, isbn);
        if (instance == null) {
            flag = false;
        } else {
            flag = true;
        }
        return flag;
    }

    //
    public static void revert(String s) throws ParseException {
        System.out.println("请输入你要归还图书的读者编号:");
        String number = TSUtility.readKeyBoard(8, false);
        String sql = "select * from `borrowbook` where `readerid`=? AND returndate IS NULL";
        List<BorrowBook> readerList = CURRENCY.getForList(BorrowBook.class, sql, number);
        if (readerList.size() == 0) {
            System.out.println("该读者没有借阅书籍！");
        } else {
            seeISBN();
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要归还图书ISBN:");
                String isbn = TSUtility.readKeyBoard(10, false);
                boolean b = checkisbn(isbn);
                if (b == true) {
                    List<BorrowBook> borrowBooks = CheckDuplicate(number);
                    boolean flag1 = true;
                    Date borrowdate = null;
                    if (borrowBooks != null) {
                        for (int i = 0; i < borrowBooks.size(); i++) {
                            if (borrowBooks.get(i).getISBN().equals(isbn)) {
                                flag1 = false;
                                borrowdate = borrowBooks.get(i).getBorrowdate();
                            }
                        }
                    }
                    if (flag1 == false) {
                        Date d = new Date();
                        String sql1 = "insert into `borrowbook` set returndate=?";
                        CURRENCY.update(sql1, d);
                        String sql2 = "select `type` from `readerinformation` where `readerid`=?";
                        reader instance = CURRENCY.getInstance(reader.class, sql2, number);
                        String sql3 = "select maxborrownum,`limit` from readertype where id = ?";
                        readerType readerType = CURRENCY.getInstance(readerType.class, sql3, instance.getType());
                        int maxday = readerType.getMaxborrownum();
                        int dayDiffer = DateUtils.getDayDiffer(borrowdate, d);
                        if (dayDiffer <= maxday) {
                            System.out.println("未逾期！");
                            double money = 0;
                            String sql4 = "insert into borrowbook set fine=?";
                            CURRENCY.update(sql4, money);
                            System.out.println("归还操作执行成功！");
                            LogService.AddOperLog("读者编号（" + number + "）归还图书isbn（" + isbn + "）,操作员[" + s + "]");
                        } else {
                            String sql4 = "select fine from readertype where id = ?";
                            readerType instance1 = CURRENCY.getInstance(readerType.class, sql4, instance.getType());
                            int i = dayDiffer - maxday;
                            double money = instance1.getPenalMoney() * i;
                            String sql5 = "insert into borrowbook set fine=?";
                            CURRENCY.update(sql5, money);
                            System.out.println("归还操作执行成功！");
                            System.out.println("逾期" + i + "天，共交：" + money + "钱。");
                            LogService.AddOperLog("读者编号（" + number + "）归还图书isbn（" + isbn + "）,操作员[" + s + "]");
                        }
                    }
                    flag = false;
                } else {
                    System.out.println("输入错误！请重新输入！");
                }
            }
        }
    }

    private static void seeISBN() {
        String sql = "select ISBN,bookname from `bookinformation` ";
        List<book> bookList = CURRENCY.getForList(book.class, sql);
        System.out.println("图书名称\t\t" + "ISBN");
        for (book bl : bookList) {
            System.out.println(bl.getBookname() + " \t\t" + bl.getISBN());
        }
    }

    //借阅信息查看
    public static void check() {
        String sql = "select * from borrowbook";
        List<BorrowBook> forList = CURRENCY.getForList(BorrowBook.class, sql);
        System.out.println("读者编号\t\t" + "ISBN\t\t" + "借阅日期\t\t" + "归还日期\t\t" + "罚款金额");
        forList.forEach(System.out::println);
    }

    public static List<BorrowBook> CheckDuplicate(String b) {
        String sql = "select readerid,ISBN,borrowdate from `borrowbook` where readerid=?";
        List<BorrowBook> borrowBookList = CURRENCY.getForList(BorrowBook.class, sql, b);
        return borrowBookList;
    }

    //查询某读者已借阅且未归还图书的总金额
    private static double AlreadyUserBM(String b) {
        double result = 0.0;
        String sql1 = "SELECT ISBN FROM borrowbook WHERE readerid = ?";
        List<BorrowBook> forList = CURRENCY.getForList(BorrowBook.class, sql1, b);
        for (BorrowBook list : forList){
            String isbn = list.getISBN();
            String sql = "SELECT unitprice FROM bookinformation WHERE ISBN = ?";
            book instance = CURRENCY.getInstance(book.class, sql, isbn);
            result = result + instance.getUnitprice();
        }
        return result;
    }

    //查询该读者是否有借阅记录
    private static int getBorrowBookNum(String number){
        int result = 0;//已经借阅的图书数量
        String sql = "select * from borrowbook where readerid = ?";
        List<BorrowBook> forList = CURRENCY.getForList(BorrowBook.class, sql, number);
        if (forList != null){//该读者有借阅记录
            for (BorrowBook list : forList) {
                if(list.getReturndate() == null) {
                    //如果该读者有未归还的图书，则已经借阅的图书数量+1
                    result = result + 1;
                }
            }
        }
        return result;
    }

}
