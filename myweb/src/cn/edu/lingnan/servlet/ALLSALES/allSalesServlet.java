package cn.edu.lingnan.servlet.ALLSALES;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class allSalesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("GB18030");
        session.setAttribute("FutureFlowid","");
        session.setAttribute("salesUserid","");
        Vector<DepotDetailsDTO> salesDetails=new Vector<DepotDetailsDTO>();
        session.setAttribute("salesDetails",salesDetails);
        response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
    }
}