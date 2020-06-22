package cn.edu.lingnan.servlet.VIP;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dao.VipDAO;
import cn.edu.lingnan.dto.UserDTO;
import cn.edu.lingnan.dto.VipDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-02-26.
 * @version 1.0
 */
public class searchVipServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String SearchTemp = request.getParameter("SearchTemp");
        HttpSession session = request.getSession();
        String authority = session.getAttribute("authority").toString();
        Vector<VipDTO> aaaa = new Vector<VipDTO>();
        if (authority.equals("su")) {
            aaaa = VipDAO.AdminSearchVipMessage(SearchTemp);
        } else {
            aaaa = VipDAO.PuSearchVipMessage(SearchTemp);
        }
        if (aaaa.size() != 0) {
            //System.out.println(aaaa.size());
            session.setAttribute("AllVip", aaaa);
            response.sendRedirect(request.getContextPath() + "/allCanAccept/aUserVipmain.jsp");
        } else {

            response.getWriter().print("<script>alert(\"请确认输入的值是否存在!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }
}
