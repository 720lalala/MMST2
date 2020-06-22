package cn.edu.lingnan.servlet.FLOWSHEET;

import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.SalesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-02-28.
 * @version 1.0
 */
public class detailFlowsheetServlet extends HttpServlet

{
    @Override
    public void init() throws ServletException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String flowid = request.getParameter("flowid");
        Vector<SalesDTO> AllSalesD = SalesDAO.SearchSomeSalesByFlowid(flowid);
        if (AllSalesD.size() != 0) {
            request.setCharacterEncoding("GB18030");
            session.setAttribute("FlowidDetail", AllSalesD);
//            System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/admin/flowsheetmain.jsp");
        } else {

            response.getWriter().print("<script>alert(\"系统错误!\");window.location.href='/admin/flowsheetmain.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}
