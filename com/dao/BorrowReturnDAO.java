package com.dao;

import com.entity.BorrowReturn;
import com.implement.BorrowReturnImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowReturnDAO extends BaseDAO<BorrowReturn> implements BorrowReturnImpl {


    @Override
    public void BorrowBook(String s, String number, String isbn, Date d) {
        String sql4 = "insert into `borrowbook` set readerid=?,ISBN=?,borrowdate=?";
        doUpdate(sql4, number, isbn, d);
        InputLimit.Notice("借阅操作执行成功！");
        new LogService().AOL("读者编号（" + number + "）借阅图书isbn（" + isbn + "）,操作员[" + s + "]");
    }

    @Override
    public void ReturnBook(Date d, String isbn) {
        String sql1 = "update `borrowbook` set returndate=? where ISBN=? AND returndate IS NULL";
        doUpdate(sql1, d, isbn);
    }

    @Override
    public void ModifyFine(String s, double money, String isbn, String number) {
        String sql4 = "update `borrowbook` set fine=? where ISBN=?";
        doUpdate(sql4, money, isbn);
        InputLimit.Notice("归还操作执行成功！");
        new LogService().AOL("读者编号（" + number + "）归还图书isbn（" + isbn + "）,操作员[" + s + "]");
    }

    @Override
    public boolean HasBookByISBN(String isbn) {
        boolean result = false;
        String sql1 = "select * from borrowbook where `ISBN`= ? ";
        ArrayList<BorrowReturn> instance1 = doQueryResultList(sql1, isbn);
        if (instance1.size() == 0){
            //这个ISBN不存在时，返回true
            result = true;
        }

        return result;
    }

    @Override
    public void ShowReaderIdISBN(String number) {
        String sql = "select readerid,ISBN from `borrowbook` where readerid = ? ";
        List<BorrowReturn> borrowReturnList = doQueryResultList(sql, number);

        if (borrowReturnList.size()!=0) {
            for (BorrowReturn borrowReturn : borrowReturnList) {
                System.out.println("读者编号:" + borrowReturn.getReaderid() + " ISBN:" + borrowReturn.getISBN());
            }
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public void QueryBorrowReturn() {
        String sql = "select * from borrowbook";
        List<BorrowReturn> forList = doQueryResultList(sql);

        if (forList.size() != 0) {
            System.out.println("读者编号\t\t" + "ISBN\t\t" + "借阅日期\t\t" + "归还日期\t\t" + "罚款金额");
            forList.forEach(System.out::println);
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public List<BorrowReturn> CheckDuplicate(String b) {
        List<BorrowReturn> borrowReturnList;
        String sql = "select readerid,ISBN,borrowdate from `borrowbook` where readerid=?";
        borrowReturnList = doQueryResultList(sql, b);

        return borrowReturnList;
    }

    @Override
    public double AlreadyUserBM(String b) {
        double result = 0;
        String sql1 = "SELECT ISBN FROM borrowbook WHERE readerid = ? AND returndate IS NULL ";
        List<BorrowReturn> forList = doQueryResultList(sql1, b);
        for (BorrowReturn list : forList){
            String isbn = list.getISBN();
            double bookPrice = new BooksInfoDAO().getBookPrice(isbn);
            result = result + bookPrice;
        }
        return result;
    }

    //根据读者Id，查询读者正在阅读的图书数量
    @Override
    public List<BorrowReturn> whetherBorrowBook(String number) {
        List<BorrowReturn> forList;
        String sql = "select * from borrowbook where readerid = ? AND returndate IS NULL";
        forList = doQueryResultList(sql, number);

        return forList;
    }

    @Override
    public boolean IsThereLibraryRecord(String number) {
        boolean result = false;
        String sql = "select * from borrowbook where readerid = ?";
        List<BorrowReturn> forList = doQueryResultList(sql, number);

        if (forList.size() == 0) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean whetherReturnBook(String number) {
        boolean alreadyReturn = false;
        String sql = "select * from `borrowbook` where `readerid`=? AND returndate IS NULL";
        List<BorrowReturn> readerList = doQueryResultList(sql, number);

        if (readerList.size() == 0) {
            alreadyReturn = true;
        }
        return alreadyReturn;
    }
}
