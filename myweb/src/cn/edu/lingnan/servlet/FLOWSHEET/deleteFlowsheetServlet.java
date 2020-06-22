package cn.edu.lingnan.servlet.FLOWSHEET;

import cn.edu.lingnan.dao.ClothingDAO;
import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dto.ClothingDTO;
import cn.edu.lingnan.dto.FlowSheetDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class deleteFlowsheetServlet extends HttpServlet {
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String flowid = request.getParameter("flowid");
        String payway = request.getParameter("payway");
        //System.out.println("payway"+payway);
        payway = new String(payway.getBytes("iso-8859-1"),"GB18030");
        boolean aaaa = FlowSheetDAO.DeleteFlowSheetMessage(flowid,payway);
        //System.out.println("payway"+payway);

        if (aaaa) {
            Vector<FlowSheetDTO> AllFlowsheet= FlowSheetDAO.findAllFlowsheet();
            //System.out.println(AllFlowsheet.size());
            request.setCharacterEncoding("GB18030");
            session.setAttribute("AllFlowsheet",AllFlowsheet);
            //System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath()+"/admin/flowsheetmain.jsp");
        } else {
            response.getWriter().print("<script>alert(\"系统出错，请再次操作!\");window.location.href='/admin/flowsheetmain.jsp'</script>");

            // response.sendRedirect(request.getContextPath()+ "error/errorSystem.html");
        }
    }

}

