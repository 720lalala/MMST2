package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.SalesDTO;
import cn.edu.lingnan.dto.StaffDTO;
import cn.edu.lingnan.util.dataAccess;
import cn.edu.lingnan.util.maths;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Vector;

public class SalesDAO {
    public static boolean InsertSalesMessage(SalesDTO salesa) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //0101002  B00000000000011 1 99 B00000000000121 0000
        System.out.println(salesa.getFlowid());
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM flowsheet WHERE  flowid='" + salesa.getFlowid() + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("没有该流水单号，请重新测试！");
                return false;
            } else {
                int state = rs.getInt("state");
                if (state == 1) {
                    System.out.println("没有该流水单号 请重新输入！");
                    return false;
                } else {
                    sql = "SELECT  * FROM depot WHERE  clothingid='" + salesa.getClothingid() + "';";
                    rs = stmt.executeQuery(sql);
                    if (!rs.next()) {
                        System.out.println("没有该衣服id，请重新测试！");
                        return false;
                    } else {
                        state = rs.getInt("state");
                        if (state == 1) {
                            System.out.println("没有该衣服id 请重新输入！");
                            return false;
                        }
                    }
                }
            }
            sql = "INSERT INTO sales VALUES('"
                    + salesa.getStaffid() + "','"
                    + salesa.getClothingid() + "', "
                    + salesa.getNumbers() + ", "
                    + salesa.getDisprice() + ", '"
                    + salesa.getFlowid() + "', '"
                    + salesa.getUserid() + "', "
                    + salesa.getState() + ");";
            //执行SQL语句
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("该衣服记录已存在，请重新测试！");
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;


    }
    public static boolean InsertReturnSalesMessage(SalesDTO salesa) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        //0101002  B00000000000011 1 99 B00000000000121 0000
        System.out.println(salesa.getFlowid());
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM flowsheet WHERE  flowid='" + salesa.getFlowid() + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                System.out.println("没有该流水单号，请重新测试！");
                return false;
            } else {
                int state = rs.getInt("state");
                if (state == 1) {
                    System.out.println("没有该流水单号 请重新输入！");
                    return false;
                } else {
                    sql = "INSERT INTO sales VALUES('"
                            + salesa.getStaffid() + "','"
                            + salesa.getClothingid() + "', "
                            + salesa.getNumbers() + ", "
                            + salesa.getDisprice() + ", '"
                            + salesa.getFlowid() + "', '"
                            + salesa.getUserid() + "', "
                            + salesa.getState() + ");";
                    //执行SQL语句
                    stmt.executeUpdate(sql);
                }
            }

        } catch (Exception e) {
            System.out.println("该衣服记录已存在，请重新测试！");
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;


    }
    //查操作
    public static Vector<SalesDTO> AllSalesMessage(String authority,String userid) {
        Vector<SalesDTO> aa=new Vector<SalesDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql;
            if(authority.equals("pu"))
            {
                sql = "SELECT * FROM  sales where userid='" + userid +"';";

            }
            else {
                sql = "SELECT * FROM  sales ;";

            }
            //执行SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                    SalesDTO a=new SalesDTO();
                    a.setStaffid(rs.getString("staffid"));
                    a.setClothingid(rs.getString("clothingid"));
                    a.setNumbers(rs.getInt("numbers"));
                    a.setDisprice( rs.getFloat("disprice"));
                    a.setFlowid(rs.getString("flowid")) ;
                    a.setUserid(rs.getString("userid"));
                    a.setState(rs.getInt("state"));
                    aa.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("系统出错,请重新测试！");

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return aa;
        }

    }

    /**
     * 找到符合条件的sales
     */

    public static Vector<SalesDTO> SearchSomeSalesMessage(String date,String staffid,String userid,String authority) {
        Vector<SalesDTO> aa=new Vector<SalesDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql;
            if(authority.equals("pu"))
            {
                if(staffid.length()!=0)
                {
                    sql = "SELECT s.* FROM  sales s,flowsheet f where s.userid='" + userid
                            +"' and s.staffid='"+staffid
                            +"' and f.time like'"+date +"%' and f.flowid=s.flowid;";

                }
                else
                {
                    sql = "SELECT s.* FROM  sales s,flowsheet f where s.userid='" + userid
                            +"' and f.time like'"+date +"%' and f.flowid=s.flowid;";
                }

            }
            else {
                if(staffid.length()!=0)
                {
                    sql = "SELECT s.* FROM  sales s,flowsheet f where  s.staffid='"+staffid
                            +"' and f.time like '"+date +"%' and f.flowid=s.flowid;";
                }
                else {
                    sql = "SELECT s.* FROM  sales s,flowsheet f where f.time like '"+date +"%' and f.flowid=s.flowid;";
                }

            }
            //System.out.println(sql);
            //执行SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("state") == 0) {
                    SalesDTO a=new SalesDTO();
                    a.setStaffid(rs.getString("staffid"));
                    a.setClothingid(rs.getString("clothingid"));
                    a.setNumbers(rs.getInt("numbers"));
                    a.setDisprice( rs.getFloat("disprice"));
                    a.setFlowid(rs.getString("flowid")) ;
                    a.setUserid(rs.getString("userid"));
                    aa.add(a);

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("系统出错,请重新测试！");

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return aa;
        }

    }

    //根据衣服id和时间和店铺情况
    public static Vector<SalesDTO> SearchSalesForClothingSales(String date,String userid,String clothingid,String areaid) {
        Vector<SalesDTO> aa=new Vector<SalesDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql="SELECT s.*,f.time FROM sales s,flowsheet f where f.flowid = s.flowid  ";

            if(date != null && date.length()!=0)
            {
                sql+=" and time like '%"+date+"%' ";

            }
            if(userid != null &&userid.length()!=0)
            {
                sql+="and s.userid='"+userid+"' ";
            }
            if(areaid != null &&areaid.length()!=0)
            {
                sql+="and s.userid like '"+areaid+"%' ";
            }
            if(clothingid != null &&clothingid.length()!=0)
            {
               sql+="and clothingid='"+clothingid+"' ";

            }
            //执行SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if (rs.getInt("state") == 0) {
                    SalesDTO a=new SalesDTO();
                    a.setClothingid(rs.getString("clothingid"));
                    a.setNumbers(rs.getInt("numbers"));
                    a.setFlowid(rs.getString("flowid")) ;
                    a.setUserid(rs.getString("userid"));
                    a.setDisprice(rs.getFloat("disprice"));
                    a.setStaffid(rs.getString("staffid"));
                    aa.add(a);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("系统出错,请重新测试！");

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return aa;
        }

    }


    //根据流水单id销售详情
    public static Vector<SalesDTO> SearchSomeSalesByFlowid(String flowid){
        Vector<SalesDTO> aa=new Vector<SalesDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql;
            sql = "SELECT * FROM  sales  where flowid = '"+flowid +"';";

            //System.out.println(sql);
            //执行SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SalesDTO a=new SalesDTO();
                a.setStaffid(rs.getString("staffid"));
                a.setClothingid(rs.getString("clothingid"));
                a.setNumbers(rs.getInt("numbers"));
                a.setDisprice( rs.getFloat("disprice"));
                a.setFlowid(rs.getString("flowid")) ;
                a.setUserid(rs.getString("userid"));
                a.setState(rs.getInt("state"));
                aa.add(a);
            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("系统出错,请重新测试！");

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return aa;
        }

    }

    /**
     * 退还货改变销售状态
     */
    public static boolean ChangeStateForReturn(String clothingid,String flowid,int numbers){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM sales WHERE  flowid='" + flowid + "' AND clothingid='"+clothingid+"';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return false;
            } else {
                int state = rs.getInt("state");
                sql = "UPDATE sales SET state = "+(state+numbers)+" WHERE flowid='" + flowid + "' AND clothingid='"+clothingid+"';";
                //执行SQL语句
                stmt.executeUpdate(sql);
                }

        } catch (Exception e) {
            System.out.println("error");
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;


    }
}
