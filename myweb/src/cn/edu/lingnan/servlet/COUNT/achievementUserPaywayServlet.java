package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.CountDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class achievementUserPaywayServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        String userid=session.getAttribute("userid").toString();
        int state=Integer.parseInt(request.getParameter("value").toString());
        float paywayAchievement[]=new float[4];
        paywayAchievement=CountDAO.CountPaywayUserAchievement(state);
        session.setAttribute("paywayAchievement",paywayAchievement);
        response.sendRedirect(request.getContextPath() + "countsumain.jsp");
    }
}