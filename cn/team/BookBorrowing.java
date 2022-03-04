package cn.team;

import cn.domain.TSUtility;

import java.text.ParseException;

public class BookBorrowing {
    public static void management(String s) {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("---------借阅管理---------");
                System.out.println("---------1.图书借阅管理---------");
                System.out.println("---------2.基本信息维护---------");
                System.out.println("---------3.退出------------");
                System.out.println("请输入你的选择:");
                char c = TSUtility.readMenuSelectionPro2();
                switch (c) {
                    case '1':
                        Book.booksBorrowing(s);
                        break;
                    case '2':
                        Base.InformationMaintenance(s);
                        break;
                    case '3':
                        System.out.println("是否退出？(Y/N)");
                        char c1 = TSUtility.readConfirmSelection();
                        if (c1 == 'Y' || c1 == 'y') {
                            flag = false;
                        } else {
                            break;
                        }
                }
            }
        }catch (ParseException e){
            e.printStackTrace();
        }
    }
}