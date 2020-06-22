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

public class salesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // System.out.println("6666");
        String userid=session.getAttribute("userid").toString();
        String salesUserid=session.getAttribute("userid").toString();

        String flowid= FlowSheetDAO.searchFutureFlowid(salesUserid);
        request.setCharacterEncoding("GB18030");
        session.setAttribute("FutureFlowid",flowid);
        session.setAttribute("salesUserid",salesUserid);
        Vector<DepotDetailsDTO> salesDetails=new Vector<DepotDetailsDTO>();
        session.setAttribute("salesDetails",salesDetails);

        response.sendRedirect(request.getContextPath()+"/allCanAccept/sales.jsp");
    }
}

