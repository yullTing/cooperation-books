package cn.domain;

public class booktype {
    private int id;
    private String typename;

    public booktype() {
    }

    public int getId() {
        return id;
    }

    public String getTypename() {
        return typename;
    }

    @Override
    public String toString() {
        return "  " + id + " \t\t" + typename ;
    }
}
