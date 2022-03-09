package com.implement;

import java.util.ArrayList;

public interface BaseImpl<T> {

    //通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）
    T doQueryOneData(String sql, Object... args);

    //通用的查询操作，用于返回数据表中的多条记录构成的集合（version 2.0：考虑上事务）
    ArrayList<T> doQueryResultList(String sql, Object... args);

    //通用的增/删/改操作
    int doUpdate(String sql, Object...args);
}
