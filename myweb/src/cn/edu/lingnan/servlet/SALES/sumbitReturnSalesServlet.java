package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.*;
import cn.edu.lingnan.dto.DepotDetailsDTO;
import cn.edu.lingnan.dto.FlowSheetDTO;
import cn.edu.lingnan.dto.SalesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author admin on 2020-04-07.
 * @version 1.0
 */
public class sumbitReturnSalesServlet extends HttpServlet {
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
        String staffid =session.getAttribute("staffid").toString();
        if(staffid==null||staffid==""){
            response.getWriter().print( "<script>alert(\"未输入员工编号!\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");
            return;
        }
        String userid=session.getAttribute("salesUserid").toString();
        Vector<DepotDetailsDTO> salesDetails=( Vector<DepotDetailsDTO>)session.getAttribute("salesDetails");
        String flowid_return=session.getAttribute("flowid_return").toString();
        Vector<SalesDTO > returnDetails=( Vector<SalesDTO>)session.getAttribute("returnDetails");
        FlowSheetDTO InsertFlowsheet=new FlowSheetDTO();
        InsertFlowsheet.setUserid(userid);
        Date date1 = new Date();
        SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2 = aaaaa.format(date1);
        InsertFlowsheet.setTime(date2);
        InsertFlowsheet.setPricea(sumprice);
        InsertFlowsheet.setPayway(payway);
        InsertFlowsheet.setUserid(userid);
        InsertFlowsheet.setFlowid(flowid);
        InsertFlowsheet.setVipphone(null);
        InsertFlowsheet.setUsescore(0);
        boolean bool= FlowSheetDAO.InsertFlowSheetMessage(InsertFlowsheet);
        int i=0;
            if(bool){
                ReturnDAO.insertReturnPolicy(flowid,flowid_return);
                while (salesDetails.size()>i)
                {
                    SalesDTO tempsales=new SalesDTO();
                    tempsales.setClothingid(salesDetails.get(i).getClothingid());
                    tempsales.setUserid(salesDetails.get(i).getUserid());
                    tempsales.setStaffid(salesDetails.get(i).getStaffid());
                    tempsales.setDisprice(salesDetails.get(i).getDiscount()*salesDetails.get(i).getPrice()*salesDetails.get(i).getNumbers());
                    tempsales.setFlowid(flowid);
                    tempsales.setNumbers(salesDetails.get(i).getNumbers());
                    boolean bool1= SalesDAO.InsertSalesMessage(tempsales);
                    boolean bool2= DepotDAO.ReduceInventory(tempsales);
                    if((!bool1)||(!bool2))
                    {
                        break;
                    }
                    i++;
                }
                int j = 0;
                while (returnDetails.size()>j)
                {
                    SalesDTO tempsales=new SalesDTO();
                    tempsales.setClothingid(returnDetails.get(j).getClothingid());
                    tempsales.setUserid(returnDetails.get(j).getUserid());
                    tempsales.setStaffid(returnDetails.get(j).getStaffid());
                    tempsales.setDisprice(0-returnDetails.get(j).getDisprice());
                    tempsales.setFlowid(flowid);
                    tempsales.setNumbers(returnDetails.get(j).getNumbers());
                    boolean bool1 = SalesDAO.InsertReturnSalesMessage(tempsales);
                    boolean bool2 = DepotDAO.InsertInventory(tempsales.getClothingid(),userid,tempsales.getNumbers());
                    boolean bool3 = SalesDAO.ChangeStateForReturn(tempsales.getClothingid(),flowid_return,tempsales.getNumbers());
                    if((!bool1)||(!bool2)||(!bool3))
                    {
                        break;
                    }
                    j++;
                }
                if((i+j)!=(salesDetails.size()+returnDetails.size()))
                {
                    //System.out.println("系统出错！");
                   response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");

                }
                else {

                    request.setCharacterEncoding("GB18030");

                    Vector<DepotDetailsDTO> salesDetails2=new Vector<DepotDetailsDTO>();
                    session.setAttribute("salesDetails",salesDetails2);
                    Vector<SalesDTO> returnDetail=new Vector<SalesDTO>();
                    session.setAttribute("returnDetails",returnDetail);
                    session.removeAttribute("flowid_return");
                    session.removeAttribute("staffid");
                    flowid= FlowSheetDAO.searchFutureFlowidForReturn(userid);
                        session.setAttribute("FutureFlowid",flowid);
                        response.getWriter().print( "<script>alert(\"成功输入订单!\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");

                }



            }




    }

}
