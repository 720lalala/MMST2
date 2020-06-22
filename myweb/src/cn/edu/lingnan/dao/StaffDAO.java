package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.StaffDTO;
import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.util.dataAccess;
import cn.edu.lingnan.util.maths;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Vector;

public class StaffDAO {
    public static boolean InsertStaffMessage(StaffDTO staffa)
    {
        if(staffa.getStaffid().length()!=7||!maths.isNumeric(staffa.getStaffid()))
        {
            System.out.println("员工id错误，应输入七个数字，请重新测试");
            return false;
        }

        if(staffa.getStaffname().length()==0||staffa.getStaffname().length()>=18)
        {
            System.out.println("员工名命名错误，输入应少于9个的中文或英文字母，请重新测试");
            return false;
        }
        if(staffa.getUserid().length()!=4|| !maths.isNumeric(staffa.getUserid()))
        {
        System.out.println("用户id错误，应输入四个数字，请重新测试");
        return false;
        }
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM user WHERE  userid='"+staffa.getUserid()+ "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该用户id，请重新测试！");
                return false;
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该用户id 请重新输入！");
                    return false;
                }
            }
            sql = "INSERT INTO staff VALUES('"
                    + staffa.getStaffid() + "', '"
                    + staffa.getStaffname() + "', '"
                    + staffa.getUserid() + "', "
                    + staffa.getState()+ ");";
            //执行SQL语句
            stmt.executeUpdate(sql);
        }
        catch (Exception e) {
            System.out.println("该用户已存在，请重新测试！");
            return false;

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;


    }
    //查操作
    public static Vector<StaffDTO> SearchStaffMessage(String temp,String authority,String userid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        Vector<StaffDTO> aa=new Vector<StaffDTO>();
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            if(authority.equals("su"))
            {
                sql = "SELECT * FROM staff WHERE userid LIKE '%"+temp+"%' OR staffname LIKE '%"+temp+"%'OR staffid LIKE '%"+temp+"%';";
                System.out.println(sql);
            }
            else
            {
                System.out.println(sql);
                sql = "SELECT * FROM staff WHERE userid ='"+userid+"' AND (staffname LIKE '%"+temp+"%'OR staffid LIKE '%"+temp+"%');";
            }
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("进来？？");
                String staffid = rs.getString("staffid");
                String staffname = rs.getString("staffname");
                String userida = rs.getString("userid");
                int state=rs.getInt("state");
                if(state==1) continue;
                aa.add(new StaffDTO(staffid,staffname,userida,0));
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统出错,请重新测试！");
        }

        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return aa;
        }

    }
    //改操作
    public static boolean ChangeStaffMessage(String userida,String staffida,String staffnamea)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM  staff where staffid='"+ staffida + "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该员工id 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该员工id 请重新输入2！");
                }
                else {
                    sql = "update staff set staffname='"+staffnamea+"', userid = '"+userida+"', staffid='"+staffida+"' where staffid='"+ staffida + "';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }

            }


        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("反正就是出错了，请重新测试！");

        }

        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return retuv;
        }


    }

    //删操作
    public static boolean DeleteStaffMessage(String staffida)
    {
        if(staffida.length()!=7|| !maths.isNumeric(staffida))
        {
            System.out.println("员工id输入错误，应输入七个数字，请重新测试");
            return false;
        }

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT * FROM staff where staffid='"+ staffida + "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该员工id 请重新输入！");
               // return false;
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该员工id 请重新输入！");
                  //  return false;
                }
                else {
                    sql="UPDATE staff set state=1 where staffid='"+ staffida + "';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
                //实现级联删除用数据库的存储过程
            }

        }
        catch (Exception e) {
            System.out.println("系统出错");
            //return false;

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return retuv;
        }
    }

    /**
     * 寻找员工（按照权限）
     */
    public static Vector<StaffDTO> findAllStaff(String userid, String authority)
    {
        Vector<StaffDTO> a =new Vector<StaffDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            if(authority.equals("su"))
            {
                sql = "SELECT * FROM staff ;";
            }
            else {
                sql="SELECT * FROM staff where userid='"+userid+"';";
            }
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    //System.out.println("ttt");
                    StaffDTO aa=new StaffDTO();
                    aa.setStaffid(rs.getString("staffid"));
                    aa.setStaffname(rs.getString("staffname"));
                    aa.setUserid(rs.getString("userid"));
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
     * 寻找一个确认的员工id
     */
    public static boolean SearchStaffOne(String temp,String userid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql = "SELECT * FROM staff WHERE userid ='"+userid+"' AND staffid ='"+temp+"';";
            System.out.println(sql);
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            while (rs.next()) {
                if(rs.getInt("state")==0) retuv=true;

            }

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统出错,请重新测试！");
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
