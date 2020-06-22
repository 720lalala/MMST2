package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-04-07.
 * @version 1.0
 */
public class returnServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // System.out.println("6666");
        String authority = session.getAttribute("authority").toString();
        String salesUserid=session.getAttribute("userid").toString();

        String flowid= FlowSheetDAO.searchFutureFlowidForReturn(salesUserid);

        session.setAttribute("FutureFlowid",flowid);
        session.setAttribute("salesUserid",salesUserid);
        request.setCharacterEncoding("GB18030");
        Vector<DepotDetailsDTO> salesDetails=new Vector<DepotDetailsDTO>();
        session.setAttribute("salesDetails",salesDetails);
        Vector<DepotDetailsDTO> returnDetails=new Vector<DepotDetailsDTO>();
        session.setAttribute("returnDetails",returnDetails);
        response.sendRedirect(request.getContextPath()+"/allCanAccept/returnsPolicy.jsp");
    }
}

