package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.util.dataAccess;
import cn.edu.lingnan.util.maths;
import com.sun.jmx.snmp.SnmpTimeticks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ApplyuserDAO {
    public static boolean register(UserDTO usera)
    {
        if(usera.getUserid().length()!=4|| !maths.isNumeric(usera.getUserid()))
        {
           // System.out.println("用户id命名错误，应输入四个数字，请重新测试");
            return false;
        }
        if(usera.getUsername().length()==0||usera.getUsername().length()>=18)
        {
//            System.out.println("用户名命名错误，输入应少于9个的中文或英文字母，请重新测试");
            return false;
        }
        if(usera.getPassword().length()<6||usera.getPassword().length()>16)
        {
//            System.out.println("用户密码过长或过短，输入应为6到16位由字母、数字组成的密码，请重新测试");
            return false;
        }
        if(!maths.isNumericOrEnglish(usera.getPassword()))
        {
//            System.out.println("密码含有非法字符，请重新测试");
            return false;
        }
        if(!usera.getAuthority().equals("su")&&!usera.getAuthority().equals("pu"))
        {
//            System.out.println("权限要输入su或pu，请重新测试");
            return false;
        }
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "INSERT INTO applyuser VALUES('"
                    + usera.getUserid() + "', '"
                    + usera.getUsername() + "', '"
                    + usera.getPassword() + "', '"
                    + usera.getAuthority() + "', "
                    + usera.getState() + ");";
            //执行SQL语句
            stmt.executeUpdate(sql);
        }
        catch (Exception e) {

//            System.out.println("该账号已存在，请重新测试！");
            return false;

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return true;


    }

    /**
     * 寻找所有的Auser
     */
    public static Vector<UserDTO> findAllAuser()
    {
        Vector<UserDTO> a =new Vector<UserDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM Applyuser ;";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    //System.out.println("ttt");
                    UserDTO aa=new UserDTO();
                    aa.setUsername(rs.getString("username"));
                    aa.setUserid(rs.getString("userid"));
                    aa.setPassword(rs.getString("password"));
                    aa.setAuthority(rs.getString("authority"));
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
     * 拒绝一个账户的申请
     */
    public static boolean DeleteAuserMessage(String userida,String usernamea)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
          //  System.out.println("来到这里1？");
            sql="SELECT * FROM applyuser where userid='"+ userida + "' and username='"+usernamea+"';";
            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            System.out.println("来到这里2？");
            if(!rs.next())
            {
                System.out.println("没有该申请用户 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该申请用户 请重新输入！");
                }
                else {
                    System.out.println("来到这里3？");
                    sql="DELETE FROM applyuser where userid='"+ userida + "' and username='"+usernamea+"';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
                //实现级联删除用数据库的存储过程
            }

        }
        catch (Exception e) {
            System.out.println("系统出错");
            e.printStackTrace();

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return retuv;
    }
    /**
     * 接受该用户
     */
    public static boolean accessApplyUser(String userida,String usernamea)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            //  System.out.println("来到这里1？");
            sql="SELECT * FROM applyuser where userid='"+ userida + "' and username='"+usernamea+"';";
           // System.out.println(sql);
            rs=stmt.executeQuery(sql);
            System.out.println("来到这里2？");
            if(!rs.next())
            {
                System.out.println("没有该申请用户 请重新输入！");
            }
            else
            {
                String password=rs.getString("password");
                String authority=rs.getString("authority");
                UserDTO temp=new UserDTO();
                temp.setUserid(userida);
                temp.setUsername(usernamea);
                temp.setAuthority(authority);
                temp.setPassword(password);
                if(UserDAO.insertUser(temp))
                {
                    System.out.println("来到这里3？");
                    sql="DELETE FROM applyuser where userid='"+ userida + "' and username='"+usernamea+"';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }

            }

        }
        catch (Exception e) {
            System.out.println("系统出错");
            e.printStackTrace();

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return retuv;

    }
}
