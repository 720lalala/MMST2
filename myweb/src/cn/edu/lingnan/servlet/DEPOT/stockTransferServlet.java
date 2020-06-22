package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
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
public class stockTransferServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        request.setCharacterEncoding("GB18030");
        session.setAttribute("stockOutDetails", new Vector<DepotDTO>());
        session.setAttribute("stockInDetails", new Vector<DepotDTO>());
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/allCanAccept/stockTransfer.jsp");
    }
}
