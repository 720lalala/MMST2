package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.CountDAO;
import cn.edu.lingnan.dto.Count.achievementUserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class achievementUserServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        int state=Integer.parseInt(request.getParameter("value").toString());
        Vector<achievementUserDTO> achievementUser=new Vector<achievementUserDTO>();
        achievementUser=CountDAO.CountUserAchievement(state);
        session.setAttribute("achievementUser",achievementUser);
        response.sendRedirect(request.getContextPath() + "countsumain.jsp");
    }
}