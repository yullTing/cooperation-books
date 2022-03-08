package com.service;

import com.dao.BorrowReturnDAO;
import com.dao.ReaderInfoDAO;
import com.dao.ReaderTypeDAO;
import com.entity.BorrowReturn;
import com.entity.ReaderInfo;
import com.utils.DateUtils;
import com.utils.InputLimit;
import com.utils.TSUtility;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class ReaderService {

    private final ReaderInfoDAO daoReader = new ReaderInfoDAO();
    private final ReaderTypeDAO daoReaderType = new ReaderTypeDAO();

    public void AddReaderInfo(String s) {
        try {
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要添加的读者的编号（如001）:");
                String number = TSUtility.readKeyBoard(8, false);

                ReaderInfo readerInfo = daoReader.QueryReaderByReaderId(number);

                if (readerInfo != null) {
                    InputLimit.Warn("存在该数据！请重新输入！");
                    break;
                } else {
                    System.out.println("读者类型有：");
                    daoReaderType.ShowReaderTypeIdName();

                    System.out.println("请选择上述的读者类型编号进行添加:");
                    int type = TSUtility.readInt();
                    boolean b = new ReaderTypeDAO().HasBookTypeId(type);
                    if (b) {
                        InputLimit.Warn("读者类型编号输入有误，请重新输入！");
                        break;
                    } else {
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

                        daoReader.AddReader(s, number, type, name, age, sex, phone, dept, date);
                        flag = false;
                    }
                }
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    public void QueryReaderInfoByReaderId() {
        QAllReaderInfo();
        System.out.println("是否根据读者编号查询单个信息？（Y/N）");
        char c = TSUtility.readConfirmSelection();
        if (c=='Y') {
            boolean flag = true;
            while (flag) {
                System.out.println("请输入你要查询的读者编号:");
                String number = TSUtility.readKeyBoard(8, false);

                ReaderInfo readerInfo = daoReader.QueryReaderByReaderId(number);

                if (readerInfo == null) {
                    InputLimit.Warn("不存在该数据！请重新输入！");
                    flag = false;
                } else {
                    System.out.println("你要查询的读者的信息为:\n" + readerInfo.toString());
                    flag = false;
                }
            }
        }
    }

    public void ModifyReaderInfo(String s) {
        try {
            boolean flag = true;
            while (flag) {
                QAllReaderInfo();
                System.out.println("请输入你要修改的读者信息的编号:");
                String number = TSUtility.readKeyBoard(8, false);

                ReaderInfo readerInfo = daoReader.QueryReaderByReaderId(number);

                if (readerInfo == null) {
                    InputLimit.Warn("不存在该数据！请重新输入！");
                    break;
                } else {
                    daoReaderType.ShowReaderTypeIdName();
                    System.out.println("请输入你要修改的读者类型编号（" + readerInfo.getType() + "）:");
                    String t = TSUtility.readString(2, String.valueOf(readerInfo.getType()));
                    int type = Integer.parseInt(t);

                    System.out.println("请输入你要修改的读者姓名（" + readerInfo.getName() + "）:");
                    String name = TSUtility.readString(20, readerInfo.getName());

                    System.out.println("请输入你要修改的读者年龄编号（" + readerInfo.getAge() + "）:");
                    String n = TSUtility.readString(3, String.valueOf(readerInfo.getAge()));
                    int age = Integer.parseInt(t);

                    System.out.println("请输入你要修改的读者性别（" + readerInfo.getSex() + "）:");
                    //String sex = TSUtility.readString(4, instance.getSex());
                    String sex = InputLimit.ModifyGender(readerInfo.getSex());

                    System.out.println("请输入你要修改的读者联系电话（" + readerInfo.getPhone() + "）:");
                    String phone = TSUtility.readString(11, readerInfo.getPhone());

                    System.out.println("请输入你要修改的读者所在系部（" + readerInfo.getDept() + "）:");
                    String dept = TSUtility.readString(10, readerInfo.getDept());

                    Date date = readerInfo.getRegdate();
                    String s1 = DateUtils.dateToString(date, "yyyy-MM-dd");
                    System.out.println("请输入你要修改的读者注册日期（" + s1 + "）:");
                    //String d = TSUtility.readString(12, s1);
                    String d = InputLimit.ModifyDate(s1);

                    Date date1 = DateUtils.stringToDate(d, "yyyy-MM-dd");
                    //System.out.println("test:"+date1);

                    daoReader.ModifyReader(s, type, name, age, sex, phone, dept, date1, number);
                    flag = false;
                }
            }
        } catch (ParseException e){
            e.printStackTrace();
        }
    }

    public void DeleteReaderInfo(String s) {
        boolean flag = true;
        while (flag) {
            QAllReaderInfo();
            System.out.println("请输入你要删除的读者信息的编号:");
            String number = TSUtility.readKeyBoard(8, false);

            ReaderInfo readerInfo = daoReader.QueryReaderByReaderId(number);

            if (readerInfo == null) {
                InputLimit.Warn("不存在该数据！");
                flag = false;
            } else {
                //根据读者Id 查询该读者是否有借阅记录
                List<BorrowReturn> borrowBooks = new BorrowReturnDAO().whetherBorrowBook(number);

                if (borrowBooks.size() == 0) {
                    daoReader.DeleteReader(s, number);
                } else {
                    InputLimit.Warn("该读者借了图书，不能删除！");
                }
                flag = false;
            }
        }
    }

    private void QAllReaderInfo() {
        daoReader.QueryAllReader();
    }
}
