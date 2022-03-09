package com.dao;

import com.entity.ReaderType;
import com.implement.BorrowMoneyImpl;
import com.utils.InputLimit;

import java.util.List;

public class BorrowMoneyDAO extends BaseDAO<ReaderType> implements BorrowMoneyImpl {
    @Override
    public List<ReaderType> QueryBorrowMoney() {
        List<ReaderType> ReaderTypes = null;
        String sql = "SELECT typename,borrowMoney FROM readertype";
        ReaderTypes = doQueryResultList(sql);

        if (ReaderTypes !=null) {
            System.out.println("读者类型\t\t" + "该类读者的图书借阅总金额");
            for (ReaderType rt : ReaderTypes){
                System.out.println(rt.getTypename() + " \t\t" + rt.getBorrowMoney());
            }
        }
        return ReaderTypes;
    }

    @Override
    public void UpdateBorrowMoney(List<ReaderType> arrayList) {
        //说明：图书的借阅金额应该根据读者类型确定
        for (ReaderType readertype : arrayList){
            String s = readertype.getTypename();
            System.out.println("读者类型[" + s + "]的图书借阅总金额(" + readertype.getBorrowMoney() + ")修改为：");
            double v = InputLimit.ModifyDouble(readertype.getBorrowMoney());
            String sql = "UPDATE readertype SET borrowMoney = ? WHERE typename = ?";
            int i = doUpdate(sql, v, s);
            if (i < 0){
                InputLimit.Warn("该图书借阅金额修改失败！");
            }
        }
    }

    @Override
    public double QueryBMByReaderTypeId(int b) {
        double result;
        List<ReaderType> ReaderTypes = null;
        String sql = "SELECT borrowMoney FROM readertype where id = ?";
        ReaderType instance = doQueryOneData(sql, b);
        result = instance.getBorrowMoney();

        return result;
    }
}
