package cn.edu.lingnan.servlet.DEPOT;
import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class AllDepotServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
      
        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String authority = session.getAttribute("authority").toString();
        String userid = session.getAttribute("userid").toString();
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<DepotDetailsDTO> AllDepotD = DepotDAO.findAllDepot(userid, authority);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllDepotD", AllDepotD);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/allCanAccept/depotmain.jsp");
    }
}