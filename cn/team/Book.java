package cn.team;

import cn.domain.TSUtility;
import cn.sercive.BorrowingService;

import java.text.ParseException;

public class Book {
    public static void booksBorrowing(String s) throws ParseException {
        boolean flag = true;
        while (flag) {
            System.out.println("---------图书借阅管理-----------");
            System.out.println("---------1.图书借阅---------");
            System.out.println("---------2.图书归还---------");
            System.out.println("---------3.借阅信息查看---------");
            System.out.println("---------4.退出------------");
            System.out.println("请输入你的选择:");
            char c = TSUtility.readMenuSelectionPro2();
            switch (c) {
                case '1':
                    BorrowingService.borrow(s);
                    break;
                case '2':
                    BorrowingService.revert(s);
                    break;
                case '3':
                    BorrowingService.check();
                    break;
                case '4':
                    System.out.println("是否退出？(Y/N)");
                    char c1 = TSUtility.readConfirmSelection();
                    if (c1 == 'Y' || c1 == 'y') {
                        flag = false;
                    } else {
                        break;
                    }
            }
        }
    }
}
