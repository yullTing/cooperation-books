package cn.team;

import cn.domain.TSUtility;
import cn.sercive.booksService;

import java.text.ParseException;

public class LibraryInformation {
    public static void books(String s) {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("---------图书信息管理-----------");
                System.out.println("---------1.图书信息添加---------");
                System.out.println("---------2.图书信息查询---------");
                System.out.println("---------3.图书信息修改---------");
                System.out.println("---------4.图书信息删除---------");
                System.out.println("---------5.退出------------");
                System.out.println("请输入你的选择:");
                char c = TSUtility.readMenuSelectionPro2();
                switch (c) {
                    case '1':
                        booksService.add(s);
                        break;
                    case '2':
                        booksService.query();
                        break;
                    case '3':
                        booksService.modify(s);
                        break;
                    case '4':
                        booksService.delete(s);
                        break;
                    case '5':
                        System.out.println("是否退出？(Y/N)");
                        char c1 = TSUtility.readConfirmSelection();
                        if (c1 == 'Y' || c1 == 'y') {
                            flag = false;
                        } else {
                            break;
                        }
                }
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
    }
}
