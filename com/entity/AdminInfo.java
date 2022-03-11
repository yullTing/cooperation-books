package com.entity;

/*
* 操作员信息
* */
public class AdminInfo {
    private int adminId; // 管理员ID
    private String adminName; // 管理员名称
    private String adminPwd; // 管理员密码
    private String adminTel; // 联系电话

    public AdminInfo() {
    }

    public AdminInfo(int adminId, String adminName, String adminPwd, String adminTel) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.adminPwd = adminPwd;
        this.adminTel = adminTel;
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

    public String getAdminTel() {
        return adminTel;
    }

    public void setAdminTel(String adminTel) {
        this.adminTel = adminTel;
    }

    @Override
    public String toString() {
        return adminId + "\t\t" + adminName + "\t\t" + adminTel;//+ "\t\t" + operPwd + "\t\t" + adminId;
    }
}
