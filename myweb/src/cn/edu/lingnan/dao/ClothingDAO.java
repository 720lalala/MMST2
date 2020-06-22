package cn.edu.lingnan.dao;
import cn.edu.lingnan.dto.ClothingDTO;
import cn.edu.lingnan.util.dataAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class ClothingDAO {
    /**
     * 寻找所有的clothing
     */
    public static Vector<ClothingDTO> findAllClothing()
    {
        Vector<ClothingDTO> a =new Vector<ClothingDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM clothes ;";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    //System.out.println("ttt");
                    ClothingDTO aa=new ClothingDTO();
                    aa.setClothingid(rs.getString("clothingid"));
                    aa.setColor(rs.getString("color"));
                    aa.setDiscount(rs.getFloat("discount"));
                    aa.setPrice(rs.getFloat("price"));
                    aa.setSize(rs.getString("size"));
                    aa.setProperty(rs.getString("property"));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        }
        catch (Exception e) {
//            System.out.println(e.getMessage());

        }
        finally {
            //关闭各种连接
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return a;
        }
    }
    public static  boolean insertClothing(String clothingid, String property, String color,String size,String price, String discount)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String Sql="Insert into clothes VALUES ('"
                    + clothingid + "', '"
                    + property + "', '"
                    + color + "', '"
                    + size + "', "
                    + price + ", "
                    + discount + ", 0);";
            stmt.executeUpdate(Sql);
            retuv=true;
        }catch (Exception e)
        {
            e.printStackTrace();
//            System.out.println("该衣服记录已存在，请重新测试！");

        }
        finally {
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return  retuv;
        }
    }
    //删操作
    public static boolean DeleteClothingMessage(String clothingida)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT * FROM clothes where clothingid='"+ clothingida + "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该衣服id 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该衣服id 请重新输入！");
                }
                else {
                    sql = "SELECT * FROM depot WHERE clothingid ='"+ clothingida + "';";
                    rs = stmt.executeQuery(sql);
                    if(rs.next())
                        return retuv;
                    sql="UPDATE clothes set state=1 where clothingid='"+ clothingida + "';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
                //实现级联删除用数据库的存储过程
            }

        }
        catch (Exception e) {
//            System.out.println("系统出错");
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
    //查操作
    public static Vector<ClothingDTO> SearchClothingMessage(String temp)
    {
        Vector<ClothingDTO> a =new Vector<ClothingDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT * FROM clothes WHERE clothingid LIKE'%"+temp+
                    "%' OR price ='"+temp+
                    "' OR property LIKE'%"+temp+
                    "%' or size like'%"+temp+
                    "%' or color like'%"+temp+
                    "%';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("state")==0)
                {
                    //System.out.println("ttt");
                    ClothingDTO aa=new ClothingDTO();
                    aa.setClothingid(rs.getString("clothingid"));
                    aa.setColor(rs.getString("color"));
                    aa.setDiscount(rs.getFloat("discount"));
                    aa.setPrice(rs.getFloat("price"));
                    aa.setSize(rs.getString("size"));
                    aa.setProperty(rs.getString("property"));
                    a.add(aa);
                    // System.out.println("tt");

                }
            }


        }
        catch (Exception e) {
//            System.out.println(e.getMessage());
            e.printStackTrace();

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
     * 寻找是否有该衣服存在
     */
    public static boolean searchOneClothing(String clothingid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try{
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql="select * from clothes where clothingid='"+clothingid+"';";
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
     * 修改一件衣服id的相关东西
     */
    public static boolean ChangeClothingMessage(String clothingid,String propertyFF,String priceFF,String discountFF)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT * FROM clothes where clothingid='"+ clothingid + "';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该衣服id 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该衣服id 请重新输入！");
                }
                else {
                    sql="UPDATE clothes set property='"+propertyFF+"' , price="+priceFF+" , discount="+discountFF+" where clothingid='"+ clothingid + "';";
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
                //实现级联删除用数据库的存储过程
            }

        }
        catch (Exception e) {
//            System.out.println("系统出错");
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
