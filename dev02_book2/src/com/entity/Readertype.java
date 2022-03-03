package com.entity;

/*
* 读者类型
* */
public class Readertype {
    private int rtId; // 读者类型编号
    private String rtName; // 读者类型名称
    private int maxborrowNum; // 最大可借阅图书数量
    private int limit; // 最长可借阅天数
    private int borrowMoney; // 图书借阅金额（亦为罚款金额）

    public Readertype() {
    }

    public Readertype(int rtId, String rtName, int maxborrowNum, int limit, int borrowMoney) {
        this.rtId = rtId;
        this.rtName = rtName;
        this.maxborrowNum = maxborrowNum;
        this.limit = limit;
        this.borrowMoney = borrowMoney;
    }

    public int getRtId() {
        return rtId;
    }

    public void setRtId(int rtId) {
        this.rtId = rtId;
    }

    public String getRtName() {
        return rtName;
    }

    public void setRtName(String rtName) {
        this.rtName = rtName;
    }

    public int getMaxborrowNum() {
        return maxborrowNum;
    }

    public void setMaxborrowNum(int maxborrowNum) {
        this.maxborrowNum = maxborrowNum;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(int borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    @Override
    public String toString() {
        return rtId + "\t\t" + rtName + "\t\t" + maxborrowNum + "\t\t" + limit + "\t\t" + borrowMoney;
    }
}
