package cn.edu.lingnan.servlet.FLOWSHEET;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dto.FlowSheetDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class searchFlowsheetServlet extends HttpServlet

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
          //  String year=session.getAttribute("authority").toString();
          //  String userid=session.getAttribute("userid").toString();
          //  String SearchTemp=request.getParameter("SearchTemp");
//            SearchTemp = new String(SearchTemp.getBytes("iso-8859-1"),"GB18030");
//            System.out.println(SearchTemp+"1111");
//            System.out.println(authority+"1111");
//            System.out.println(userid+"1111");
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
            String payway=request.getParameter("paywayF");
//            payway=new String(payway.getBytes("iso-8859-1"),"GB18030");
            String flowid=request.getParameter("flowidF");
            String userid=request.getParameter("useridF");
            //System.out.println(dete+"0000");
            //System.out.println(payway+"1111");
            //System.out.println(flowid+"2222");
            //System.out.println(userid+"3333");
            Vector<FlowSheetDTO> AllFlowsheet= FlowSheetDAO.SearchFlowsheetMessage(dete,flowid,payway,userid,null);
            if(AllFlowsheet.size()!=0)
            {
                request.setCharacterEncoding("GB18030");
                session.setAttribute("AllFlowsheet",AllFlowsheet);
                //System.out.println(request.getContextPath());
                response.sendRedirect(request.getContextPath()+"/admin/flowsheetmain.jsp");
            }
            else{

                response.getWriter().print( "<script>alert(\"请确认输入的值是否存在!\");window.location.href='/admin/flowsheetmain.jsp'</script>");
                //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

            }


        }

    }