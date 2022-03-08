package com.implement;

import com.entity.ReaderType;

import java.util.List;
/*
 * 图书借阅金额设定
 * */
public interface BorrowMoneyImpl {

    //查看全部图书借阅金额的设定
    List<ReaderType> QueryBorrowMoney();

    //修改图书借阅金额的设定
    void UpdateBorrowMoney(List<ReaderType> arrayList);

    //查询某类读者的图书借阅总金额
    double QueryBMByReaderTypeId(int b);
}
