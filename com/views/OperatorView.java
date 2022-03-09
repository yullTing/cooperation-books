package com.views;

import com.utils.TSUtility;
import com.service.BookTypeService;
import com.service.BorrowReturnService;
import com.service.ReaderTypeService;
import com.service.ReaderService;
import com.service.BooksService;
import com.service.LogService;


public class OperatorView {

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
                BooksInfoView(s);
                break;
            case '2':
                ReaderManageView(s);
                break;
            case '3':
                BorrowReturnView(s);
                break;
            case '4':
                System.out.println("是否退出？(Y/N)");
                char c1 = TSUtility.readConfirmSelection();
                if (c1=='Y'||c1=='y'){
                    new LogService().ALL("操作员[" + s + "]退出系统");
                    flag=false;
                }else {
                    break;
                }
            }
        }
    }

    //1.图书管理
    private static void BooksInfoView(String s) {
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
                    new BooksService().AddBooksInfo(s);
                    break;
                case '2':
                    new BooksService().QueryBookInfoByBookName();
                    break;
                case '3':
                    new BooksService().ModifyBooksInfo(s);
                    break;
                case '4':
                    new BooksService().DeleteBooksInfo(s);
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

    //2.读者管理
    private static void ReaderManageView(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("---------读者信息管理-----------");
            System.out.println("---------1.读者信息添加---------");
            System.out.println("---------2.读者信息查询---------");
            System.out.println("---------3.读者信息修改---------");
            System.out.println("---------4.读者信息删除---------");
            System.out.println("---------5.退出------------");
            System.out.println("请输入你的选择:");
            char c = TSUtility.readMenuSelectionPro2();
            switch (c) {
                case '1':
                    new ReaderService().AddReaderInfo(s);
                    break;
                case '2':
                    new ReaderService().QueryReaderInfoByReaderId();
                    break;
                case '3':
                    new ReaderService().ModifyReaderInfo(s);
                    break;
                case '4':
                    new ReaderService().DeleteReaderInfo(s);
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

    //3.借阅管理
    private static void BorrowReturnView(String s) {
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
                    boolean flag1 = true;
                    while (flag1) {
                        System.out.println("---------图书借阅管理-----------");
                        System.out.println("---------1.图书借阅---------");
                        System.out.println("---------2.图书归还---------");
                        System.out.println("---------3.借阅信息查看---------");
                        System.out.println("---------4.退出------------");
                        System.out.println("请输入你的选择:");
                        char c1 = TSUtility.readMenuSelectionPro2();
                        switch (c1) {
                            case '1':
                                new BorrowReturnService().Borrow(s);
                                break;
                            case '2':
                                new BorrowReturnService().Return(s);
                                break;
                            case '3':
                                new BorrowReturnService().QBorrowReturn();
                                break;
                            case '4':
                                System.out.println("是否退出？(Y/N)");
                                char yn = TSUtility.readConfirmSelection();
                                if (yn == 'Y' || yn == 'y') {
                                    flag1 = false;
                                } else {
                                    break;
                                }
                        }
                    }
                    break;
                case '2':
                    boolean flag2 = true;
                    while (flag2) {
                        System.out.println("---------基本信息维护-----------");
                        System.out.println("---------1.图书类别设置---------");
                        System.out.println("---------2.读者类别设置---------");
                        System.out.println("---------3.罚金设置---------");
                        System.out.println("---------4.退出------------");
                        System.out.println("请输入你的选择:");
                        char c2 = TSUtility.readMenuSelectionPro2();
                        switch (c2) {
                            case '1':
                                BookSettingView(s);
                                break;
                            case '2':
                                ReaderSettingView(s);
                                break;
                            case '3':
                                new ReaderTypeService().QueryModifyPenalty(s);
                                break;
                            case '4':
                                System.out.println("是否退出？(Y/N)");
                                char yn = TSUtility.readConfirmSelection();
                                if (yn == 'Y' || yn == 'y') {
                                    flag2 = false;
                                } else {
                                    break;
                                }
                        }
                    }
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
    }

    //图书类别设置
    private static void BookSettingView(String s) {
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
                    new BookTypeService().QBookType();
                    break;
                case '2':
                    new BookTypeService().ABookType(s);
                    break;
                case '3':
                    new BookTypeService().DBookType(s);
                    break;
                case '4':
                    new BookTypeService().MBookType(s);
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

    //读者类别设置
    private static void ReaderSettingView(String s) {
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
                    new ReaderTypeService().QReaderType();
                    break;
                case '2':
                    new ReaderTypeService().AReaderType(s);
                    break;
                case '3':
                    new ReaderTypeService().DReaderType(s);
                    break;
                case '4':
                    new ReaderTypeService().MReaderType(s);
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