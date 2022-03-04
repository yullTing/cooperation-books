package cn.team;

import cn.domain.TSUtility;
import cn.sercive.BookCategoryService;

public class BookSetting {
    public static void set(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("---------图书类别设置-----------");
            System.out.println("---------1.图书类别查看---------");
            System.out.println("---------2.图书类别增加---------");
            System.out.println("---------3.图书类别删除---------");
            System.out.println("---------4.图书类别修改---------");
            System.out.println("---------5.退出    ------------");
            System.out.println("请输入你的选择(1-5):");
            char c = TSUtility.readMenuSelectionPro1();
            switch (c) {
                case '1':
                    BookCategoryService.see();
                    break;
                case '2':
                    BookCategoryService.add(s);
                    break;
                case '3':
                    BookCategoryService.delete(s);
                    break;
                case '4':
                    BookCategoryService.modify(s);
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
    }
}
