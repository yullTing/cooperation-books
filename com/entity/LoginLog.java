package com.entity;

/*
* 登录日志
* */
public class LoginLog {
    private int llogId; // 登录日志 编号（主键）
    private String llogTime; // 登录日志 时间
    private String llogDetails; // 登录日志 内容：管理员/操作员【xxx】登录系统

    public LoginLog() {
    }

    public LoginLog(int llogId, String llogTime, String llogDetails) {
        this.llogId = llogId;
        this.llogTime = llogTime;
        this.llogDetails = llogDetails;
    }

    public int getLlogId() {
        return llogId;
    }

    public void setLlogId(int llogId) {
        this.llogId = llogId;
    }

    public String getLlogTime() {
        return llogTime;
    }

    public void setLlogTime(String llogTime) {
        this.llogTime = llogTime;
    }

    public String getLlogDetails() {
        return llogDetails;
    }

    public void setLlogDetails(String llogDetails) {
        this.llogDetails = llogDetails;
    }

    @Override
    public String toString() {
        /*return "Loginlog{" +
                "llogId=" + llogId +
                ", llogTime='" + llogTime + '\'' +
                ", llogDetails='" + llogDetails + '\'' +
                '}';*/
        return llogTime + "\t\t" + llogDetails;
    }
}
