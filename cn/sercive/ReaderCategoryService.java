package cn.sercive;

import cn.domain.CURRENCY;
import cn.domain.TSUtility;
import cn.domain.reader;
import cn.domain.readerType;
import com.service.LogService;

import java.util.List;
import java.util.Scanner;

public class ReaderCategoryService {
    /*public static void main(String[] args) {
        // see();
//        add();
//        delete();
        modify();
    }*/
    private static Scanner sc = new Scanner(System.in);
    public static void see() {
        String sql = "select typename from `readertype` ";
        List<readerType> readerTypeList = CURRENCY.getForList(readerType.class, sql);
        for(int i=0;i<readerTypeList.size();i++){
            System.out.println(readerTypeList.get(i).getTypename());
        }
    }

    public static void add(String s) {
        boolean flag=true;
        while (flag) {
            System.out.println("请输入你要增加的读者类型名称:");
            String readertype = TSUtility.readKeyBoard(20, false);
            boolean check = CheckDuplicate(readertype);
            if (check == true) {
                String sql1 = "insert into `readertype` set typename=?";
                CURRENCY.update(sql1, readertype);
                System.out.println("添加成功！");
                LogService.AddOperLog("操作员[" + s + "]添加读者类型" + readertype);
                flag=false;
            } else {
                System.out.println("存在该读者类型名称！请重新输入！");
                break;
            }
        }
    }

    public static void delete(String s) {
        boolean flag=true;
        while (flag) {
            System.out.println("请输入你要删除的读者类型名称:");
            String type1 = TSUtility.readKeyBoard(20, false);
            boolean check = CheckDuplicate(type1);
            if (check == false) {
                String sql1 = "delete from readertype where typename=?";
                CURRENCY.update(sql1, type1);
                System.out.println("删除成功！");
                LogService.AddOperLog("操作员[" + s + "]删除读者类型" + type1);
                flag=false;
            } else {
                System.out.println("不存在该读者类型名称！请重新输入！");
                break;
            }
        }
    }

    public static void modify(String s) {
        boolean flag=true;
        while (flag) {
            System.out.println("请输入你要修改的读者类型的编号:");
            int readInt = TSUtility.readInt();
            boolean check = CheckDuplicate1(readInt);
            if (check == false) {
                System.out.println("请输入你要将读者编号为"+readInt+"的读者类型名称修改为:");
                String type = TSUtility.readKeyBoard(20, false);
                String sql1 = "update readertype set typename=? where id=?";
                CURRENCY.update(sql1, type,readInt);
                System.out.println("修改成功！");
                LogService.AddOperLog("操作员[" + s + "]修改读者类型" + type);
                flag=false;
            } else {
                System.out.println("不存在该读者类型的编号！请重新输入！");
                break;
            }
        }

    }
    public static boolean CheckDuplicate(String b){
        boolean flag=true;
        String sql = "select typename from `readertype` ";
        List<readerType> readerTypeList = CURRENCY.getForList(readerType.class, sql);
        for(int i=0;i<readerTypeList.size();i++){
            if (readerTypeList.get(i).getTypename().equals(b)){
                flag=false;
            }
        }
        return flag;
    }

    public static boolean CheckDuplicate1(int i){
        boolean flag=true;
        String sql = "select id from `readertype` ";
        List<readerType> readerTypeList = CURRENCY.getForList(readerType.class, sql);
        for(int j=0;j<readerTypeList.size();j++){
            if (readerTypeList.get(j).getId()==i){
                flag=false;
            }
        }
        return flag;
    }

    public static void AllReaderType() {
        String sql = "select * from readertype";
        List<readerType> readerTypes = CURRENCY.getForList(readerType.class, sql);
        /*for (int i = 0; i < readersList.size(); i++) {
            System.out.println(readersList.get(i).getName());
        }*/
        System.out.println("读者类型编号\t\t" + "读者类型");
        readerTypes.forEach(System.out::println);
    }
}
