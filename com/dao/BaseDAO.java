package com.dao;

import com.implement.BaseImpl;
import com.utils.InputLimit;
import com.utils.JDBCUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/*
* 封装了针对于数据表的通用的操作
* 数据的查和增/删/改代码
* */
//public abstract class BaseDAO<T> implements BaseImpl<T> {
public class BaseDAO<T> implements BaseImpl<T> {

    private Class<T> clazz;
    private com.mysql.jdbc.Connection conn;

    //获取连接
    {
        try {
            conn = (Connection) JDBCUtils.getConnResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取当前BaseDAO的子类继承的父类中的泛型
    {
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        //获取了父类的泛型参数
        Type[] typeArguments = paramType.getActualTypeArguments();
        //泛型的第一个参数
        clazz = (Class<T>) typeArguments[0];
    }

    /*
    * 通用的查询操作，用于返回数据表中的一条记录（version 2.0：考虑上事务）
    * */
    @Override
    public T doQueryOneData(String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            try {
                rs = ps.executeQuery();
            } catch (MySQLSyntaxErrorException e){
                InputLimit.Warn("");
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                return t;
            } else {
                InputLimit.Warn("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.CloseResources(null, ps, rs);
        }
        return null;
    }


    /*
    * 用于查询特殊值的通用的方法，如查询总行数
    * */
    /*public <E> E doQuerySpecialValue(String sql){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            *//*for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }*//*
            try {
                rs = ps.executeQuery();
            } catch (MySQLSyntaxErrorException e){
                InputLimit.Warn("");
            }
            if(rs.next()){
                return (E) rs.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.CloseResources(null, ps, rs);
        }
        return null;
    }*/


    /*
    * 通用的查询操作，用于返回数据表中的多条记录构成的集合（version 2.0：考虑上事务）
    * */
    @Override
    public ArrayList<T> doQueryResultList(String sql, Object... args) {//
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            if (args!=null) {
                //System.out.println("test:" + args.length);
                for (int i = 0; i < args.length; i++) {
                    //System.out.println("test:" + args[i]);
                    ps.setObject(i + 1, args[i]);
                }
            }
            try {
                rs = ps.executeQuery();
            } catch (MySQLSyntaxErrorException e){
                InputLimit.Warn("");
            }
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<T>();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.CloseResources(null, ps, rs);
        }
        return null;
    }

    /*
    * 通用的增/删/改操作
    * */
    @Override
    public int doUpdate(String sql, Object...args) {
        int iResult = 0;
        PreparedStatement ps = null;
        try {
            ps = (PreparedStatement) conn.prepareStatement(sql);
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);
            }
            iResult = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            JDBCUtils.CloseResources(null, ps, null);
        }
        return iResult;
    }
}
