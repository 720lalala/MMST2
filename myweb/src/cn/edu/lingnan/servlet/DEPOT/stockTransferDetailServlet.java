package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dao.StockTransferDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;
import cn.edu.lingnan.dto.StockTransferDTO;

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
public class stockTransferDetailServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userid = session.getAttribute("userid").toString();
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<StockTransferDTO> AllStockTransfer = StockTransferDAO.findAllStockTransfer(userid,null,null,null,-1);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllStockTransfer", AllStockTransfer);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/admin/stockTransferDetail.jsp");
    }
}