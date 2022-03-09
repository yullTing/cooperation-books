package com.service;

import com.dao.QueryPenalBillDAO;

/*
 * 图书逾期罚金账单查
 * */
public class PenalBillService {

    private final QueryPenalBillDAO daoPenalBill = new QueryPenalBillDAO();

    public void QPP(String s) {
        daoPenalBill.QueryPenalPill(s);
    }
}
