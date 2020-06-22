package cn.edu.lingnan.servlet.COUNT;
import cn.edu.lingnan.dto.Count.*;
import cn.edu.lingnan.dao.CountDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class countpuServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        String userid=session.getAttribute("userid").toString();
        float paywayAchievement[]=new float[4];
        paywayAchievement=CountDAO.CountPaywayAchievement(0,userid);
        session.setAttribute("paywayAchievement",paywayAchievement);
        Vector<achievementStaffDTO> achievementStaff=new Vector<achievementStaffDTO>();
        achievementStaff=CountDAO.CountStaffAchievement(0,userid);
        session.setAttribute("achievementStaff",achievementStaff);
        Vector<salesKingDTO> salesKing=CountDAO.findSalesKing(userid);
        session.setAttribute("salesKing",salesKing);
        response.sendRedirect(request.getContextPath() + "countpumain.jsp");

    }
}