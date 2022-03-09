package com.utils;

import java.sql.*;

/*
* 工具类：获取连接、关闭资源①②
* */
public class JDBCUtils {
    /*
    * 获取连接
    * 返回连接的结果
    * */
    public static Connection getConnResult() throws Exception{
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/librarymanagement";//test是数据库名
        String user = "root";//这里是登录MySQL数据库的用户名
        String password = "root";//这里是登录登录MySQL数据库的密码
        String driverName = "com.mysql.jdbc.Driver";//这个是驱动名字，不是前面导入的包名。
        // MySQL的驱动【com.mysql.jdbc.Drive】，Oracle的驱动【oracle.jdbc.driver.OracleDriver】

        //2.加载驱动
        Class.forName(driverName);

        //3.获取连接
        conn = (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, password);

        //4.返回值
        return conn;
    }

    /*
    * 关闭资源
    * 关闭Connection、PreparedStatement和ResultSet对象
    * */
    public static void CloseResources(Connection conn, PreparedStatement ps, ResultSet rs) {
        try {
            if (rs!=null){ rs.close(); }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) { ps.close(); }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) { conn.close(); }
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }


    /*
    * 执行操作：增/删/改
    * 添加Add
    * 删除Delete
    * 修改Update
    * */
    /*public static int ExecuteData(String sql, Object... param){
        int iResult = 0;
        com.mysql.jdbc.Connection conn = null;
        PreparedStatement ps = null;
        try {
            //获取Connection对象conn
            conn = (com.mysql.jdbc.Connection) getConnResult();
            //获取PreparedStatement对象ps
            ps = conn.prepareStatement(sql);
            //填充占位符
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    ps.setObject(i + 1, param[i]);
                }
            }
            //执行操作：增/删/改
            iResult = ps.executeUpdate();
            //执行结果是一个更新计数i。这里只添加了一行，所以返回的计数i是1
            *//*if (i>0){
                System.out.println("添加操作执行成功！");
            } else {
                System.out.println("添加操作执行失败！");
            }*//*
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源。资源是要即是关闭的，而且最好还是将数据放在数组中去访问，防止程序卡顿等问题
            CloseResources(conn, ps, null);
        }
        return iResult;
    }*/

    /*
     * 查询Query（单条查询）
     * */
    /*public static <T> T QuerySingle(Class<T> tCalss, String sql, Object... param) {
        T t = null;//新建实体类对象
        com.mysql.jdbc.Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取Connection对象conn
            conn = (com.mysql.jdbc.Connection) getConnResult();
            //获取PreparedStatement对象ps
            ps = conn.prepareStatement(sql);
            //填充占位符
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    ps.setObject(i + 1, param[i]);
                }
            }
            //执行查询语句，获取结果集rs。如果是增删改，则用execute()方法
            rs = ps.executeQuery();
            //获取结果集rs中列的类型和属性信息
            ResultSetMetaData metaData = rs.getMetaData();
            //获取列的个数columnCount
            int columnCount = metaData.getColumnCount();
            //System.out.println("表【xxx】共有" + columnCount + "列");
            //结果集rs指针不为false，即指针指向的一行存在数据
            while (rs.next()) {
                t = tCalss.newInstance();//新建实体类对象
                for (int i = 0; i < columnCount; i++) {
                    //获取列名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //通过列名获取包括private权限的成员变量
                    Field field = tCalss.getDeclaredField(columnLabel);
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //取消Java语言访问检查
                    field.setAccessible(true);
                    //给成员变量赋值
                    field.set(t, columnValue);
                }
                //在控制台打印第一列和第二列的数据（注意索引从1开始）
                //System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源。资源是要即是关闭的，而且最好还是将数据放在数组中去访问，防止程序卡顿等问题
            CloseResources(conn, ps, rs);
        }

        return t;
    }*/

    /*
     * 查询Query（多条查询）
     * */
    /*public static <T> ArrayList<T> QueryMultiple(Class<T> tCalss, String sql, Object... param) {//
        ArrayList<T> arrayList = new ArrayList<T>();
        com.mysql.jdbc.Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取Connection对象conn
            conn = (com.mysql.jdbc.Connection) getConnResult();
            //获取PreparedStatement对象ps
            ps = conn.prepareStatement(sql);
            //填充占位符
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    ps.setObject(i + 1, param[i]);
                }
            }
            //执行查询语句，获取结果集rs。如果是增删改，则用execute()方法
            rs = ps.executeQuery();
            //获取结果集rs中列的类型和属性信息
            ResultSetMetaData metaData = rs.getMetaData();
            //获取列的个数columnCount
            int columnCount = metaData.getColumnCount();
            //System.out.println("表【candidate】共有" + columnCount + "列");
            //结果集rs指针不为false，即指针指向的一行存在数据
            while (rs.next()) {
                T t = tCalss.newInstance();//新建实体类对象
                for (int i = 0; i < columnCount; i++) {
                    //获取列名
                    String columnLabel = metaData.getColumnLabel(i + 1);
                    //通过列名获取包括private权限的成员变量
                    Field field = tCalss.getDeclaredField(columnLabel);
                    //获取列值
                    Object columnValue = rs.getObject(i + 1);
                    //取消Java语言访问检查
                    field.setAccessible(true);
                    //给成员变量赋值
                    field.set(t, columnValue);
                }
                arrayList.add(t);
                //在控制台打印第一列和第二列的数据（注意索引从1开始）
                //System.out.println(rs.getInt(1) + "\t" + rs.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源。资源是要即是关闭的，而且最好还是将数据放在数组中去访问，防止程序卡顿等问题
            CloseResources(conn, ps, rs);
        }
        return arrayList;
    }*/

}
