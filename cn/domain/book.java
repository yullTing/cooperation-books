package cn.domain;

import java.util.Date;

public class book {
    private String ISBN;
    private int typeid;
    private String bookname;
    private String author;
    private String publish;
    private Date publishdate;
    private int publishtime;
    private double unitprice;

    public String getISBN() {
        return ISBN;
    }

    public int getTypeid() {
        return typeid;
    }

    public String getBookname() {
        return bookname;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublish() {
        return publish;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public int getPublishtime() {
        return publishtime;
    }

    public double getUnitprice() {
        return unitprice;
    }

    @Override
    public String toString() {
        return "  " + ISBN +
                " \t\t" + bookname +
                " \t\t" + typeid +
                " \t\t" + author +
                " \t\t" + publish +
                " \t\t" + publishdate +
                " \t\t" + publishtime +
                " \t\t" + unitprice;
    }
}
