package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.StockTransferDAO;
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
public class searchStockTransferDetailServlet extends HttpServlet {
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
        String userid = session.getAttribute("userid").toString();
        String in_userid = request.getParameter("in_useridF");
        String out_userid = request.getParameter("out_useridF");
        String statusF = request.getParameter("statusF");
        int status;
        if (statusF.equals("已入库")) status = 1;
        else if (statusF.equals("已出库")) status = 0;
        else if  (statusF.equals("异常")) status = 2;
        else status = -1;
        String id = request.getParameter("idF");
        Vector<StockTransferDTO> AllStockTransfer = StockTransferDAO.findAllStockTransfer(userid, in_userid, out_userid, id,status);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllStockTransfer", AllStockTransfer);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/admin/stockTransferDetail.jsp");
    }
}