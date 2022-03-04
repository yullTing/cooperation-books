package cn.team;

import cn.domain.TSUtility;
import cn.sercive.PenaltyService;

public class Base {
    public static void InformationMaintenance(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("---------基本信息维护-----------");
            System.out.println("---------1.图书类别设置---------");
            System.out.println("---------2.读者类别设置---------");
            System.out.println("---------3.罚金设置---------");
            System.out.println("---------4.退出------------");
            System.out.println("请输入你的选择:");
            char c = TSUtility.readMenuSelectionPro2();
            switch (c) {
                case '1':
                    BookSetting.set(s);
                    break;
                case '2':
                    ReaderSetting.set(s);
                    break;
                case '3':
                    PenaltyService.set(s);
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
