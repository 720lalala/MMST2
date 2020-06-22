package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.StockTransferDTO;
import cn.edu.lingnan.util.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author admin on 2020-04-08.
 * @version 1.0
 */
public class StockTransferDAO {
    /**
     * 寻找新的id号
     */
    public static String searchFutureFlowid() {
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2 = aaaaa.format(date1);
        String temp = date2.substring(0, 4) + date2.substring(5, 7) + date2.substring(8, 10);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        String big = temp;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();
            String sql = null;
            sql = "SELECT * FROM stock_transfer WHERE id LIKE'" + temp + "%';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String x = rs.getString("id");
                if (big.compareTo(x) < 0) {
                    big = x;
                }
            }
            if (big.compareTo(temp) == 0) {
                big = temp + "001";
            } else {
                int a = Integer.parseInt(big.substring(8));
                a += 1;
                String tranToStr = String.valueOf(a);
                while (tranToStr.length() != 3) {
                    tranToStr = "0" + tranToStr;
                }
//                    System.out.println("big.substring(0,12)"+big.substring(0,12));
                big = big.substring(0, 8) + tranToStr;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return big;
        }
    }

    /**
     * 添加出库信息
     */
    public static boolean insertStockTransfer(StockTransferDTO a) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM stock_transfer WHERE  id='" + a.getId() + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("没有该用户id，请重新测试！");
                sql = "INSERT INTO stock_transfer VALUES('"
                        + a.getId() + "', "
                        + a.getNumbers() + ", '"
                        + a.getOutTime() + "', "
                        + a.getInTime() + ",'"
                        + a.getOutStaffid() + "', '"
                        + a.getInStaffid() + "', '"
                        + a.getOutUserid() + "','"
                        + a.getInUserid() + "', "
                        + a.getStatus() + ");";
                //执行SQL语句
//            System.out.println(sql);
                stmt.executeUpdate(sql);
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("该已存在，请重新测试！");
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;
    }

    /**
     * 添加出库详细信息
     */
    public static boolean insertStockTransferDetail(DepotDTO a, String id, int status) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM depot WHERE  clothingid='" + a.getClothingid() + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return false;
            } else {
                if (rs.getInt("state") == 1) {
                    System.out.println("没有该衣服id 请重新输入！");
                    return false;
                }

            }

            sql = "INSERT INTO stock_transfer_details VALUES('"
                    + id + "','"
                    + a.getClothingid() + "', "
                    + a.getNumbers() + "," + status +
                    ");";
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

    /**
     * 根据单号和入库店铺编号寻找入库详细信息
     */
    public static Vector<DepotDTO> findStockTransferDetails(String userid, String id) {
        Vector<DepotDTO> a = new Vector<DepotDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = null;

            sql = "SELECT * FROM stock_transfer where id='" + id + "' AND in_userid ='" + userid + "' AND status = 0;";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) return a;
            else {

                sql = "SELECT * FROM stock_transfer_details where id='" + id + "' ;";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    DepotDTO aa = new DepotDTO();
                    aa.setClothingid(rs.getString("clothingid"));
                    aa.setNumbers(rs.getInt("numbers"));
                    a.add(aa);
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

    /**
     * 改变入库详细信息状态
     */
    public static boolean changeStockTransferDetail(DepotDTO a, String id, int status) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM stock_transfer_details WHERE  id = '" + id +
                    "' AND clothingid = '" + a.getClothingid() +
                    "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (a.getNumbers() == 0) {
                    sql = "DELETE FROM stock_transfer_details WHERE id = '" + id +
                            "' AND clothingid = '" + a.getClothingid() +
                            "'";
                } else {
                    sql = "UPDATE stock_transfer_details SET  numbers =" + a.getNumbers() +
                            ",status =" + status +
                            " WHERE id = '" + id +
                            "' AND clothingid = '" + a.getClothingid() +
                            "'";
                }
                stmt.executeUpdate(sql);
            }

            //执行SQL语句

        } catch (Exception e) {
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;
    }

    /**
     * 改变入库信息状态
     */
    public static boolean changeStockTransfer(StockTransferDTO a) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句

            String sql = "UPDATE stock_transfer SET  status =" + a.getStatus() +
                    ",in_time ='" + a.getInTime() +
                    "', in_userid ='" + a.getInUserid() +
                    "',in_staffid ='" + a.getInStaffid() +
                    "' WHERE id = '" + a.getId() +
                    "' ";
            //执行SQL语句
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;
    }

    /**
     * 根据权限不同查询出入库信息
     */
    public static Vector<StockTransferDTO> findAllStockTransfer(String userid, String in_userid, String out_userid, String id,int status) {
        Vector<StockTransferDTO> a = new Vector<StockTransferDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = null;
            if (in_userid == null && out_userid == null&& id==null && status==-1){
                sql = "SELECT * FROM stock_transfer where in_userid='" + userid + "'OR out_userid = '" + userid + "';";
            }
            else {
                sql = "SELECT * FROM stock_transfer WHERE ";
                boolean bool1 = false;
                if(in_userid!=null&&in_userid.length()!=0){
                    sql = sql +"in_userid ='"+in_userid+"' ";
                    bool1 =true;
                }
                if(out_userid!=null&&out_userid.length()!=0){
                    if(bool1){
                        sql = sql +"and out_userid ='"+out_userid+"' ";
                    }else {
                        sql = sql +"out_userid ='"+out_userid+"' ";
                        bool1 =true;
                    }
                }
                if(id!=null&&id.length()!=0){
                    if(bool1){
                        sql = sql +"and id ='"+id+"' ";
                    }else {
                        sql = sql +"id ='"+id+"' ";
                        bool1 =true;
                    }
                }
                if(status!=-1){
                    if(bool1){
                        sql = sql +"and status ='"+status+"' ";
                    }else {
                        sql = sql +"status ="+status+" ";
                    }
                }

            }
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                StockTransferDTO aa = new StockTransferDTO();
                aa.setId(rs.getString("id"));
                aa.setInTime(rs.getString("in_time"));
                aa.setInStaffid(rs.getString("in_staffid"));
                aa.setInUserid(rs.getString("in_userid"));
                aa.setNumbers(rs.getInt("numbers"));
                aa.setOutStaffid(rs.getString("out_staffid"));
                aa.setOutUserid(rs.getString("out_userid"));
                aa.setOutTime(rs.getString("out_time"));
                aa.setStatus(rs.getInt("status"));
                a.add(aa);

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

    /**
     * 根据流水号获取出入库详细信息
     */
    public static Vector<DepotDTO> findStockTransferDetail(String id) {
        Vector<DepotDTO> a = new Vector<DepotDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM stock_transfer_details where id='" + id + "';";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {

                DepotDTO aa = new DepotDTO();
                aa.setClothingid(rs.getString("clothingid"));
                aa.setNumbers(rs.getInt("numbers"));
                aa.setState(rs.getInt("status"));
                a.add(aa);

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

}
