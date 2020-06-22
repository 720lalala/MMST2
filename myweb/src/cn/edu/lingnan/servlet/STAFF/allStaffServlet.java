package cn.edu.lingnan.servlet.STAFF;

import cn.edu.lingnan.dao.ApplyuserDAO;
import cn.edu.lingnan.dao.StaffDAO;
import cn.edu.lingnan.dto.StaffDTO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class allStaffServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
       // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String authority=session.getAttribute("authority").toString();
        String userid=session.getAttribute("userid").toString();
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<StaffDTO> Allstaff= StaffDAO.findAllStaff(userid,authority);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("Allstaff",Allstaff);
       // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/allCanAccept/staffmain.jsp");
    }
}
