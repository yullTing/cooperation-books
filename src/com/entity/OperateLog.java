package com.entity;

public class OperateLog {
    private int ologId; //员工工作日志 编号(主键)
    private String ologTime; //员工工作日志 时间
    private String ologDetails; //员工工作日志 内容

    public OperateLog() {
    }

    public OperateLog(int ologId, String ologTime, String ologDetails) {
        this.ologId = ologId;
        this.ologTime = ologTime;
        this.ologDetails = ologDetails;
    }

    public int getOlogId() {
        return ologId;
    }

    public void setOlogId(int ologId) {
        this.ologId = ologId;
    }

    public String getOlogTime() {
        return ologTime;
    }

    public void setOlogTime(String ologTime) {
        this.ologTime = ologTime;
    }

    public String getOlogDetails() {
        return ologDetails;
    }

    public void setOlogDetails(String ologDetails) {
        this.ologDetails = ologDetails;
    }

    @Override
    public String toString() {
        return ologTime + "\t\t" + ologDetails;
    }
}
