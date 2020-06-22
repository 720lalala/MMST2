package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.VipDTO;
import cn.edu.lingnan.util.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 * @author admin on 2020-02-26.
 * @version 1.0
 */
public class VipDAO {
    //管理员查询特定vip用户
    public static Vector<VipDTO> AdminSearchVipMessage(String temp) {
        Vector<VipDTO> a = new Vector<VipDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM vip WHERE phone LIKE'%" + temp + "%' OR name LIKE'%" + temp + "%';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                if (rs.getInt("state") == 0) {
                    //System.out.println("ttt");
                    VipDTO aa = new VipDTO();
                    aa.setPhone(rs.getString("phone"));
                    aa.setName(rs.getString("name"));
                    aa.setScore(rs.getInt("score"));
                    aa.setDate(rs.getDate("date").toString().substring(0, 4));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }


    }

    //普通用户查询特定vip用户
    public static Vector<VipDTO> PuSearchVipMessage(String temp) {
        Vector<VipDTO> a = new Vector<VipDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM vip WHERE phone = '" + temp + "' OR name ='" + temp + "';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                if (rs.getInt("state") == 0) {
                    //System.out.println("ttt");
                    VipDTO aa = new VipDTO();
                    aa.setPhone(rs.getString("phone"));
                    aa.setName(rs.getString("name"));
                    aa.setScore(rs.getInt("score"));
                    aa.setDate(rs.getDate("date").toString().substring(0, 4));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }


    }

    //寻找所有vip用户
    public static Vector<VipDTO> findAllVip(String authority) {
        Vector<VipDTO> a = new Vector<VipDTO>();
        if (authority.equals("pu")) return a;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM vip ;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                if (rs.getInt("state") == 0) {
                    //System.out.println("ttt");
                    VipDTO aa = new VipDTO();
                    aa.setPhone(rs.getString("phone"));
                    aa.setName(rs.getString("name"));
                    aa.setScore(rs.getInt("score"));
                    aa.setDate(rs.getDate("date").toString().substring(0, 4));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }
    }

    //修改特定vip用户
    public static boolean ChangeVipMessage(String phone, String name) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean retuv = false;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = null;
            sql = "SELECT * FROM vip where name='" + name + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("没有该vip 请重新输入！");
            } else {
                int state = rs.getInt("state");
                if (state == 1) {
                    System.out.println("没有该vip 请重新输入！");
                } else {
                    String phone2 = rs.getString("phone");
                    sql = "UPDATE vip set phone = '" + phone + "' WHERE name='" + name + "' AND phone = '" + phone2 + "';";
                    stmt.executeUpdate(sql);
                    retuv = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统出错,请重新测试！");
            retuv = false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);

        }
        return retuv;
    }

    //删除某vip用户
    public static boolean DeleteVipMessage(String name) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean retuv = false;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();
            String sql = null;
            sql = "SELECT * FROM vip where name='" + name + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("没有该vip 请重新输入！");
            } else {
                int state = rs.getInt("state");
                if (state == 1) {
                    System.out.println("没有该vip 请重新输入！");
                } else {
                    sql = "UPDATE vip set state=1 where name='" + name + "';";
                    stmt.executeUpdate(sql);
                    retuv = true;
                }
                //实现级联删除用数据库的存储过程
            }

        } catch (Exception e) {
            System.out.println("系统出错");

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return retuv;
    }

    //添加某vip用户
    public static boolean insertVip(VipDTO vip) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean retuv = false;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();
            String Sql = "Insert into vip VALUES ('"
                    + vip.getPhone() + "', '"
                    + vip.getName() + "', "
                    + vip.getScore() + ", "
                    + vip.getDate() + ", "
                    + vip.getState() + ");";
            stmt.executeUpdate(Sql);
            retuv = true;
        } catch (Exception e) {
            System.out.println("该账号已存在，请重新测试！");

        } finally {
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return retuv;
        }

    }

    //根据电话查询该vip
    public static VipDTO searchVipByPhone(String temp) {
        VipDTO a = new VipDTO();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM vip WHERE phone ='" + temp + "' ;";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {

                if (rs.getInt("state") == 0) {

                    a.setPhone(rs.getString("phone"));
                    a.setName(rs.getString("name"));
                    a.setScore(rs.getInt("score"));
                    a.setDate(rs.getDate("date").toString().substring(0, 4));

                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }

    }

    //结算时增加或减少积分。
    public static void settlementVipScore(int score, String phone) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = null;
            sql = "UPDATE vip set score = '" + score + "' WHERE phone='" + phone + "';";
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统出错,请重新测试！");

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);

        }
    }
}
