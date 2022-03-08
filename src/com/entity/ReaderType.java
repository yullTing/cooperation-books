package com.entity;

public class ReaderType {
    private int id;
    private String typename;
    private int maxborrownum;
    private int limit;
    private int penalMoney;//逾期未还书每天需支付金额
    private int borrowMoney; //每类读者的图书借阅总金额


    public ReaderType() {
    }

    public ReaderType(int id, String typename, int maxborrownum, int limit, int borrowMoney) {
        this.id = id;
        this.typename = typename;
        this.maxborrownum = maxborrownum;
        this.limit = limit;
        this.borrowMoney = borrowMoney;
    }

    public int getId() {
        return id;
    }

    public String getTypename() {
        return typename;
    }

    public int getMaxborrownum() {
        return maxborrownum;
    }

    public int getLimit() {
        return limit;
    }

    public int getPenalMoney() {
        return penalMoney;
    }

    public void setPenalMoney(int penalMoney) {
        this.penalMoney = penalMoney;
    }

    public int getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(int borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    @Override
    public String toString() {
        return "  " + id +
                " \t\t" + typename +
                " \t\t" + maxborrownum +
                " \t\t" + limit +
                " \t\t" + penalMoney +
                " \t\t" + borrowMoney;
    }
}
