package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.FlowSheetDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author admin on 2020-04-07.
 * @version 1.0
 */
public class returnFlowidServlet extends HttpServlet

{

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String flowid = request.getParameter("returnNumF");

        if(FlowSheetDAO.SearchFlowsheet(flowid))
        {
            session.setAttribute("flowid_return", flowid);
            response.getWriter().print( "<script>alert(\"退货单号确定\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");

        }
        else
        {
            response.getWriter().print( "<script>alert(\"没有此流水单号\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");
        }
    }
}