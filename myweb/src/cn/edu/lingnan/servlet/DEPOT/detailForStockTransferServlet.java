package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.StockTransferDAO;
import cn.edu.lingnan.dto.DepotDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-04-09.
 * @version 1.0
 */
public class detailForStockTransferServlet extends HttpServlet

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
        String id = request.getParameter("id");
        Vector<DepotDTO>  StockTransferDetail= StockTransferDAO.findStockTransferDetail(id);
        if (StockTransferDetail.size() != 0) {
            request.setCharacterEncoding("GB18030");
            session.setAttribute("StockTransferDetail", StockTransferDetail);
//            System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/admin/stockTransferDetail.jsp");
        } else {

            response.getWriter().print("<script>alert(\"系统错误!\");window.location.href='/admin/stockTransferDetail.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}
