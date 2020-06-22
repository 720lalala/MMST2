package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-02-28.
 * @version 1.0
 */
public class countDepotServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String authority = session.getAttribute("authority").toString();
        String userid = session.getAttribute("userid").toString();
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<DepotDetailsDTO> AllDepotD = DepotDAO.findAllDepotByAdmin(userid);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("CountAllDepotD", AllDepotD);
        session.setAttribute("sumpiece", 0);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/allCanAccept/countDepot.jsp");
    }
}
