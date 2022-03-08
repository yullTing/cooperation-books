package com.entity;

import java.util.Date;

public class BorrowReturn {
    private String readerid;
    private String ISBN;
    private Date borrowdate;
    private Date returndate;
    private double fine;

    public BorrowReturn() {
    }

    public BorrowReturn(String readerid, String ISBN, Date borrowdate, Date returndate, double fine) {
        this.readerid = readerid;
        this.ISBN = ISBN;
        this.borrowdate = borrowdate;
        this.returndate = returndate;
        this.fine = fine;
    }

    public String getReaderid() {
        return readerid;
    }

    public void setReaderid(String readerid) {
        this.readerid = readerid;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Date getBorrowdate() {
        return borrowdate;
    }

    public void setBorrowdate(Date borrowdate) {
        this.borrowdate = borrowdate;
    }

    public Date getReturndate() {
        return returndate;
    }

    public void setReturndate(Date returndate) {
        this.returndate = returndate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return  "  " + readerid +
                " \t\t" + ISBN +
                " \t\t" + borrowdate +
                " \t\t" + returndate +
                " \t\t" + fine;
    }
}
