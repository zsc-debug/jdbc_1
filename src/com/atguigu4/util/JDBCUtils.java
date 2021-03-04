package com.atguigu4.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {

    /**
     * 使用的是c3p0的数据库连接池技术
     *
     * @return
     * @throws Exception
     */
    //数据库连接池只需提供一个
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");

    public static Connection getConnection1() throws Exception {
        Connection conn = cpds.getConnection();
        return conn;
    }

    /**
     * 使用DBCP数据库连接池技术
     */
    private static DataSource source;

    static {
        try {
            Properties pros = new Properties();
            FileInputStream is = new FileInputStream(new File("src/dbcp.properties"));
            pros.load(is);
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws Exception {
        Connection conn = source.getConnection();
        return conn;
    }

    /**
     * 使用Druid数据库连接池技术
     */
    private static DataSource source1;

    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            pros.load(is);
            source1 = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection3() throws Exception {
        Connection conn = source1.getConnection();
        return conn;
    }

    /**
     * @param conn
     * @param ps
     * @Description 关闭连接和Statement的操作
     * @author shkstart
     * @date 上午9:12:40
     */
    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conn
     * @param ps
     * @param rs
     * @Description 关闭资源操作
     * @author shkstart
     * @date 上午10:21:15
     */
    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param conn
     * @param ps
     * @param rs
     * @Description 使用dbutils.jar中提供的DbUtils工具类，实现资源的关闭
     * @author shkstart
     * @date 下午4:53:09
     */
    public static void closeResource1(Connection conn, Statement ps, ResultSet rs) {
       /*
        try {
            DbUtils.close(conn);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        try {
            DbUtils.close(ps);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            DbUtils.close(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */

        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }
}
