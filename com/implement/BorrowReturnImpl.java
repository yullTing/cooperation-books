package com.implement;

import com.entity.BorrowReturn;

import java.util.Date;
import java.util.List;

public interface BorrowReturnImpl {

    //借阅图书
    void BorrowBook(String s, String number, String isbn, Date d);

    //归还图书
    void ReturnBook(Date d, String isbn);

    //修改罚金
    void ModifyFine(String s, double money, String isbn, String number);

    //根据ISBN检查是否存在图书
    boolean HasBookByISBN(String isbn);

    //查询所有借阅记录的读者Id+ISBN
    void ShowReaderIdISBN(String number);

    //查看所有的借阅归还信息
    void QueryBorrowReturn();

    //根据读者Id查询借阅时间
    List<BorrowReturn> CheckDuplicate(String b);

    //查询某读者已借阅且未归还图书的总金额（总价格）
    double AlreadyUserBM(String b);

    //根据读者Id 查询该读者是否有借阅记录
    List<BorrowReturn> whetherBorrowBook(String number);

    //根据读者Id 查询该读者是否有图书未归还
    boolean whetherReturnBook(String number);
}
