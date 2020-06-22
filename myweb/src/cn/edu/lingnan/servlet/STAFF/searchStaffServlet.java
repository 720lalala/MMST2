package cn.edu.lingnan.servlet.STAFF;

import cn.edu.lingnan.dao.StaffDAO;
import cn.edu.lingnan.dto.StaffDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class searchStaffServlet extends HttpServlet {
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
        HttpSession session = request.getSession();
//        request.setCharacterEncoding("utf-8");
        String authority=session.getAttribute("authority").toString();
        String userid=session.getAttribute("userid").toString();
        String SearchTemp=request.getParameter("SearchTemp");
        //System.out.println(SearchTemp+"1111");
        //SearchTemp = new String(SearchTemp.getBytes("GB18030"),"UTF-8");
        //System.out.println(SearchTemp+"1111");
        //System.out.println(authority+"1111");
        //System.out.println(userid+"1111");

        Vector<StaffDTO> aaaa= StaffDAO.SearchStaffMessage(SearchTemp,authority,userid);
        if(aaaa.size()!=0)
        {
            //System.out.println(aaaa.size());
            session.setAttribute("Allstaff",aaaa);
            response.sendRedirect(request.getContextPath()+"/allCanAccept/staffmain.jsp");
        }
        else{

            response.getWriter().print( "<script>alert(\"请确认输入的值是否存在!\");window.location.href='/allCanAccept/staffmain.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}