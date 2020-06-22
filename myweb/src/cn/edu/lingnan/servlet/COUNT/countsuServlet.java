package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.CountDAO;
import cn.edu.lingnan.dto.Count.achievementStaffDTO;
import cn.edu.lingnan.dto.Count.achievementUserDTO;
import cn.edu.lingnan.dto.Count.salesKingDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class countsuServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        Vector<achievementUserDTO> achievementUser=new Vector<achievementUserDTO>();
        achievementUser=CountDAO.CountUserAchievement(0);
        session.setAttribute("achievementUser",achievementUser);
        float paywayAchievement[]=new float[4];
        paywayAchievement=CountDAO.CountPaywayUserAchievement(0);
        session.setAttribute("paywayAchievement",paywayAchievement);
        salesKingDTO salesKing=CountDAO.findUserSalesKing();
        session.setAttribute("salesKing",salesKing);

        response.sendRedirect(request.getContextPath() + "countsumain.jsp");

    }
}