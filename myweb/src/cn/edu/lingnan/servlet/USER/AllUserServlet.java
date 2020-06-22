package cn.edu.lingnan.servlet.USER;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Vector;

public class AllUserServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
       // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
       // System.out.println("6666");
        Vector<UserDTO> AllUser=UserDAO.findAllUser();

        //System.out.println(AllUser.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllUser",AllUser);
        //System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"adminmain.jsp");
    }
}
