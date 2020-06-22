package cn.edu.lingnan.dao;

import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.DepotDetailsDTO;
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

public class DepotDAO {

    public static boolean InsertDepotMessage(String clothingid,String userid,int number)
    {

        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT  * FROM depot WHERE  userid='"+userid+ "'AND clothingid='"+clothingid+"';";
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                sql = "INSERT INTO depot VALUES('"
                    + clothingid + "', "
                    + number + ", '"
                    + userid + "', 0);";
                //执行SQL语句
                stmt.executeUpdate(sql);
                retuv=true;

            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {

                    sql="update depot set state=0,numbers="+number+" WHERE clothingid='"+clothingid+"' AND userid='"+userid+"';";
//                   System.out.println(sql);
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
                else {
                    int numbers = rs.getInt("numbers");
                    number +=numbers;
                    sql="update depot set state=0,numbers="+number+" WHERE clothingid='"+clothingid+"' AND userid='"+userid+"';";
//                   System.out.println(sql);
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
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
    //查操作
    public static Vector<DepotDetailsDTO> SearchDepotMessage(String temp,String authority,String userid)
    {
        Vector<DepotDetailsDTO> a=new Vector<DepotDetailsDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql;
            if(authority.equals("su"))
            {
                sql = "SELECT d.clothingid,c.property,c.color,c.size,c.price,d.numbers,d.userid,d.state" +
                        " FROM depot d,clothes c WHERE c.clothingid=d.clothingid and (d.userid LIKE'%"+temp+"%' OR d.clothingid LIKE'%"+temp+"%'OR d.numbers="+temp+");";
//                System.out.println(sql);
            }
            else
            {
                sql = "SELECT d.clothingid,c.property,c.color,c.size,c.price,d.numbers,d.userid,d.state" +
                        " FROM depot d,clothes c WHERE d.userid ='"+userid+"' AND c.clothingid=d.clothingid and ( d.clothingid LIKE'%"+temp+"%'OR d.numbers="+temp+");";
//                System.out.println(sql);
            }
            rs=stmt.executeQuery(sql);
            while (rs.next()) {
                if(rs.getInt("d.state")==0)
                {
                    //System.out.println("ttt");
                    DepotDetailsDTO aa=new DepotDetailsDTO();
                    aa.setClothingid(rs.getString("d.clothingid"));
                    aa.setColor(rs.getString("c.color"));
                    aa.setNumbers(rs.getInt("d.numbers"));
                    aa.setSize(rs.getString("c.size"));
                    aa.setUserid(rs.getString("d.userid"));
                    aa.setProperty(rs.getString("c.property"));
                    aa.setPrice(rs.getFloat("c.price"));
                    a.add(aa);
                    // System.out.println("tt");
                }


            }

        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("系统出错,请重新测试！");

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
    public static boolean ChangeDepotMessage(String clothingida,String userid,String number)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM depot where clothingid='"+clothingida+"' and userid='"+userid + "';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该条仓库信息 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该条仓库信息 请重新输入！");
                }
                else{
                    sql = "update depot set numbers="+number+" where clothingid='"+clothingida+"' and userid='"+userid + "';";
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("没有该仓库信息，请重新测试！");


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
    public static boolean DeleteDepotMessage(String clothingida,String userida)
    {

        String aa=clothingida.substring(1);
        char c=clothingida.charAt(0);
        boolean retuv=false;
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM  depot where clothingid='"+clothingida+"' and userid='"+userida + "';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该条仓库信息 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {
                    System.out.println("没有该条仓库信息 请重新输入！");
                    return false;
                }
                else
                {
                    sql="UPDATE depot set state=1 where clothingid='"+clothingida+"' and userid='"+userida + "';";
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
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return retuv;
        }
    }

    /**
     * 寻找衣服（按照权限）
     */
    public static Vector<DepotDetailsDTO> findAllDepot(String userid, String authority)
    {
        Vector<DepotDetailsDTO> a =new Vector<DepotDetailsDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            if(authority.equals("su"))
            {
                sql="SELECT d.clothingid,c.property,c.color,c.size,c.price,d.numbers,d.userid,d.state" +
                        " FROM depot d,clothes c where  c.clothingid=d.clothingid;";
            }
            else {
                sql="SELECT d.clothingid,c.property,c.color,c.size,c.price,d.numbers,d.userid,d.state" +
                        " FROM depot d,clothes c where userid='"+userid+"' and c.clothingid=d.clothingid;";
            }
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("d.state")==0)
                {
                    //System.out.println("ttt");
                    DepotDetailsDTO aa=new DepotDetailsDTO();
                    aa.setClothingid(rs.getString("d.clothingid"));
                    aa.setColor(rs.getString("c.color"));
                    aa.setNumbers(rs.getInt("d.numbers"));
                    aa.setSize(rs.getString("c.size"));
                    aa.setUserid(rs.getString("d.userid"));
                    aa.setProperty(rs.getString("c.property"));
                    aa.setPrice(rs.getFloat("c.price"));
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
    //管理员找自己的衣服

    public static Vector<DepotDetailsDTO> findAllDepotByAdmin(String userid){
        Vector<DepotDetailsDTO> a =new Vector<DepotDetailsDTO>();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT d.clothingid,c.property,c.color,c.size,c.price,d.numbers,d.userid,d.state" +
                        " FROM depot d,clothes c where userid='"+userid+"' and c.clothingid=d.clothingid;";
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {

                if(rs.getInt("d.state")==0)
                {
                    //System.out.println("ttt");
                    DepotDetailsDTO aa=new DepotDetailsDTO();
                    aa.setClothingid(rs.getString("d.clothingid"));
                    aa.setColor(rs.getString("c.color"));
                    aa.setNumbers(rs.getInt("d.numbers"));
                    aa.setSize(rs.getString("c.size"));
                    aa.setUserid(rs.getString("d.userid"));
                    aa.setProperty(rs.getString("c.property"));
                    aa.setPrice(rs.getFloat("c.price"));
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
     * 判断是否能加入销售
     */
    public static boolean JudgeInsert(Vector<DepotDetailsDTO> a,String clothingid,String userid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        int i=0;
        DepotDetailsDTO aa=new DepotDetailsDTO();
//        if(a.size()==0)
//        {
////            System.out.println("aaaa");
//            return true;
//        }
        System.out.println("大小："+a.size());
        while (a.size()>i)
        {
            aa=a.get(i);
            System.out.println(aa.getClothingid());
            if(aa.getClothingid().compareTo(clothingid)==0)
            {
               // System.out.println("成功？");
                break;
            }
            i++;

        }
        if(a.size()==i)
        {
            aa=new DepotDetailsDTO();
        }
        try {
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            if(a.size()!=i)
            {
                String sql="SELECT * FROM depot WHERE userid='"+userid+"' and clothingid='"+clothingid+"';";
//                System.out.println(sql);
                rs=stmt.executeQuery(sql)   ;
                if(rs.next())
                {
//                    System.out.println("仓库："+rs.getInt("numbers")+" 销售单："+aa.getNumbers());
                    if((rs.getInt("numbers")>(aa.getNumbers()))&&(rs.getInt("state")==0))
                    {
                        retuv=true;
                    }
                }
            }
            else {
                String sql="SELECT * FROM depot WHERE userid='"+userid+"' and clothingid='"+clothingid+"';";
                rs=stmt.executeQuery(sql)   ;
                if(rs.next())
                {
                    if(rs.getInt("state")==0)
                    {
                        retuv=true;
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return  retuv;
        }





    }
    public static boolean JudgeInsertForStock(Vector<DepotDTO> a,String clothingid,String userid)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        int i=0;
        DepotDTO aa=new DepotDTO();
//        if(a.size()==0)
//        {
////            System.out.println("aaaa");
//            return true;
//        }
        while (a!=null&&a.size()>i)
        {
            aa=a.get(i);
            if(aa.getClothingid().compareTo(clothingid)==0)
            {
                // System.out.println("成功？");
                break;
            }
            i++;

        }
        if(a!=null&&a.size()==i)
        {
            aa=new DepotDTO();
        }
        try {
            conn=dataAccess.getConnection();
            stmt=conn.createStatement();
            if(a!=null&&a.size()!=i)
            {
                String sql="SELECT * FROM depot WHERE userid='"+userid+"' and clothingid='"+clothingid+"';";
//                System.out.println(sql);
                rs=stmt.executeQuery(sql)   ;
                if(rs.next())
                {
//                    System.out.println("仓库："+rs.getInt("numbers")+" 销售单："+aa.getNumbers());
                    if((rs.getInt("numbers")>(aa.getNumbers()))&&(rs.getInt("state")==0))
                    {
                        retuv=true;
                    }
                }
            }
            else {
                String sql="SELECT * FROM depot WHERE userid='"+userid+"' and clothingid='"+clothingid+"';";
                rs=stmt.executeQuery(sql)   ;
                if(rs.next())
                {
                    if(rs.getInt("state")==0)
                    {
                        retuv=true;
                    }
                }
            }
        }catch (Exception e){e.printStackTrace();}
        finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return  retuv;
        }





    }
    /**
     * 拿出一个DepotDetail
     */
    public static DepotDetailsDTO SearchOneDepotDetail(String temp,String userid)
    {
        DepotDetailsDTO aa=new DepotDetailsDTO();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql = "SELECT d.clothingid,c.property,c.color,c.size,c.price,d.numbers,d.userid,d.state,c.discount" +
                        " FROM depot d,clothes c WHERE d.userid ='"+userid+"' AND c.clothingid=d.clothingid and ( d.clothingid LIKE'%"+temp+"%' OR d.numbers="+temp+");";
            rs=stmt.executeQuery(sql);
            System.out.println(sql);

            if (rs.next()) {
                if(rs.getInt("d.state")==0)
                {
                    //System.out.println("ttt");

                    aa.setClothingid(rs.getString("d.clothingid"));
                    aa.setColor(rs.getString("c.color"));
                    aa.setNumbers(rs.getInt("d.numbers"));
                    aa.setSize(rs.getString("c.size"));
                    aa.setUserid(rs.getString("d.userid"));
                    aa.setProperty(rs.getString("c.property"));
                    aa.setPrice(rs.getFloat("c.price"));
                    aa.setDiscount(rs.getFloat("c.discount"));
                    // System.out.println("tt");
                }


            }

        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("系统出错,请重新测试！");

        }finally {
            dataAccess.closeConnection(rs);
            dataAccess.closeConnection(stmt);
            dataAccess.closeConnection(conn);
            return aa;
        }

    }
    /**
     * 减少库存
     */
    public static boolean ReduceInventory(SalesDTO saless)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM depot where clothingid='"+saless.getClothingid()+"' and userid='"+saless.getUserid() + "';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该条仓库信息 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {

                    System.out.println("没有该条仓库信息 请重新输入！");
                }
                else{
                    if(rs.getInt("numbers")-saless.getNumbers()==0)
                    {
                        sql = "update depot set state=1,numbers=0 where clothingid='"+saless.getClothingid()+"' and userid='"+saless.getUserid() + "';";
                    }
                    else{
                        sql = "update depot set numbers="+(rs.getInt("numbers")-saless.getNumbers())+" where clothingid='"+saless.getClothingid()+"' and userid='"+saless.getUserid() + "';";

                    }
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("没有该仓库信息，请重新测试！");


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
     * 增加一个库存
     */
    public static boolean InsertInventory(String clothingid,String userid,int numbers)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM depot where clothingid='"+clothingid+"' and userid='"+userid + "';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                sql = "INSERT INTO depot VALUES('"
                        + clothingid + "',"+numbers+",'"
                        + userid + "', 0);";
                //执行SQL语句
                stmt.executeUpdate(sql);
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {

                    sql = "update depot set state=1,numbers="+numbers+" where clothingid='"+clothingid+"' and userid='"+userid + "';";
                }
                else{

                        sql = "update depot set numbers="+(rs.getInt("numbers")+numbers)+" where clothingid='"+clothingid+"' and userid='"+userid + "';";
                }
                stmt.executeUpdate(sql);
                retuv=true;
            }


        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("没有该仓库信息，请重新测试！");


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
     * 出库减少库存
     */
    public static boolean StockOutReduceInventory(String clothingid,String userid,int numbers)
    {
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs=null;
        boolean retuv=false;
        try {
            conn= dataAccess.getConnection();
            stmt = conn.createStatement();//创建SQL语句
            String sql=null;
            sql="SELECT * FROM depot where clothingid='"+clothingid+"' and userid='"+userid + "';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            if(!rs.next())
            {
                System.out.println("没有该条仓库信息 请重新输入！");
            }
            else
            {
                int state=rs.getInt("state");
                if(state==1)
                {

                    System.out.println("没有该条仓库信息 请重新输入！");
                }
                else{
                    if(rs.getInt("numbers")-numbers==0)
                    {
                        sql = "update depot set state=1,numbers=0 where clothingid='"+clothingid+"' and userid='"+userid + "';";
                    }
                    else{
                        sql = "update depot set numbers="+(rs.getInt("numbers")-numbers)+" where clothingid='"+clothingid+"' and userid='"+userid + "';";

                    }
                    System.out.println(sql);
                    stmt.executeUpdate(sql);
                    retuv=true;
                }
            }


        }
        catch (Exception e) {
            e.printStackTrace();
//            System.out.println("没有该仓库信息，请重新测试！");


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
