package cn.edu.lingnan.dao;

import cn.edu.lingnan.util.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author admin on 2020-04-07.
 * @version 1.0
 */
public class ReturnDAO {
    /**
     * 添加退换货信息
     */
    public static boolean insertReturnPolicy(String flowid_returns,String flowid_sales){
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM return_policy WHERE  flowid_returns='"+flowid_returns+ "'AND flowid_sales='"+flowid_sales+"';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                sql = "INSERT INTO return_policy VALUES('"
                        + flowid_returns + "', '"
                        + flowid_sales + "', 0);";
                //执行SQL语句
                stmt.executeUpdate(sql);
                retuv=true;

            }


        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("该衣服记录已存在，请重新测试！");

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return retuv;
        }

    }
}
