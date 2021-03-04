package com.atguigu.connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest {
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "123456");
        Connection conn = driver.connect(url, info);
        System.out.println(conn);
    }

    @Test
    public void testConnection2() throws Exception {
        //1.提供三个基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "123456";
        //2.加载Driver
        Class.forName("com.mysql.jdbc.Driver");
        //3.获取连接
        Connection conn = (Connection) DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    @Test
    //将数据库连接需要的四个基本信息声明在配置文件中去，通过读取配置文件的方式来获取连接
    public void testConnection3() throws Exception {
        //1.读取配置文件的四个基本信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //2.加载驱动
        Class.forName(driverClass);

        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
}
