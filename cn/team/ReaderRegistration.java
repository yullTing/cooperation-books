package cn.team;

import cn.domain.TSUtility;
import cn.sercive.readerService;

import java.text.ParseException;

public class ReaderRegistration {
    /*public static void main(String[] args) {
        String s = "XXX";
        try {
            readerService.modify(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }*/

    public static void reader(String s){
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("---------读者注册管理-----------");
                System.out.println("---------1.读者信息添加---------");
                System.out.println("---------2.读者信息查询---------");
                System.out.println("---------3.读者信息修改---------");
                System.out.println("---------4.读者信息删除---------");
                System.out.println("---------5.退出------------");
                System.out.println("请输入你的选择:");
                char c = TSUtility.readMenuSelectionPro2();
                switch (c) {
                    case '1':
                        readerService.add(s);
                        break;
                    case '2':
                        readerService.query();
                        break;
                    case '3':
                        readerService.modify(s);
                        break;
                    case '4':
                        readerService.delete(s);
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
