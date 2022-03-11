package com.service;

import com.entity.ReaderType;
import com.dao.BorrowMoneyDAO;
import com.utils.InputLimit;

import java.util.List;

//2. 图书借阅金额设定
public class BorrowMoneyService {

    private final BorrowMoneyDAO daoBorrowMoney = new BorrowMoneyDAO();

    //说明：图书的借阅金额应该根据读者类型确定
    public void BorrowMoney(String s){
        InputLimit.BlueFont("********************************");
        List<ReaderType> readerTypes = daoBorrowMoney.QueryBorrowMoney();
        if (readerTypes.size() != 0){
            InputLimit.Warn("请确认是否修改此设定？（Y/N）");
            char ynBMV = InputLimit.InputChoice();
            if (ynBMV == 'Y') {
                daoBorrowMoney.UpdateBorrowMoney(readerTypes);
                new LogService().AOL("管理员[" + s + "]修改图书借阅金额的设定");
            }
        }else {
            InputLimit.Warn("请先设置读者类型后再设置图书借阅金额！");
        }
    }

    //查询某类读者的图书借阅总金额
    public double QueryBMByRTId(int b){
        double result;
        result = daoBorrowMoney.QueryBMByReaderTypeId(b);

        return result;
    }

}
