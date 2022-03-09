package com.dao;

import com.entity.BorrowReturn;
import com.implement.QueryPenalBillImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.List;

public class QueryPenalBillDAO extends BaseDAO<BorrowReturn> implements QueryPenalBillImpl {
    @Override
    public void QueryPenalPill(String s) {
        String sql = "SELECT * FROM borrowbook WHERE fine > 0";
        List<BorrowReturn> forList = doQueryResultList(sql);
        if (forList.size()!=0) {
            System.out.println("读者编号\t\t" + "图书ISBN\t\t" + "借阅日期\t\t" + "归还日期\t\t" + "罚款总额");
            forList.forEach(System.out::println);
        } else {
            InputLimit.Warn("无任何逾期罚金账单信息！");
        }
        new LogService().AOL("管理员[" + s + "]查看图书逾期罚金账单");
    }
}
