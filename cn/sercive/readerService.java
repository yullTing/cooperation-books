package cn.sercive;

import cn.domain.*;
import com.service.LogService;
import com.utils.InputLimit;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class readerService {
    /*public static void main(String[] args) throws ParseException {
//        add();
//        query();
//        modify();
        delete();
    }*/

    public static void add(String s) throws ParseException {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要添加的读者的编号（如001）:");
            String number = TSUtility.readKeyBoard(8, false);
            String sql = "select name from `readerinformation` where `readerid`=?";
            reader instance = CURRENCY.getInstance(reader.class, sql, number);
            if (instance != null) {
                System.out.println("存在该数据！请重新输入！");
                break;
            } else {
                System.out.println("读者类型有：");
                ReaderCategoryService.ShowReaderTypeIdName();

                System.out.println("请选择上述的读者类型编号进行添加:");
                int type = TSUtility.readInt();
                System.out.println("请输入你要添加的读者姓名:");
                String name = TSUtility.readKeyBoard(20, false);
                System.out.println("请输入你要添加的读者的年龄:");
                int age = TSUtility.readInt();
                System.out.println("请输入你要添加的读者的性别:");
                //String sex = TSUtility.readKeyBoard(4, false);
                String sex = InputLimit.InputGender();
                System.out.println("请输入你要添加的读者的联系电话:");
                //String phone = TSUtility.readKeyBoard(11,false);
                String phone = InputLimit.InputTel();
                System.out.println("请输入你要添加的读者所在的系部:");
                String dept = TSUtility.readKeyBoard(20, false);
                System.out.println("请输入你要添加的读者注册的日期(形式：yyyy-MM-dd):");
                //String regdate = TSUtility.readKeyBoard(12,false);
                String regdate = InputLimit.InputDate();
                Date date = DateUtils.stringToDate(regdate, "yyyy-MM-dd");
                String sql2 = "insert into `readerinformation` values (?,?,?,?,?,?,?,?)";
                CURRENCY.update(sql2, number, type, name, age, sex, phone, dept, date);
                System.out.println("读者信息添加成功！");
                LogService.AddOperLog("操作员[" + s + "]添加读者（编号" + number + "）" + name);
                flag = false;
            }
        }

    }

    public static void query() {
        Queryall();
        System.out.println("是否根据读者编号查询单个信息？（Y/N）");
        char c = TSUtility.readConfirmSelection();
        if (c=='Y') {
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要查询的读者编号:");
                String number = TSUtility.readKeyBoard(8, false);
                String sql = "select * from `readerinformation` where `readerid`=?";
                reader instance = CURRENCY.getInstance(reader.class, sql, number);
                if (instance == null) {
                    System.out.println("不存在该数据！请重新输入！");
                    flag = false;
                } else {
                    System.out.println("你要查询的读者的信息为:\n" + instance);
                    flag = false;
                }
            }
        }
    }

    public static void modify(String s) throws ParseException {
        boolean flag = true;
        while (flag) {
            Queryall();
            System.out.println("请输入你要修改的读者信息的编号:");
            String number = TSUtility.readKeyBoard(8, false);
            String sql = "select * from `readerinformation` where `readerid`=?";
            reader instance = CURRENCY.getInstance(reader.class, sql, number);
            if (instance == null) {
                System.out.println("不存在该数据！请重新输入！");
                break;
            } else {
                ReaderCategoryService.ShowReaderTypeIdName();
                System.out.println("请输入你要修改的读者类型编号（" + instance.getType() + "）:");
                String t = TSUtility.readString(2, String.valueOf(instance.getType()));
                int type = Integer.parseInt(t);

                System.out.println("请输入你要修改的读者姓名（" + instance.getName() + "）:");
                String name = TSUtility.readString(20, instance.getName());

                System.out.println("请输入你要修改的读者年龄编号（" + instance.getAge() + "）:");
                String n = TSUtility.readString(3, String.valueOf(instance.getAge()));
                int age = Integer.parseInt(t);

                System.out.println("请输入你要修改的读者性别（" + instance.getSex() + "）:");
                //String sex = TSUtility.readString(4, instance.getSex());
                String sex = InputLimit.ModifyGender(instance.getSex());

                System.out.println("请输入你要修改的读者联系电话（" + instance.getPhone() + "）:");
                String phone = TSUtility.readString(11, instance.getPhone());

                System.out.println("请输入你要修改的读者所在系部（" + instance.getDept() + "）:");
                String dept = TSUtility.readString(10, instance.getDept());

                Date date = instance.getRegdate();
                String s1 = DateUtils.dateToString(date, "yyyy-MM-dd");
                System.out.println("请输入你要修改的读者注册日期（" + s1 + "）:");
                //String d = TSUtility.readString(12, s1);
                String d = InputLimit.ModifyDate(s1);

                Date date1 = DateUtils.stringToDate(d, "yyyy-MM-dd");
                //System.out.println("test:"+date1);

                String sql1 = "update `readerinformation` set `type`=?,`name`=?,`age`=?,`sex`=?,`phone`=?,`dept`=?,`regdate`=? where readerid = ?";
                CURRENCY.update(sql1, type, name, age, sex, phone, dept, date1, number);
                System.out.println("读者信息修改成功！");
                LogService.AddOperLog("操作员[" + s + "]修改读者（编号" + number + "）" + name);
                flag = false;
            }
        }
    }

    public static void delete(String s) {
        boolean flag = true;
        while (flag) {
            Queryall();
            System.out.println("请输入你要删除的读者信息的编号:");
            String number = TSUtility.readKeyBoard(8, false);
            String sql = "select * from `readerinformation` where `readerid`=?";
            reader instance = CURRENCY.getInstance(reader.class, sql, number);
            String sql1 = "select * from `borrowbook` where `readerid`=?";
            BorrowBook instance1 = CURRENCY.getInstance(BorrowBook.class, sql1, number);
            if (instance1 == null) {
                if (instance == null) {
                    System.out.println("不存在该数据！");
                    flag = false;
                } else {
                    String sql2 = "delete from readerinformation where readerid=?";
                    CURRENCY.update(sql2, number);
                    System.out.println("读者信息删除成功！");
                    LogService.AddOperLog("操作员[" + s + "]删除读者" + number);
                    flag = false;
                }
            } else {
                System.out.println("该读者借了图书，不能删除！");
                flag = false;
            }
        }
    }

    private static void Queryall() {
        String sql = "select * from `readerinformation` ";
        List<reader> readersList = CURRENCY.getForList(reader.class, sql);
        /*for (int i = 0; i < readersList.size(); i++) {
            System.out.println(readersList.get(i).getName());
        }*/
        System.out.println("读者编号\t\t" + "读者类型\t\t" + "读者名称\t\t" + "年龄\t\t" + "性别\t\t" + "联系电话\t\t" + "所在系部\t\t" + "注册时间");
        readersList.forEach(System.out::println);
    }
}
