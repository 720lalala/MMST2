package cn.edu.lingnan.util;

import java.sql.*;
import java.util.Properties;
public class dataAccess {
    private static String driver;
    private  static String url;
    private  static String user;
    private  static String pwd;
    static {
        DatabaseConfigParser databaseConfig=new DatabaseConfigParser();
        try{
            databaseConfig.parse("database.conf.xml");
            Properties dbProps=databaseConfig.getProps();
            driver=dbProps.getProperty("driver");
            url=dbProps.getProperty("url");
            user=dbProps.getProperty("user");
            pwd=dbProps.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        Connection Conn=null;
        try {
            //注册JDBC程序
            Class.forName(driver);
            //建立数据库连接
            Conn = DriverManager.getConnection
                    (url,user,pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Conn;
    }
    //关闭数据库链接
    public static void closeConnection(Connection Conn)
    {
        if(Conn!=null)
        {
            try {
                Conn.close();
                Conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭发送SQL语句
    public static void closeConnection(Statement stmt)
    {
        if(stmt!=null)
        {
            try {
                stmt.close();
                stmt=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //关闭结果集
    public static void closeConnection(ResultSet rs)
    {
        if(rs!=null)
        {
            try {
                rs.close();
                rs=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
