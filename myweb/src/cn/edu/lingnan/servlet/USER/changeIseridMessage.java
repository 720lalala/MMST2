package cn.edu.lingnan.servlet.USER;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class changeIseridMessage extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        String userid=session.getAttribute("userid").toString();
        // System.out.println("6666");
        UserDTO User= UserDAO.raedUserMessage(userid);
        request.setCharacterEncoding("GB18030");
        session.setAttribute("User",User);
        //System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"ChangeUseridMessage.jsp");
    }
}

