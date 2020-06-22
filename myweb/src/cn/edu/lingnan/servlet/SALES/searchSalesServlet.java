package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.FlowSheetDTO;
import cn.edu.lingnan.dto.SalesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class searchSalesServlet extends HttpServlet

{
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        HttpSession session = request.getSession();
          String authority=session.getAttribute("authority").toString();
          String userid=session.getAttribute("userid").toString();
        String month=request.getParameter("month");
        String day=request.getParameter("day");
        String dete=request.getParameter("year");
        if(month.length()==1)
        {
            month="0"+month;
        }
        if(day.length()==1)
        {
            day="0"+day;
        }
        if(month != null && month != "")
            dete = dete +"-"+month;
        if(day != null && day != "")
            dete = dete +"-"+day;
        String staffid=request.getParameter("staffidF");
        Vector<SalesDTO> AllSalesD= SalesDAO.SearchSomeSalesMessage(dete,staffid,userid,authority);
        if(AllSalesD.size()!=0)
        {
            request.setCharacterEncoding("GB18030");
            session.setAttribute("AllSalesD",AllSalesD);
//            System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath()+"/allCanAccept/checkSalesmain.jsp");
        }
        else{

            response.getWriter().print( "<script>alert(\"请确认输入的值是否存在!\");window.location.href='/allCanAccept/checkSalesmain.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}