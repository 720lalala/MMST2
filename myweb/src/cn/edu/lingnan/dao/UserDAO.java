package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.util.dataAccess;
import cn.edu.lingnan.util.maths;
import javafx.collections.ObservableList;

import javax.xml.validation.Validator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Vector;

public class UserDAO {
    /**
     * 注册
     * @param usera
     * @return
     */
    public static boolean register(UserDTO usera)
    {
        if(usera.getUserid().length()!=4|| !maths.isNumeric(usera.getUserid()))
        {
            System.out.println("用户id命名错误，应输入四个数字，请重新测试");
            return false;
        }
        if(usera.getUsername().length()==0||usera.getUsername().length()>=18)
        {
            System.out.println("用户名命名错误，输入应少于9个的中文或英文字母，请重新测试");
            return false;
        }
        if(usera.getPassword().length()<6||usera.getPassword().length()>16)
        {
            System.out.println("用户密码过长或过短，输入应为6到16位由字母、数字组成的密码，请重新测试");
            return false;
        }
        if(!maths.isNumericOrEnglish(usera.getPassword()))
        {
            System.out.println("密码含有非法字符，请重新测试");
            return false;
        }
        if(!usera.getAuthority().equals("su")&&!usera.getAuthority().equals("pu"))
        {
            System.out.println("权限要输入su或pu，请重新测试");
            return false;
        }
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM user WHERE userid='"
                    + usera.getUserid() + "';";
            rs=stmt.executeQuery(sql);
            System.out.println("11111");
            while(rs.next())
            {
                if(rs.getString("state")=="0")
                {
                    return false;
                }
            }
            if(!ApplyuserDAO.register(usera)) {
                System.out.println("11111222222");
                return false;
            }

        }
        catch (Exception e) {
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
    public static Vector<UserDTO> SearchUserMessage(String temp)
    {
        Vector<UserDTO> a =new Vector<UserDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM user WHERE userid LIKE'%"+temp+"%' OR username LIKE'%"+temp+"%';";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0&&rs.getString("authority").equals("pu"))
                {
                    //System.out.println("ttt");
                    UserDTO aa=new UserDTO();
                    aa.setUsername(rs.getString("username"));
                    aa.setUserid(rs.getString("userid"));
                    aa.setPassword(rs.getString("password"));
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

    public static boolean ChangeUserMessage(String userida,String passworda,String usernamea)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv = false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM user where userid='"+ userida + "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该用户id 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该用户id 请重新输入！");
                }
                else{
                    sql = "UPDATE user set password = '"+passworda+"' , username = '"+usernamea+"' WHERE userid='"+userida+"';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统出错,请重新测试！");
            retuv= false;

        }

        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);

        }
        return retuv;

    }
    //删操作
    public static boolean DeleteUserMessage(String userida)
    {
        if(userida.length()!=4|| !maths.isNumeric(userida))
        {
            System.out.println("用户id输入错误，应输入四个数字，请重新测试");
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
            sql="SELECT * FROM user where userid='"+ userida + "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该用户id 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该用户id 请重新输入！");
                }
                else {
                    sql = "SELECT * FROM depot d,staff s WHERE (d.userid ='"+userida+"' or s.userid = '"+userida+"') and s.state = 0 and d.state = 0 ";
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                        return retuv;
                    sql="UPDATE user set state=1 where userid='"+ userida + "';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
                //实现级联删除用数据库的存储过程
            }

        }
        catch (Exception e) {
            System.out.println("系统出错");

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
        }
        return retuv;
    }
    public static boolean checklogin(String id,String password,String authority)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM user where userid='"+ id + "' and password='"+password+"' and authority='"+authority+"';";
//            System.out.println(sql);
            //执行SQL语句
            rs=stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(sql);
                int state=rs.getInt("state");
                if(state==1) continue;
                return true;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统出错,请重新测试！");
            return false;

        }

        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);

        }
        return false;
    }
    /**
     *寻找所有的用户信息
     */
    public static Vector<UserDTO> findAllUser()
    {
        Vector<UserDTO> a =new Vector<UserDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM user ;";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0&&rs.getString("authority").equals("pu"))
                {
                    //System.out.println("ttt");
                    UserDTO aa=new UserDTO();
                    aa.setUsername(rs.getString("username"));
                    aa.setUserid(rs.getString("userid"));
                    aa.setPassword(rs.getString("password"));
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
     * 添加用户
     */
    public static  boolean insertUser(UserDTO usera)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String Sql="Insert into user VALUES ('"
                    + usera.getUserid() + "', '"
                    + usera.getUsername() + "', '"
                    + usera.getPassword() + "', '"
                    + usera.getAuthority() + "', "
                    + usera.getState() + ");";
            stmt.executeUpdate(Sql);
            retuv=true;
        }catch (Exception e)
        {
            System.out.println("该账号已存在，请重新测试！");

        }
        finally {
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return  retuv;
        }
    }
    /**
     * 寻找是否有该用户
     */
    public static boolean searchOneUser(String userid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try{
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql="select * from USER where userid='"+userid+"';";
            rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                if(rs.getString("state")!="0")
                    retuv=true;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return  retuv;

        }
    }
    /**
     * 读出自己的信息
     */
    public static UserDTO raedUserMessage(String userid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        UserDTO retuv=new UserDTO();
        try{
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql="select * from USER where userid='"+userid+"';";
            rs=stmt.executeQuery(sql);
            if(rs.next())
            {
                if(rs.getString("state")!="0")
                {
                    retuv.setUsername(rs.getString("username"));
                    retuv.setUserid(rs.getString("userid"));
                    retuv.setPassword(rs.getString("password"));
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
            return  retuv;

        }
    }

}
