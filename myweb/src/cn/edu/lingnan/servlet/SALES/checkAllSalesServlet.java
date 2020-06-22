package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;
import cn.edu.lingnan.dto.SalesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class checkAllSalesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String authority = session.getAttribute("authority").toString();
        String userid = session.getAttribute("userid").toString();

        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<SalesDTO> AllSalesD = SalesDAO.AllSalesMessage(authority,userid );
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllSalesD", AllSalesD);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/allCanAccept/checkSalesmain.jsp");
    }
}