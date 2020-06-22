package cn.edu.lingnan.servlet.APPLYUser;

import cn.edu.lingnan.dao.ApplyuserDAO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class acceptAuserServlet extends HttpServlet {
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("GB18030");
        String userid = request.getParameter("useridA");
        String username = request.getParameter("usernameA");
        boolean aaaa = ApplyuserDAO.accessApplyUser(userid,username);
        if (aaaa) {
            Vector<UserDTO> AllAuser = ApplyuserDAO.findAllAuser();
            HttpSession session = request.getSession();
            session.setAttribute("AllAuser", AllAuser);
            response.sendRedirect(request.getContextPath() + "/admin/aUsermain.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/error/errorSystem.html");
        }
    }


}
