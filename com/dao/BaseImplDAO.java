package com.dao;

import com.implement.BaseImpl;

import java.util.ArrayList;

public class BaseImplDAO<T> implements BaseImpl<T> {
    @Override
    public T doQueryOneData(String sql, Object... args) {
        return null;
    }

    @Override
    public ArrayList<T> doQueryResultList(String sql, Object... args) {
        return null;
    }

    @Override
    public int doUpdate(String sql, Object... args) {
        return 0;
    }
}
