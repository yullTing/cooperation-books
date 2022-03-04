package cn.domain;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
//操作数据库的工具类
public class JDBCUtils {
    //获取数据库的连接
    public static  Connection getConnection() throws Exception {
        // 1.读取配置文件中的4个基本信息
        /*InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");*/

        //1.数据库连接的4个基本要素：
        String url = "jdbc:mysql://localhost:3306/librarymanagement";//test是数据库名
        String user = "root";//这里是登录MySQL数据库的用户名
        String password = "root";//这里是登录登录MySQL数据库的密码
        String driverClass = "com.mysql.jdbc.Driver";//这个是驱动名字，不是前面导入的包名。

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }
    //关闭连接和Statement的操作
    public static void closeResource(Connection conn,Statement ps){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //关闭资源操作
    public static void closeResource(Connection conn,Statement ps,ResultSet rs){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

