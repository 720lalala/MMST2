package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.StockTransferDAO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-04-08.
 * @version 1.0
 */
public class idStockInServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
        String id = request.getParameter("idTemp");
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<DepotDTO> AllDepotD = StockTransferDAO.findStockTransferDetails(userid,id);
        if(AllDepotD.size()==0){
            response.getWriter().print( "<script>alert(\"没有该单号!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");

        }
        else {
            request.setCharacterEncoding("GB18030");
            session.setAttribute("stockInDetails", AllDepotD);
            session.setAttribute("idTemp", id);
            // System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/allCanAccept/stockTransfer.jsp");
        }
        }

}
