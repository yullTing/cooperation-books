package com.dao;

import com.entity.ReaderType;
import com.implement.ReaderTypeImpl;
import com.service.LogService;
import com.utils.InputLimit;

import java.util.List;

public class ReaderTypeDAO extends BaseDAO<ReaderType> implements ReaderTypeImpl {
    @Override
    public void QueryBookType() {
        String sql = "select * from readertype";
        List<ReaderType> readerTypeList = doQueryResultList(sql);

        if (readerTypeList.size() != 0) {
            System.out.println("读者类别编号" + "\t类别名称" + "\t最多可借阅图书数量" + "\t最大可借阅天数");
            for (ReaderType list : readerTypeList) {
                System.out.println(" " + list.getId() + "\t\t" + list.getTypename() + "\t\t" + list.getMaxborrownum() + "\t\t" + list.getLimit());
            }
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public void AddReaderType(String s, String readerType) {
        String sql1 = "INSERT INTO readertype VALUES(NULL, ?, 30, 7, 10, 200)";
        doUpdate(sql1, readerType);
        InputLimit.Notice("添加成功！");
        new LogService().AOL("操作员[" + s + "]添加读者类型" + readerType);
    }

    @Override
    public void DeleteReaderType(String s, String type1) {
        String sql1 = "delete from readertype where typename=?";
        doUpdate(sql1, type1);
        InputLimit.Notice("删除成功！");
        new LogService().AOL("操作员[" + s + "]删除读者类型" + type1);
    }

    @Override
    public void ModifyReaderType(String s, String type, int readInt) {
        String sql1 = "update readertype set typename=? where id=?";
        doUpdate(sql1, type, readInt);
        InputLimit.Notice("修改成功！");
        new LogService().AOL("操作员[" + s + "]修改读者类型" + type);
    }

    @Override
    public boolean HasBookTypeName(String bookTypeName) {
        boolean flag = true;
        String sql = "select * from `readertype` ";
        List<ReaderType> readerTypeList = doQueryResultList(sql);
        for (ReaderType rt : readerTypeList) {
            if (rt.getTypename().equals(bookTypeName)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean HasBookTypeId(int bookTypeId) {
        boolean flag = true;
        String sql = "select * from `readertype` ";
        List<ReaderType> readerTypeList = doQueryResultList(sql);

        for(ReaderType rt : readerTypeList){
            if (rt.getId() == bookTypeId) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public void ShowReaderTypeIdName() {
        String sql1 = "select id,typename from readertype";
        List<ReaderType> readerTypes = doQueryResultList(sql1);

        if (readerTypes.size() != 0) {
            System.out.println("读者类型编号\t\t" + "读者类型名称");
            for (ReaderType rt : readerTypes) {
                System.out.println(rt.getId() + "\t\t " + rt.getTypename());
            }
        } else {
            InputLimit.Warn("没有找到任何信息");
        }
    }

    @Override
    public List<ReaderType> showPenaltyInfo() {
        List<ReaderType> penaltyInfo;
        String sql = "select * from readertype";
        penaltyInfo = doQueryResultList(sql);

        for (ReaderType penalty : penaltyInfo){
            System.out.println(penalty.getId() + "\t\t " + penalty.getTypename() + "\t\t " + penalty.getPenalMoney());
        }
        return penaltyInfo;
    }

    @Override
    public void QueryModifyPenaltySetting(double aDouble, String name) {
        String sql1 = "UPDATE readertype SET penalMoney = ? WHERE typename = ?";
        doUpdate(sql1, aDouble, name);
        InputLimit.Notice("修改成功！");
    }

    //查询规定可借阅的最多图书数量
    @Override
    public ReaderType getRTByReaderTypeId(int typeId) {
        ReaderType readerType;
        String sql1 = "select * from readertype where id = ?";
        readerType = doQueryOneData(sql1, typeId);

        return readerType;
    }

}
