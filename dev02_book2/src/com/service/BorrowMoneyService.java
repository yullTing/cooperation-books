package com.service;

import com.entity.Readertype;
import com.utils.InputLimit;
import com.utils.JDBCUtils;

import java.util.ArrayList;

public class BorrowMoneyService {
    /*
    * 图书借阅金额设定
    * */
    //查看图书借阅金额的设定
    public static ArrayList<Readertype> QueryBorrowMoney(){
        ArrayList<Readertype> readertypes = null;
        String sql = "SELECT * FROM readertype ";
        readertypes = JDBCUtils.QueryMultiple(Readertype.class, sql);
        if (readertypes!=null) {
            readertypes.forEach(System.out::println);
        }
        return readertypes;
    }

    //修改图书借阅金额的设定
    public static void UpdateBorrowMoney(ArrayList<Readertype> arrayList){
        //说明：图书的借阅金额应该根据读者类型确定
        for (Readertype readertype : arrayList){
            String s = readertype.getRtName();
            System.out.println("读者类型[" + s + "]的图书借阅金额(" + readertype.getBorrowMoney() + ")修改为：");
            double v = InputLimit.ModifyDouble(readertype.getBorrowMoney());
            String sql = "UPDATE readertype SET borrowMoney = ? WHERE rtName = ?";
            int i = JDBCUtils.ExecuteData(sql, v, s);
            if (i < 0){
                InputLimit.Warn("该图书借阅金额修改失败！");
            }
        }
    }

}
