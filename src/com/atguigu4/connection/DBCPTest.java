package com.atguigu4.connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPTest {

    //方式一：
    @Test
    public void testGetConnection() throws SQLException {
        //创建了DBCP的数据库连接池
        DataSource source = new BasicDataSource();

        //设置基本信息
        ((BasicDataSource) source).setDriverClassName("com.mysql.jdbc.Driver");
        ((BasicDataSource) source).setUrl("jdbc:mysql://localhost:3306/test");
        ((BasicDataSource) source).setUsername("root");
        ((BasicDataSource) source).setPassword("123456");

        //还可以设置其他涉及数据库连接池管理的相关属性
        ((BasicDataSource) source).setInitialSize(10);
        ((BasicDataSource) source).setMaxActive(10);
        //...........

        Connection conn = source.getConnection();
        System.out.println(conn);
    }

    //方式二：使用配置文件
    @Test
    public void testGetConnection1() throws Exception {
        Properties pros = new Properties();
        //方式1：
        //InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        //方式2：
        FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
        pros.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(pros);

        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
