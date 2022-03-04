package com.service;

import cn.domain.BorrowBook;
import cn.domain.CURRENCY;
import cn.domain.readerType;
import com.utils.JDBCUtils;

import java.util.ArrayList;
import java.util.List;

public class PenalPillService {
    /*
    * 图书逾期罚金账单查
    * */
    public static void QueryPenalPill(){
        String sql = "SELECT * FROM borrowbook WHERE fine > 0";
        List<BorrowBook> forList = CURRENCY.getForList(BorrowBook.class, sql);
        System.out.println("读者编号\t\t" + "图书ISBN\t\t" + "借阅日期\t\t" + "归还日期\t\t" + "罚款总额");
        forList.forEach(System.out::println);
    }
}
