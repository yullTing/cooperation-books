package com.service;

import com.utils.InputLimit;
import com.utils.TSUtility;
import com.dao.ReaderTypeDAO;
import com.entity.ReaderType;

import java.util.List;

public class ReaderTypeService {

    private final ReaderTypeDAO daoReaderType = new ReaderTypeDAO();

    public void QReaderType() {
        daoReaderType.QueryBookType();
    }

    public void AReaderType(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要增加的读者类型名称:");
            String readerType = TSUtility.readKeyBoard(20, false);
            boolean check = daoReaderType.HasBookTypeName(readerType);
            if (check) {
                daoReaderType.AddReaderType(s, readerType);
                flag = false;
            } else {
                InputLimit.Warn("存在该读者类型名称！请重新输入！");
                break;
            }
        }
    }

    public void DReaderType(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要删除的读者类型名称:");
            String type1 = TSUtility.readKeyBoard(20, false);
            boolean check = daoReaderType.HasBookTypeName(type1);
            if (!check) {
                daoReaderType.DeleteReaderType(s, type1);
                flag = false;
            } else {
                System.out.println("不存在该读者类型名称！请重新输入！");
                break;
            }
        }
    }

    public void MReaderType(String s) {
        boolean flag = true;
        while (flag) {
            System.out.println("请输入你要修改的读者类型的编号:");
            int readInt = TSUtility.readInt();
            boolean check = daoReaderType.HasBookTypeId(readInt);
            if (!check) {
                ReaderType readerType = new ReaderTypeDAO().getRTByReaderTypeId(readInt);
                if (readerType == null) {
                    System.out.println("输入错误！请重新输入！");
                    break;
                } else {
                    System.out.println("请输入新的读者类型名称（" + readerType.getTypename() + "）:");
                    String type = InputLimit.ModifyString(readerType.getTypename());

                    System.out.println("输入新的最大借阅数量（" + readerType.getMaxborrownum() + "）:");
                    int maxNum = InputLimit.ModifyInt(readerType.getBorrowMoney());

                    System.out.println("输入新的最大借阅天数（" + readerType.getLimit() + "）:");
                    int limit = InputLimit.ModifyInt(readerType.getLimit());

                    daoReaderType.ModifyReaderType(s, type, maxNum, limit, readInt);
                    flag = false;
                }
            } else {
                InputLimit.Warn("不存在该读者类型的编号！请重新输入！");
                break;
            }
        }

    }

    //查询读者类型，仅显示类型编号、类型名称
    public void ShowReaderTypeIdName() {

    }

    //罚金设置。罚金设置跟随读者类型
    public void QueryModifyPenalty(String s) {
        List<ReaderType> penaltyInfo = daoReaderType.showPenaltyInfo();

        System.out.println("是否修改罚金设置？（Y/N）");
        char c = TSUtility.readConfirmSelection();
        if (c == 'Y') {
            for (ReaderType rt : penaltyInfo) {
                System.out.println("请给'" + rt.getTypename() + "'类型读者设置超过一天的罚金（" + rt.getPenalMoney() + "）:");
                double aDouble = TSUtility.readDouble();
                String name = rt.getTypename();

                daoReaderType.QueryModifyPenaltySetting(aDouble, name);
            }
            new LogService().AOL("操作员[" + s + "]修改罚金设置");
        }
    }

}
