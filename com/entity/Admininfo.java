package com.entity;

/*
* 管理员信息
* */
public class Admininfo {
    private int adminId; // 管理员用户编号（主键）
    private String adminName; // 管理员用户名称
    private String adminPwd; // 管理员用户密码

    public Admininfo() {
    }

    public Admininfo(int adminId, String adminName, String adminPwd) {
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
        return adminId + "\t" + adminName;// + "\t\t" + adminPwd;
    }
}
