package cn.team;

import cn.domain.TSUtility;
import cn.sercive.ReaderCategoryService;

public class ReaderSetting {
    public static void set(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("---------读者类别设置-----------");
            System.out.println("---------1.读者类别查看---------");
            System.out.println("---------2.读者类别增加---------");
            System.out.println("---------3.读者类别删除---------");
            System.out.println("---------4.读者类别修改---------");
            System.out.println("---------5.退出------------");
            System.out.println("请输入你的选择:");
            char c = TSUtility.readMenuSelectionPro1();
            switch (c) {
                case '1':
                    ReaderCategoryService.see();
                    break;
                case '2':
                    ReaderCategoryService.add(s);
                    break;
                case '3':
                    ReaderCategoryService.delete(s);
                    break;
                case '4':
                    ReaderCategoryService.modify(s);
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
