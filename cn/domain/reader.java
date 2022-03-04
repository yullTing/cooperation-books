package cn.domain;

import java.util.Date;

public class reader {
    private String readerid;
    private int type;
    private String name;
    private int age;
    private String sex;
    private String phone;
    private String dept;
    private Date regdate;

    public String getReaderid() {
        return readerid;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public String getPhone() {
        return phone;
    }

    public String getDept() {
        return dept;
    }

    public Date getRegdate() {
        return regdate;
    }

    @Override
    public String toString() {
        return "  " + readerid +
                " \t\t" + type +
                " \t\t" + name +
                " \t\t" + age +
                " \t\t" + sex +
                " \t\t" + phone +
                " \t\t" + dept +
                " \t\t" + regdate;
    }
}
