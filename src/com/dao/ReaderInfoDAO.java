package com.dao;

import com.entity.ReaderInfo;
import com.implement.ReaderInfoImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.Date;
import java.util.List;

public class ReaderInfoDAO extends BaseDAO<ReaderInfo> implements ReaderInfoImpl {
    @Override
    public void AddReader(String s, String number, int type, String name, int age, String sex, String phone, String dept, Date date) {
        String sql2 = "insert into `readerinformation` values (?,?,?,?,?,?,?,?)";
        doUpdate(sql2, number, type, name, age, sex, phone, dept, date);
        InputLimit.Notice("读者信息添加成功！");
        new LogService().AOL("操作员[" + s + "]添加读者（编号" + number + "）" + name);

    }

    @Override
    public ReaderInfo QueryReaderByReaderId(String number) {
        ReaderInfo instance;
        String sql = "select * from `readerinformation` where `readerid`=?";
        instance = doQueryOneData(sql, number);

        return instance;
    }

    @Override
    public void QueryAllReader() {
        String sql = "select * from `readerinformation` ";
        List<ReaderInfo> readersList = doQueryResultList(sql);

        if (readersList.size() != 0) {
            System.out.println("读者编号\t\t" + "读者类型\t\t" + "读者名称\t\t" + "年龄\t\t" + "性别\t\t" + "联系电话\t\t" + "所在系部\t\t" + "注册时间");
            readersList.forEach(System.out::println);
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public void ModifyReader(String s, int type, String name, int age, String sex, String phone, String dept, Date date1, String number) {
        String sql1 = "update `readerinformation` set `type`=?,`name`=?,`age`=?,`sex`=?,`phone`=?,`dept`=?,`regdate`=? where readerid = ?";
        doUpdate(sql1, type, name, age, sex, phone, dept, date1, number);
        InputLimit.Notice("读者信息修改成功！");
        new LogService().AOL("操作员[" + s + "]修改读者（编号" + number + "）" + name);
    }

    @Override
    public void DeleteReader(String s, String number) {
        String sql2 = "delete from readerinformation where readerid=?";
        doUpdate(sql2, number);
        InputLimit.Notice("读者信息删除成功！");
        new LogService().AOL("操作员[" + s + "]删除读者" + number);
    }
}
