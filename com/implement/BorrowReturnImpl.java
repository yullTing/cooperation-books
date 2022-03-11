package com.implement;

import com.entity.BorrowReturn;

import java.util.Date;
import java.util.List;

public interface BorrowReturnImpl {

    /**
     * 借阅图书
     * @param s 操作员姓名
     * @param number 读者编号
     * @param isbn 图书ISBN
     * @param d 借阅时间
     */
    void BorrowBook(String s, String number, String isbn, Date d);

    /**
     * 归还图书
     * @param d 归还日期
     * @param isbn 图书ISBN
     */
    void ReturnBook(Date d, String isbn);

    /**
     * 修改罚金：逾期归还图书需要记录罚金
     * @param s 操作员姓名
     * @param money 罚款金额
     * @param isbn 图书ISBN
     * @param number 读者编号
     */
    void ModifyFine(String s, double money, String isbn, String number);

    /**
     * 根据ISBN检查是否存在图书
     * @param isbn 图书ISBN
     * @return 返回boolean值
     */
    boolean HasBookByISBN(String isbn);

    /**
     * 查询所有借阅记录的读者Id+ISBN
     * @param number 读者编号Id
     */
    void ShowReaderIdISBN(String number);

    /**
     * 查看所有的借阅归还信息
     */
    void QueryBorrowReturn();

    /**
     * 根据读者Id查询借阅时间
     * @param b 读者编号Id
     * @return 返回借阅信息集合
     */
    List<BorrowReturn> CheckDuplicate(String b);

    /**
     * 查询某读者已借阅且未归还图书的总金额（总价格）
     * @param b 读者编号Id
     * @return 返回double值
     */
    double AlreadyUserBM(String b);

    /**
     * 根据读者Id，查询读者正在阅读的图书数量
     * @param number 读者编号Id
     * @return 返回借阅信息集合
     */
    List<BorrowReturn> whetherBorrowBook(String number);

    /**
     * 根据读者Id 查询该读者是否有借阅记录，在删除读者时使用
     * @param number 读者编号Id
     * @return 返回boolean值
     */
    boolean IsThereLibraryRecord(String number);

    /**
     * 根据读者Id 查询该读者是否有图书未归还
     * @param number 读者编号Id
     * @return 返回boolean值
     */
    boolean whetherReturnBook(String number);

}
