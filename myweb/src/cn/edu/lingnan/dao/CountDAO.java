package cn.edu.lingnan.dao;
import cn.edu.lingnan.dto.Count.*;
import cn.edu.lingnan.util.dataAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class CountDAO {
    /**
     * 普通用户清点员工业绩
     */
    public static Vector<achievementStaffDTO>  CountStaffAchievement(int state,String userid)
    {
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        Date date1 = new Date();
        Vector<achievementStaffDTO> a=new Vector<achievementStaffDTO>();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date2 = aaaaa.format(date1).substring(0,11);
//        System.out.println(date2);
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            if(state==0)
            {
                sql="SELECT s.* FROM sales s,flowsheet f WHERE f.time like'"+date2+"%' and s.userid='"+userid+"' and s.flowid=f.flowid ;";
            }
            else if(state==1)
            {
                sql="SELECT s.* FROM sales s,flowsheet f WHERE f.time like'"+date2.substring(0,8)+"%' and s.userid='"+userid+"'  and s.flowid=f.flowid ;";
            }
            else if(state==2)
            {
                sql="SELECT s.* FROM sales s,flowsheet f WHERE f.time like'"+date2.substring(0,4)+"%' and s.userid='"+userid+"'  and s.flowid=f.flowid ;";

            }
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                int i=0;
                if(a.size()==0)
                {
                    achievementStaffDTO aa=new achievementStaffDTO();
                    aa.setStaffid(rs.getString("staffid"));
                    aa.setAchievement(rs.getFloat("disprice"));
                    a.add(aa);
                }
                else {
                    String staffid=rs.getString("staffid");

                    while(a.size()>i)
                    {
                        if(staffid.compareTo(a.get(i).getStaffid())==0){
                            a.get(i).setAchievement((rs.getFloat("disprice"))+a.get(i).getAchievement());
                        break;
                        }
                        i++;
                    }
                    if(i==a.size())
                    {
                        achievementStaffDTO aa=new achievementStaffDTO();
                        aa.setStaffid(rs.getString("staffid"));
                        aa.setAchievement(rs.getFloat("disprice"));
                        a.add(aa);
                    }

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
            return a;

        }
    }

    /**
     * 普通用户进行支付方式的营业额统计
     */
    public static float[]  CountPaywayAchievement(int state,String userid)
    {
        float a[]=new float[4];
        for(float i:a)
        {
            i=0;
        }
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date2 = aaaaa.format(date1).substring(0,11);
//        System.out.println(date2);
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            if(state==0)
            {
                sql="SELECT * FROM flowsheet f  WHERE f.time like'"+date2+"%' and userid='"+userid+"'  ;";
            }
            else if(state==1)
            {
                sql="SELECT * FROM flowsheet f WHERE f.time like'"+date2.substring(0,8)+"%' and userid='"+userid+"'  ;";

            }
            else if(state==2)
            {
                sql="SELECT * FROM flowsheet f WHERE f.time like'"+date2.substring(0,4)+"%' and userid='"+userid+"'  ;";

            }
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                if(rs.getString("payway").compareTo("现金")==0)
                {
                    a[0]+=rs.getFloat("pricea");
                }
                else if(rs.getString("payway").compareTo("微信")==0)
                {
                    a[1]+=rs.getFloat("pricea");
                }
                else if(rs.getString("payway").compareTo("支付宝")==0)
                {
                    a[2]+=rs.getFloat("pricea");
                }
                else if(rs.getString("payway").compareTo("银行卡")==0)
                {
                    a[3]+=rs.getFloat("pricea");
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
            return a;

        }
    }
    /**
     * 统计出一日销量最好的十件商品
     */
    public static Vector<salesKingDTO> findSalesKing(String userid)
    {
        Vector<salesKingDTO> a=new Vector<salesKingDTO>();
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date2 = aaaaa.format(date1).substring(0,11);
//        System.out.println(date2);
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT s.* FROM sales s,flowsheet f WHERE f.flowid=s.flowid AND f.time LIKE '"+date2+"%' and s.userid='"+userid+"';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                int i=0;
                if(a.size()==0)
                {
                    salesKingDTO aa=new salesKingDTO();
                    aa.setClothingid(rs.getString("clothingid"));
                    aa.setMunber(rs.getInt("numbers"));
                    if(rs.getFloat("disprice") < 0){
                    aa.setMunber(0 - rs.getInt("numbers"));
                }
                    a.add(aa);
                }
                else {
                    String clothingid=rs.getString("clothingid");

                    while(a.size()>i)
                    {
                        if(clothingid.compareTo(a.get(i).getClothingid())==0){
                            if(rs.getFloat("disprice") < 0){
                                a.get(i).setMunber(a.get(i).getMunber()-(rs.getInt("numbers")));
                            }else {
                                a.get(i).setMunber((rs.getInt("numbers"))+a.get(i).getMunber());
                            }
                            break;
                        }
                        i++;
                    }
                    if(i==a.size())
                    {
                        salesKingDTO aa=new salesKingDTO();
                        aa.setClothingid(rs.getString("clothingid"));
                        aa.setMunber(rs.getInt("numbers"));
                        if(rs.getFloat("disprice") < 0){
                            aa.setMunber(0 - rs.getInt("numbers"));
                        }
                        a.add(aa);
                    }

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
            return a;

        }
    }

    /**
     * 管理员查看各区业绩
     */
    public static Vector<achievementUserDTO>  CountUserAchievement(int state)
    {
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        Date date1 = new Date();
        Vector<achievementUserDTO> a=new Vector<achievementUserDTO>();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date2 = aaaaa.format(date1).substring(0,11);
//        System.out.println(date2);
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            if(state==0)
            {
                sql="SELECT s.* FROM sales s,flowsheet f WHERE f.time like'"+date2+"%' and s.flowid=f.flowid ;";
            }
            else if(state==1)
            {
                sql="SELECT s.* FROM sales s,flowsheet f WHERE f.time like'"+date2.substring(0,8)+"%' and s.flowid=f.flowid ;";
            }
            else if(state==2)
            {
                sql="SELECT s.* FROM sales s,flowsheet f WHERE f.time like'"+date2.substring(0,4)+"%' and s.flowid=f.flowid ;";

            }
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                int i=0;
                if(a.size()==0)
                {
                    achievementUserDTO aa=new achievementUserDTO();
                    aa.setUserid(rs.getString("userid").substring(0,2));
                    aa.setAchievement(rs.getFloat("disprice"));
                    a.add(aa);
                }
                else {
                    String userid=rs.getString("userid").substring(0,2);

                    while(a.size()>i)
                    {
                        if(userid.compareTo(a.get(i).getUserid())==0){
                            a.get(i).setAchievement((rs.getFloat("disprice"))+a.get(i).getAchievement());
                            break;
                        }
                        i++;
                    }
                    if(i==a.size())
                    {
                        achievementUserDTO aa=new achievementUserDTO();
                        aa.setUserid(rs.getString("userid").substring(0,2));
                        aa.setAchievement(rs.getFloat("disprice"));
                        a.add(aa);
                    }

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
            return a;

        }

    }

    /**
     * 所有使用者的支付方式汇总
     */
    public static float[]  CountPaywayUserAchievement(int state)
    {
        float a[]=new float[4];
        for(float i:a)
        {
            i=0;
        }
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date2 = aaaaa.format(date1).substring(0,11);
//        System.out.println(date2);
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            if(state==0)
            {
                sql="SELECT * FROM flowsheet f  WHERE f.time like'"+date2+"%'  ;";
            }
            else if(state==1)
            {
                sql="SELECT * FROM flowsheet f WHERE f.time like'"+date2.substring(0,8)+"%'   ;";

            }
            else if(state==2)
            {
                sql="SELECT * FROM flowsheet f WHERE f.time like'"+date2.substring(0,4)+"%' ;";

            }
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                if(rs.getString("payway").compareTo("现金")==0)
                {
                    a[0]+=rs.getFloat("pricea");
                }
                else if(rs.getString("payway").compareTo("微信")==0)
                {
                    a[1]+=rs.getFloat("pricea");
                }
                else if(rs.getString("payway").compareTo("支付宝")==0)
                {
                    a[2]+=rs.getFloat("pricea");
                }
                else if(rs.getString("payway").compareTo("银行卡")==0)
                {
                    a[3]+=rs.getFloat("pricea");
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
            return a;

        }

    }
/**
 * 寻找所有店铺中卖得最好的
 */
    public static salesKingDTO findUserSalesKing()
    {
        Vector<salesKingDTO> a=new Vector<salesKingDTO>();
        Connection conn=null;
        ResultSet rs=null;
        Statement stmt=null;
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        String date2 = aaaaa.format(date1).substring(0,11);
//        System.out.println(date2);
        try {
            conn= dataAccess.getConnection();
            stmt=conn.createStatement();
            String sql=null;
            sql="SELECT s.* FROM sales s,flowsheet f WHERE f.flowid=s.flowid AND f.time LIKE '"+date2+"%';";
//            System.out.println(sql);
            rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                int i=0;
                if(a.size()==0)
                {
                    salesKingDTO aa=new salesKingDTO();
                    aa.setClothingid(rs.getString("clothingid"));
                    aa.setMunber(rs.getInt("numbers"));
                    if(rs.getFloat("disprice") < 0){
                        aa.setMunber(0 - rs.getInt("numbers"));
                    }
                    a.add(aa);
                }
                else {
                    String clothingid=rs.getString("clothingid");

                    while(a.size()>i)
                    {
                        if(clothingid.compareTo(a.get(i).getClothingid())==0){
                            if(rs.getFloat("disprice") < 0){
                                a.get(i).setMunber(a.get(i).getMunber()-(rs.getInt("numbers")));
                            }else {
                                a.get(i).setMunber((rs.getInt("numbers"))+a.get(i).getMunber());
                            }


//                            System.out.println("a.get(i).getMunber():"+a.get(i).getMunber());
                            break;
                        }
                        i++;
                    }
                    if(i==a.size())
                    {
                        salesKingDTO aa=new salesKingDTO();
                        aa.setClothingid(rs.getString("clothingid"));
                        aa.setMunber(rs.getInt("numbers"));
                        if(rs.getFloat("disprice") < 0){
                            aa.setMunber(0 - rs.getInt("numbers"));
                        }
                        a.add(aa);
                    }

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
            int i=1;
            int max=0;
            if(a.size()==0)
            {
                return null;
            }
            while(i<a.size())
            {
                if(a.get(i).getMunber()>a.get(max).getMunber())
                {
                    max=i;
                }
                i++;
            }

            return a.get(max);

        }
    }

}
