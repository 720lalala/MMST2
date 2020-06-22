package cn.edu.lingnan.servlet.VIP;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dao.VipDAO;
import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.dto.VipDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Vector;
/**
 * @author admin on 2020-02-26.
 * @version 1.0
 */
public class AllVipServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        // System.out.println("6666");
        String authority = session.getAttribute("authority").toString();
        Vector<VipDTO> AllVip= VipDAO.findAllVip(authority);

        //System.out.println(AllUser.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllVip",AllVip);
        //System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/allCanAccept/aUserVipmain.jsp");
    }
}
