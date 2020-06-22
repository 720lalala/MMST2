package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-04-07.
 * @version 1.0
 */
public class deleteReturnSalesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Vector<DepotDetailsDTO> salesDetails = (Vector<DepotDetailsDTO>) session.getAttribute("salesDetails");
        String clothingid = request.getParameter("clothingid");
        int i = 0;
        //System.out.println("xxx"+salesDetails.size());
        while (salesDetails.size() > i) {
            if (salesDetails.get(i).getClothingid().equals(clothingid)) {

                break;
            }
            i++;
        }
        if (salesDetails.size() == i) {
            // System.out.println("系统出错!");
            response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");
            }
        else {
            salesDetails.remove(i);
            request.setCharacterEncoding("GB18030");
            //System.out.println(salesDetails.size()+"???");
            session.setAttribute("salesDetails",salesDetails);
            response.sendRedirect(request.getContextPath()+"/allCanAccept/returnsPolicy.jsp");
           }
    }
}