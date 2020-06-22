package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.FlowSheetDTO;
import cn.edu.lingnan.util.dataAccess;
import cn.edu.lingnan.util.maths;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Vector;
import java.util.Date;

public class FlowSheetDAO {
    public static boolean InsertFlowSheetMessage(FlowSheetDTO flowSheeta) {
        String aa = flowSheeta.getFlowid().substring(1);
        char c = flowSheeta.getFlowid().charAt(0);
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM user WHERE  userid='" + flowSheeta.getUserid() + "';";
            rs = stmt.executeQuery(sql);
            if (!rs.next()) {
//                System.out.println("没有该用户id，请重新测试！");
                return false;
            } else {
                int state = rs.getInt("state");
                if (state == 1) {
//                    System.out.println("没有该用户id 请重新输入！");
                    return false;
                }
            }
            sql = "INSERT INTO flowSheet VALUES('"
                    + flowSheeta.getFlowid() + "', "
                    + flowSheeta.getPricea() + ", '"
                    + flowSheeta.getPayway() + "', '"
                    + flowSheeta.getTime() + "','"
                    + flowSheeta.getUserid() + "', '"
                    + flowSheeta.getVipphone() + "',"
                    + flowSheeta.getUsescore() + ", "
                    + flowSheeta.getState() + ");";
            //执行SQL语句
//            System.out.println(sql);
            stmt.executeUpdate(sql);
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

    //查操作
    public static Vector<FlowSheetDTO> SearchFlowsheetMessage(String date,String flowida,String paywaya,String userid,String areaid) {
        Vector<FlowSheetDTO> a=new Vector<FlowSheetDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql="SELECT * FROM flowsheet where ";
            int bb=sql.length();
            if(date != null &&date.length()!=2)
            {
                sql+=" time like '%"+date+"%' ";

            }
            if(userid != null && userid.length()!=0)
            {
                if(bb!=sql.length())
                {
                    sql+="and userid='"+userid+"' ";
                }
                else {
                    sql+="userid='"+userid+"' ";
                }
            }
            if(areaid != null && areaid.length()!=0)
            {
                if(bb!=sql.length())
                {
                    sql+="and userid like '"+areaid+"%' ";
                }
                else {
                    sql+="userid like '"+areaid+"%' ";
                }
            }
            if(paywaya != null &&paywaya.length()!=0)
            {
                if(bb!=sql.length())
                {
                    sql+="and payway='"+paywaya+"' ";
                }
                else {
                    sql+="payway='"+paywaya+"' ";
                }
            }
            if(flowida != null &&flowida.length()!=0)
            {
                if(bb!=sql.length())
                {
                    sql+="and flowid='"+flowida+"' ";
                }
                else {
                    sql+="flowid='"+flowida+"' ";
                }
            }
            //System.out.println(sql);
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    //System.out.println("ttt");
                    FlowSheetDTO aa=new FlowSheetDTO();
                    aa.setFlowid(rs.getString("flowid"));
                    aa.setPayway(rs.getString("payway"));
                    aa.setPricea(rs.getFloat("pricea"));
                    aa.setTime(rs.getString("time"));
                    aa.setUserid(rs.getString("userid"));
                    aa.setVipphone(rs.getString("vipphone"));
                    aa.setUsescore(rs.getInt("usescore"));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }
    }

    //改操作
    public static boolean ChangeFlowSheetMessage(String flowida,String old, String temp) {
        String aa = flowida.substring(1);
        char c = flowida.charAt(0);
        if (flowida.length() != 15 || !('A' <= c && c <= 'Z') || !maths.isNumeric(aa)) {
//            System.out.println("流水单id错误，应在开头输入大写英文字母随后输入14个数字，请重新测试");
            return false;
        }
        if (old.length() != 1 ||old.charAt(0) < 'A' || old.charAt(0) < 'A') {
//            System.out.println("旧的支付方式输入错误 请输入一个大写字母！");

        }
        if (temp.length() != 1 ||temp.charAt(0) < 'A' || temp.charAt(0) < 'A') {
//            System.out.println("新的支付方式输入错误 请输入一个大写字母！");

        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM flowsheet where flowid='" + flowida + "' and payway='"+old+"';";

            //执行SQL语句
            rs=stmt.executeQuery(sql);
            if (rs.next()) {
                if(rs.getInt("state")==1)
                {
//                    System.out.println("没有该条流水单信息");
                    return false;
                }

            }
            sql = "update flowsheet set payway='" + temp + "' where flowid='" + flowida + "' and payway='"+old+"';";

            //执行SQL语句
            stmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
//            System.out.println("没有该条流水单记录，请重新测试！");
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);

        }
        return true;

    }

    //删操作
    public static boolean DeleteFlowSheetMessage(String flowida,String payway) {
        String aa = flowida.substring(1);
        char c = flowida.charAt(0);
        if (flowida.length() != 15 || !('A' <= c && c <= 'Z') || !maths.isNumeric(aa)) {
//            System.out.println("流水单id错误，应在开头输入大写英文字母随后输入14个数字，请重新测试");
            return false;
        }
        if (payway.length() != 1 ||payway.charAt(0) < 'A' || payway.charAt(0) < 'A') {
//            System.out.println("支付方式输入错误 请输入一个大写字母！");

        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM flowsheet where flowid='" + flowida + "' and payway='"+payway+"';";

            //执行SQL语句
            rs=stmt.executeQuery(sql);
            if (rs.next()) {
                if(rs.getInt("state")==1)
                {
//                    System.out.println("没有该条流水单信息");
                    return false;
                }

            }
            sql = "update flowsheet set state=1 where flowid='" + flowida + "' and payway='"+payway+"';";

            //执行SQL语句
            stmt.executeUpdate(sql);
                //实现级联删除用数据库的存储过程


        } catch (Exception e) {
            System.out.println("系统出错");
            return false;

        } finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;
    }
    /**
     * 寻找所有的flowid
     */
    public static Vector<FlowSheetDTO> findAllFlowsheet()
    {
        Vector<FlowSheetDTO> a =new Vector<FlowSheetDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM flowsheet ;";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    //System.out.println("ttt");
                    FlowSheetDTO aa=new FlowSheetDTO();
                    aa.setFlowid(rs.getString("flowid"));
                    aa.setPayway(rs.getString("payway"));
                    aa.setPricea(rs.getFloat("pricea"));
                    aa.setTime(rs.getString("time"));
                    aa.setUserid(rs.getString("userid"));
                    aa.setUsescore(rs.getInt("usescore"));
                    aa.setVipphone(rs.getString("vipphone"));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }
    }

    /**
     * 寻找到将来的流水号
     */
    public static String searchFutureFlowid(String userid)
    {
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2 = aaaaa.format(date1);
        String temp="L"+date2.substring(2,4)+date2.substring(5,7)+date2.substring(8,10)+userid;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        String big=temp;
        try {
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT * FROM user WHERE userid='"+userid+"';";
            rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                sql="select * FROM flowsheet WHERE flowid LIKE '%"+temp+"%';";
//                System.out.println(sql);
                rs=stmt.executeQuery(sql);
                while(rs.next())
                {
                    String x=rs.getString("flowid");
                    if(big.compareTo(x)<0)
                    {
                        big=x;
                    }
                }
                if(big.compareTo(temp)==0)
                {
                    big=temp+"0001";
                }
                else{
                    int a=Integer.parseInt(big.substring(11));
                    a+=1;
                    String tranToStr = String.valueOf(a);
                    while(tranToStr.length()!=4)
                    {
                        tranToStr="0"+tranToStr;
                    }
//                    System.out.println("big.substring(0,12)"+big.substring(0,12));
                    big=big.substring(0,11)+tranToStr;
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return big;
        }
    }
    /**
     * 寻找到将来的流水号给退换货
     */
    public static String searchFutureFlowidForReturn(String userid){
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String date2 = aaaaa.format(date1);
        String temp="R"+date2.substring(2,4)+date2.substring(5,7)+date2.substring(8,10)+userid;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        String big=temp;
        try {
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT * FROM user WHERE userid='"+userid+"';";
            rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                sql="select * FROM flowsheet WHERE flowid LIKE '"+temp+"%';";
//                System.out.println(sql);
                rs=stmt.executeQuery(sql);
                while(rs.next())
                {
                    String x=rs.getString("flowid");
                    if(big.compareTo(x)<0)
                    {
                        big=x;
                    }
                }
                if(big.compareTo(temp)==0)
                {
                    big=temp+"0001";
                }
                else{
                    int a=Integer.parseInt(big.substring(11));
                    a+=1;
                    String tranToStr = String.valueOf(a);
                    while(tranToStr.length()!=4)
                    {
                        tranToStr="0"+tranToStr;
                    }
//                    System.out.println("big.substring(0,12)"+big.substring(0,12));
                    big=big.substring(0,11)+tranToStr;
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return big;
        }
    }
    /**
     * 寻找特定流水单号
     */
    public static boolean SearchFlowsheet(String flowid) {
        Vector<FlowSheetDTO> a=new Vector<FlowSheetDTO>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        boolean retult = false;
        try {
            conn = dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql="SELECT * FROM flowsheet where flowid = '"+flowid+"';";
            //System.out.println(sql);
            //执行SQL语句
            rs=stmt.executeQuery(sql);

            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    retult =  true;
                }

            }


        }
        catch (Exception e) {
            System.out.println(e.getMessage());

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return retult;
        }
    }


}
