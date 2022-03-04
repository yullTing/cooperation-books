package cn.team;

import cn.domain.TSUtility;
import com.service.LogService;

import java.text.ParseException;

public class OperatorFunctions {
    public static void operator(String s) {
        boolean flag=true;
        while (flag){
        System.out.println("---------操作员功能---------");
        System.out.println("---------1.图书管理---------");
        System.out.println("---------2.读者管理---------");
        System.out.println("---------3.借阅管理---------");
        System.out.println("---------4.退出------------");
        System.out.println("请输入你的选择:");
        char c = TSUtility.readMenuSelectionPro2();
        switch (c){
            case '1':
                LibraryInformation.books(s);
                break;
            case '2':
                ReaderRegistration.reader(s);
                break;
            case '3':
                BookBorrowing.management(s);
                break;
            case '4':
                System.out.println("是否退出？(Y/N)");
                char c1 = TSUtility.readConfirmSelection();
                if (c1=='Y'||c1=='y'){
                    LogService.AddLoginLog("操作员[" + s + "]退出系统");
                    flag=false;
                }else {
                    break;
                }
            }
        }
    }
}