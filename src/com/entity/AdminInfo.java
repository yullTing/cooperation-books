package com.entity;

/*
* 操作员信息
* */
public class AdminInfo {
    private int adminId; // 管理员ID
    private String adminName; // 管理员名称
    private String adminPwd; // 管理员密码

    public AdminInfo() {
    }

    public AdminInfo(int adminId, String adminName, String adminPwd) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPwd = adminPwd;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    @Override
    public String toString() {
        return adminId + "\t\t" + adminName;//+ "\t\t" + operPwd + "\t\t" + adminId;
    }
}
