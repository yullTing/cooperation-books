package com.implement;

import com.entity.ReaderType;

import java.util.List;
/*
 * 图书借阅金额设定
 * */
public interface BorrowMoneyImpl {

    /**
     * 查看全部图书借阅金额的设定
     * @return 读者类型集合包含图书借阅金额属性，故返回List<ReaderType>
     */
    List<ReaderType> QueryBorrowMoney();

    /**
     * 修改图书借阅金额的设定，遍历集合，依次修改
     * @param arrayList 图书借阅金额集合
     */
    void UpdateBorrowMoney(List<ReaderType> arrayList);

    /**
     * 查询某类读者的图书借阅总金额
     * @param b 读者类型编号，根据该参数查询
     * @return 返回该类型读者的可借阅金额，double
     */
    double QueryBMByReaderTypeId(int b);
}
