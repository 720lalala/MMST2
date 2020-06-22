package cn.edu.lingnan.servlet.FLOWSHEET;
import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dto.FlowSheetDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class AllFlowsheetServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        // System.out.println("6666");
        Vector<FlowSheetDTO> AllFlowsheet= FlowSheetDAO.findAllFlowsheet();
        //System.out.println(AllFlowsheet.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllFlowsheet",AllFlowsheet);
        //System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"/admin/flowsheetmain.jsp");
    }
}
