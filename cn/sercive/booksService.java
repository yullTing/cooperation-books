package cn.sercive;

import cn.domain.*;
import com.service.LogService;
import com.utils.InputLimit;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class booksService {
    private static Scanner sc = new Scanner(System.in);
//    public static void main(String[] args) throws ParseException {
//        modify();
//    }

    public static void add(String s) throws ParseException {
        boolean flag=true;
        while (flag){
            System.out.println("请输入你要添加的图书名称:");
            String bookname = TSUtility.readKeyBoard(30, false);
            String sql = "select * from `bookinformation` where `bookname`=?";
            book instance = CURRENCY.getInstance(book.class, sql, bookname);
            if (instance!=null){
                System.out.println("存在该数据！请重新输入！");
                break;
            }else {
                System.out.println("请输入你要添加的图书的ISBN（长度小于10，如tu23454）:");
                String isbn = TSUtility.readKeyBoard(10, false);

                inquiry();
                System.out.println("请选择上述图书类型编号进行添加:");
                int typeid = sc.nextInt();

                System.out.println("请输入你要添加的图书作者:");
                String author = TSUtility.readKeyBoard(30, false);
                System.out.println("请输入你要添加的图书的出版社:");
                String publish = TSUtility.readKeyBoard(30, false);
                System.out.println("请输入你要添加的图书的出版日期(形式：yyyy-MM-dd):");
                //String b = TSUtility.readKeyBoard(9,false);
                String b = InputLimit.InputDate();

                Date publishdate = DateUtils.stringToDate(b,"yyyy-MM-dd");
                System.out.println("请输入你要添加的图书的印刷次数:");
                int publishtime = sc.nextInt();
                System.out.println("请输入你要添加的图书的单价:");
                double unitprice = sc.nextDouble();
                String sql2 = "insert into `bookinformation` values (?,?,?,?,?,?,?,?)";
                CURRENCY.update(sql2,isbn,typeid,bookname,author,publish,publishdate,publishtime,unitprice);
                LogService.AddOperLog("操作员[" + s + "]添加图书" + bookname + "");
                flag=false;
            }
        }
    }

    private static void inquiry() {
        String sql1 = "select id,typename from booktype";
        List<booktype> booktypeList = CURRENCY.getForList(booktype.class, sql1);
        System.out.println("图书编号\t\t" + "图书名称");
        booktypeList.forEach(System.out::println);
        /*for (int i=0;i<booktypeList.size();i++){
            System.out.println(":"+booktypeList.get(i).getId()+"    :"+booktypeList.get(i).getTypename());
        }*/
    }


    public static void query() {
        Queryall();
        System.out.println("是否根据图书名称查询单个信息？（Y/N）");
        char c = TSUtility.readConfirmSelection();
        if (c=='Y') {
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要查询的图书信息的名称:");
                String name = TSUtility.readKeyBoard(30, false);
                String sql = "select * from `bookinformation` where `bookname`=?";
                book instance = CURRENCY.getInstance(book.class, sql, name);
                if (instance == null) {
                    System.out.println("不存在该数据！请重新输入！");
                    flag = false;
                } else {
                    System.out.println("你要查询的图书的信息为:");
                    System.out.println("ISBN\t\t"+"图书名称\t\t" + "图书类型编号\t\t" + "作者\t\t" + "出版社\t\t" + "出版日期\t\t" + "印刷次数\t\t" + "单价");
                    System.out.println(instance.toString());
                    flag = false;
                }
            }
        }
    }

    public static void modify(String s) throws ParseException {
        boolean flag=true;
        while (flag){
            Queryall();
            System.out.println("请输入你要修改的图书信息的名称:");
            String name = TSUtility.readKeyBoard(30, false);
            String sql = "select * from `bookinformation` where `bookname`=?";
            book instance = CURRENCY.getInstance(book.class, sql, name);
            if (instance == null) {
                System.out.println("不存在该数据！请重新输入！");
                break;
            } else {
                inquiry();
                System.out.println("请输入图书类型的编号:");
                int type = TSUtility.readInt();

                System.out.println("请输入图书ISBN（"+instance.getISBN()+"）:");
                String isbn = TSUtility.readString(8, instance.getISBN());

                System.out.println("请输入图书的作者（"+instance.getAuthor()+"）:");
                String author = TSUtility.readString(30, instance.getAuthor());

                System.out.println("请输入图书的出版社（"+instance.getPublish()+"）:");
                String publish = TSUtility.readString(4, instance.getPublish());

                Date date = instance.getPublishdate();
                String s1 = DateUtils.dateToString(date,"yyyy-MM-dd");
                System.out.println("请输入图书的出版日期（"+s1+"）:");
                //String d = TSUtility.readString(9, s1);
                String d = InputLimit.InputDate();
                Date date1 = DateUtils.stringToDate(d,"yyyy-MM-dd");

                System.out.println("请输入图书的印刷次数（"+instance.getPublishtime()+"）:");
                String dept = TSUtility.readString(5, String.valueOf(instance.getPublishtime()));
                int publishtime = Integer.parseInt(dept);
                System.out.println("请输入图书的单价（"+instance.getUnitprice()+"）:");
                String dt = TSUtility.readString(10, String.valueOf(instance.getUnitprice()));
                double untiprice = Double.parseDouble(dt);
                String sql1 = "update `bookinformation` set ISBN = ?,typeid=?,author=?,publish=?,publishdate=?,publishtime=?,unitprice=? where `bookname`=?";
                CURRENCY.update( sql1,isbn,type, author, publish, date1,publishtime,untiprice,name);
                LogService.AddOperLog("操作员[" + s + "]修改图书" + name + "");
                flag=false;
            }
        }
    }

    public static void delete (String s) {
        Queryall();
        boolean flag=true;
        while (flag){
            System.out.println("请输入你要删除的图书名称:");
            String name = TSUtility.readKeyBoard(30, false);
            String sql = "select * from `bookinformation` where `bookname`=?";
            book instance = CURRENCY.getInstance(book.class, sql,name);
            String sql1 = "select * borrowbook` where `ISBN`=?";
            BorrowBook instance1 = CURRENCY.getInstance(BorrowBook.class, sql1, instance.getISBN());
            if (instance1==null) {
                if (instance == null) {
                    System.out.println("不存在该数据！");
                    flag = false;
                } else {
                    String sql2 = "delete from bookinformation where `bookname`=?";
                    CURRENCY.update(sql2, name);
                    LogService.AddOperLog("操作员[" + s + "]删除图书" + name + "");
                    flag = false;
                }
            }else {
                System.out.println("该读者借了图书，不能删除！");
                flag = false;
            }
        }
    }

    private static void Queryall() {
        String sql = "select * from `bookinformation` ";
        List<book> bookList = CURRENCY.getForList(book.class, sql);
        /*for (int i=0;i<bookList.size();i++){
            System.out.println(bookList.get(i).getBookname());
        }*/
        System.out.println("ISBN\t\t"+"图书名称\t\t" + "图书类型编号\t\t" + "作者\t\t" + "出版社\t\t" + "出版日期\t\t" + "印刷次数\t\t" + "单价");
        bookList.forEach(System.out::println);
    }
}

