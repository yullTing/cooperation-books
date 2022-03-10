package com.entity;

public class OperatorInfo {
    private int operId; // 操作员用户编号（主键）
    private String operName; // 操作员用户名称
    private String operPwd; // 操作员用户名
    private int adminId; // 管理员ID

    public OperatorInfo() {
    }

    public OperatorInfo(int operId, String operName, String operPwd, int adminId) {
        this.operId = operId;
        this.operName = operName;
        this.operPwd = operPwd;
        this.adminId = adminId;
    }

    public int getOperId() {
        return operId;
    }

    public void setOperId(int operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName;
    }

    public String getOperPwd() {
        return operPwd;
    }

    public void setOperPwd(String operPwd) {
        this.operPwd = operPwd;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public String toString() {
        return operId + "\t\t" + operName + "\t\t" + adminId;
    }
}
