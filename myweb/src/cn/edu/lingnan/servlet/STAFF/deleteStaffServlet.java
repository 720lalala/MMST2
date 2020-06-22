package cn.edu.lingnan.servlet.STAFF;

import cn.edu.lingnan.dao.StaffDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.StaffDTO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class deleteStaffServlet extends HttpServlet {
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
        String staffid=request.getParameter("staffid");
        // System.out.println(userid+"0000");
        // System.out.println(password+"1111");
        String authority=session.getAttribute("authority").toString();
        String userid=session.getAttribute("userid").toString();
        boolean aaaa= StaffDAO.DeleteStaffMessage(staffid);
        if(aaaa)
        {
            Vector<StaffDTO> Allstaff= StaffDAO.findAllStaff(userid,authority);
            //System.out.println(Allstaff.size());
            request.setCharacterEncoding("GB18030");
            session.setAttribute("Allstaff",Allstaff);
            response.getWriter().print( "<script>alert(\"删除成功!\");window.location.href='/allCanAccept/staffmain.jsp'</script>");
        }
        else{
            response.getWriter().print( "<script>alert(\"系统出错，请再次操作!\");window.location.href='/allCanAccept/staffmain.jsp'</script>");

           // response.sendRedirect(request.getContextPath()+ "error/errorSystem.html");
        }


    }

}


