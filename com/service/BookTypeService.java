package com.service;

import com.utils.InputLimit;
import com.utils.TSUtility;
import com.dao.BookTypeDAO;

public class BookTypeService {
    private final BookTypeDAO daoBookType = new BookTypeDAO();

    public void QBookType() {
        daoBookType.QueryBookType();
    }

    public void ABookType(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要增加的图书类型名称:");
            String bookType = TSUtility.readKeyBoard(30, false);
            //检查该图书类别名称是否存在
            boolean check = daoBookType.HasBookTypeName(bookType);
            if (check) {
                daoBookType.AddBookType(s, bookType);
                flag = false;
            } else {
                InputLimit.Warn("存在该图书类型名称！请重新输入！");
                break;
            }
        }
    }

    public void DBookType(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要删除的图书类型名称:");
            String type1 = TSUtility.readKeyBoard(30, false);
            //检查该图书类别名称是否存在
            boolean check = daoBookType.HasBookTypeName(type1);
            if (!check) {
                daoBookType.DeleteBookType(s, type1);
                flag = false;
            } else {
                InputLimit.Warn("不存在该图书类型名称！请重新输入！");
                break;
            }
        }
    }

    public void MBookType(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要修改的图书类型的编号:");
            int readInt = TSUtility.readInt();
            //遍历图书类别，找到输入编号的图书类别
            String s1 = daoBookType.GetBookTypeName(readInt);
            if (s1 != null) {
                System.out.println("请输入新的图书名称（" + s1 + "）:");
                String type = TSUtility.readKeyBoard(30, false);
                boolean check = daoBookType.HasBookTypeName(type);
                if (check){
                    daoBookType.ModifyBookType(s, type, readInt);
                    flag = false;
                } else {
                    InputLimit.Warn("存在该图书类型名称！请重新输入！");
                    break;
                }
            } else {
                InputLimit.Warn("不存在该图书类型的编号！请重新输入！");
                break;
            }
        }
    }

}
