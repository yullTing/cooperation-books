package com.entity;

public class BookType {
    private int id;
    private String typename;

    public BookType() {
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
