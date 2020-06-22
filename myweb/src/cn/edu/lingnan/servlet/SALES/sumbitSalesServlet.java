package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.*;
import cn.edu.lingnan.dto.DepotDetailsDTO;
import cn.edu.lingnan.dto.FlowSheetDTO;
import cn.edu.lingnan.dto.SalesDTO;
import cn.edu.lingnan.dto.StaffDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class sumbitSalesServlet extends HttpServlet {
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
       // request.setCharacterEncoding("GB18030");
        HttpSession session = request.getSession();
        String flowid=session.getAttribute("FutureFlowid").toString();
        float sumprice=Float.parseFloat(request.getParameter("sumPriceF"));
        String payway=request.getParameter("zhifuF");
//        payway = new String(payway.getBytes("ISO8859_1"),"GB18030");
        String userid=session.getAttribute("salesUserid").toString();
        Vector<DepotDetailsDTO> salesDetails=( Vector<DepotDetailsDTO>)session.getAttribute("salesDetails");
        FlowSheetDTO InsertFlowsheet=new FlowSheetDTO();
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
        String vipphone = null;
        int score= 0 ;
        int vipScore =0 ;
        try {
            score = Integer.valueOf(request.getParameter("scoreF").toString());
            vipphone = session.getAttribute("vipPhone").toString();
            vipScore = Integer.valueOf(session.getAttribute("vipScore").toString());
        }catch (Exception e){

        }
        if(vipScore < score || score < 0)
        {
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"扣除积分多于原有积分或扣除积分少于0!\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"扣除积分多于原有积分或扣除积分少于0!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }
        }
        boolean judge = true;
        for(int i = 0; i < salesDetails.size(); i++){
            if(salesDetails.get(i).getStaffid() == null || salesDetails.get(i).getStaffid() == "null")
            {
                judge = false;
                //System.out.println("系统出错！");
                if(sales1.equals(slaes2))
                {
                    response.getWriter().print( "<script>alert(\"员工编号未输入!\");window.location.href='/allCanAccept/sales.jsp'</script>");
                }
                else
                {
                    response.getWriter().print( "<script>alert(\"员工编号未输入!\");window.location.href='/admin/allSalesmain.jsp'</script>");
                }
            }
        }
        if(judge){
            InsertFlowsheet.setUserid(userid);
            Date date1 = new Date();
            SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date2 = aaaaa.format(date1);
            InsertFlowsheet.setTime(date2);
            InsertFlowsheet.setPricea(sumprice-score);
            InsertFlowsheet.setPayway(payway);
            InsertFlowsheet.setUserid(userid);
            InsertFlowsheet.setFlowid(flowid);
            InsertFlowsheet.setVipphone(vipphone);
            InsertFlowsheet.setUsescore(score);
            boolean bool= FlowSheetDAO.InsertFlowSheetMessage(InsertFlowsheet);
            int i=0;
            if(bool){

                VipDAO.settlementVipScore(vipScore - score + (int)(sumprice/10.0),vipphone);
                while (salesDetails.size()>i)
                {
                    SalesDTO tempsales=new SalesDTO();
                    tempsales.setClothingid(salesDetails.get(i).getClothingid());
                    tempsales.setUserid(salesDetails.get(i).getUserid());
                    tempsales.setStaffid(salesDetails.get(i).getStaffid());
                    tempsales.setDisprice(salesDetails.get(i).getDiscount()*salesDetails.get(i).getPrice()*salesDetails.get(i).getNumbers());
                    tempsales.setFlowid(flowid);
                    tempsales.setNumbers(salesDetails.get(i).getNumbers());
                    boolean bool1=SalesDAO.InsertSalesMessage(tempsales);
                    boolean bool2= DepotDAO.ReduceInventory(tempsales);
                    if((!bool1)||(!bool2))
                    {
                        break;
                    }
                    i++;
                }
                if(i!=salesDetails.size())
                {
                    //System.out.println("系统出错！");
                    if(sales1.equals(slaes2))
                    {
                        response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/sales.jsp'</script>");
                    }
                    else
                    {
                        response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/admin/allSalesmain.jsp'</script>");
                    }
                }
                else {

                    request.setCharacterEncoding("GB18030");

                    Vector<DepotDetailsDTO> salesDetails2=new Vector<DepotDetailsDTO>();
                    session.setAttribute("salesDetails",salesDetails2);
                    session.removeAttribute("vipPhone");
                    session.removeAttribute("staffid");
                    session.removeAttribute("vipScore");
                    if(sales1.equals(slaes2))
                    {

                        flowid= FlowSheetDAO.searchFutureFlowid(userid);
                        session.setAttribute("FutureFlowid",flowid);

                        response.getWriter().print( "<script>alert(\"成功输入订单!\");window.location.href='/allCanAccept/sales.jsp'</script>");
                    }
                    else
                    {
                        session.setAttribute("FutureFlowid","");
                        response.getWriter().print( "<script>alert(\"成功输入订单!\");window.location.href='/admin/allSalesmain.jsp'</script>");
                    }
                }



            }
        }



    }

}