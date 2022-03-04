package cn.sercive;

import cn.domain.CURRENCY;
import cn.domain.TSUtility;
import cn.domain.booktype;
import com.service.LogService;

import java.util.List;
import java.util.Scanner;

public class BookCategoryService {
    private static Scanner sc = new Scanner(System.in);
    /*public static void main(String[] args) {
       // see();
//        add();
//        delete();
        modify();
    }*/

    public static void see() {
        String sql = "select * from `booktype` ";
        List<booktype> booktypeList = CURRENCY.getForList(booktype.class, sql);
        System.out.println("图书类型编号" + "\t\t图书类型名称");
        booktypeList.forEach(System.out::println);
    }

    public static void add(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要增加的图书类型名称:");
            String booktype = TSUtility.readKeyBoard(30, false);
            boolean check = CheckDuplicate(booktype);
            if (check == true) {
                String sql1 = "insert into `booktype` set typename=?";
                CURRENCY.update(sql1, booktype);
                System.out.println("添加成功！");
                LogService.AddOperLog("操作员[" + s + "]添加图书类型" + booktype);
                flag = false;
            } else {
                System.out.println("存在该图书类型名称！请重新输入！");
                break;
            }
        }
    }

    public static void delete(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要删除的图书类型名称:");
            String type1 = TSUtility.readKeyBoard(30, false);
            boolean check = CheckDuplicate(type1);
            if (check == false) {
                String sql1 = "delete from booktype where typename=?";
                CURRENCY.update(sql1, type1);
                System.out.println("删除成功！");
                LogService.AddOperLog("操作员[" + s + "]删除图书类型" + type1);
                flag = false;
            } else {
                System.out.println("不存在该图书类型名称！请重新输入！");
                break;
            }
        }
    }

    public static void modify(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要修改的图书类型的编号:");
            int readInt = TSUtility.readInt();
            String s1 = CheckDuplicate1(readInt);
            if (s1 != null) {
                System.out.println("请输入新的图书名称（" + s1 + "）:");
                String type = TSUtility.readKeyBoard(30, false);
                String sql1 = "update booktype set typename=? where id=?";
                CURRENCY.update(sql1, type, readInt);
                System.out.println("修改成功！");
                LogService.AddOperLog("操作员[" + s + "]修改图书类型" + type);
                flag = false;
            } else {
                System.out.println("不存在该图书类型的编号！请重新输入！");
                break;
            }
        }

    }

    public static boolean CheckDuplicate(String b) {
        boolean flag = true;
        String sql = "select typename from `booktype` ";
        List<booktype> booktypeList = CURRENCY.getForList(booktype.class, sql);
        for (int i = 0; i < booktypeList.size(); i++) {
            if (booktypeList.get(i).getTypename().equals(b)) {
                flag = false;
            }
        }
        return flag;
    }

    public static String CheckDuplicate1(int i) {
        String result = null;
        String sql = "select * from `booktype` ";
        List<booktype> booktypeList = CURRENCY.getForList(booktype.class, sql);
        for (booktype bt : booktypeList) {
            if (bt.getId() == i) {
                result = bt.getTypename();
            }
        }
        return result;
    }
}
