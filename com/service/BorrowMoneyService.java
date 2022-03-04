package com.service;

import cn.domain.CURRENCY;
import cn.domain.readerType;
import com.utils.InputLimit;
import com.utils.JDBCUtils;

import java.util.List;

public class BorrowMoneyService {
    /*
    * 图书借阅金额设定
    * */
    //查看全部图书借阅金额的设定
    public static List<readerType> QueryBorrowMoney(){
        List<readerType> readerTypes = null;
        String sql = "SELECT typename,borrowMoney FROM readertype";
        readerTypes = CURRENCY.getForList(readerType.class, sql);

        //readertypes = JDBCUtils.QueryMultiple(Readertype.class, sql);
        if (readerTypes!=null) {
            System.out.println("读者类型\t\t" + "该类读者的图书借阅总金额");
            for (readerType rt : readerTypes){
                System.out.println(rt.getTypename() + " \t\t" + rt.getBorrowMoney());
            }
        }
        return readerTypes;
    }

    //修改图书借阅金额的设定
    public static void UpdateBorrowMoney(List<readerType> arrayList){
        //说明：图书的借阅金额应该根据读者类型确定
        for (readerType readertype : arrayList){
            String s = readertype.getTypename();
            System.out.println("读者类型[" + s + "]的图书借阅总金额(" + readertype.getBorrowMoney() + ")修改为：");
            double v = InputLimit.ModifyDouble(readertype.getBorrowMoney());
            String sql = "UPDATE readertype SET borrowMoney = ? WHERE rtName = ?";
            int i = JDBCUtils.ExecuteData(sql, v, s);
            if (i < 0){
                InputLimit.Warn("该图书借阅金额修改失败！");
            }
        }
    }

    //查询某类读者的图书借阅总金额
    public static double QueryBMByReaderTypeId(int b){
        double result;
        List<readerType> readerTypes = null;
        String sql = "SELECT borrowMoney FROM readertype where id = ?";
        readerType instance = CURRENCY.getInstance(readerType.class, sql, b);
        result = instance.getBorrowMoney();

        return result;
    }
}
