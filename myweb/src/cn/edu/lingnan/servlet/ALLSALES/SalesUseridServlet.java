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

public class SalesUseridServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("GB18030");
        Vector<DepotDetailsDTO> salesDetails=new Vector<DepotDetailsDTO>();
        session.setAttribute("salesDetails",salesDetails);
        String userid=request.getParameter("salesUserid");
        session.setAttribute("salesUserid",userid);
        String FutureFlowid= FlowSheetDAO.searchFutureFlowid(userid);
        if(FutureFlowid.length()!=15)
        {
            response.getWriter().print( "<script>alert(\"请确认输入的店铺编号是否有存在!\");window.location.href='/admin/allSalesmain.jsp'</script>");
        }
        else {
            session.setAttribute("FutureFlowid",FutureFlowid);
            salesDetails=new Vector<DepotDetailsDTO>();
            response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
        }
    }
}