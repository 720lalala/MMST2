package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class deleteSalesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Vector<DepotDetailsDTO> salesDetails = (Vector<DepotDetailsDTO>) session.getAttribute("salesDetails");
        String clothingid = request.getParameter("clothingid");
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
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
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }        }
        else {
            salesDetails.remove(i);
            request.setCharacterEncoding("GB18030");
            //System.out.println(salesDetails.size()+"???");
            session.setAttribute("salesDetails",salesDetails);
            if(sales1.equals(slaes2))
            {
                response.sendRedirect(request.getContextPath()+"/allCanAccept/sales.jsp");
            }
            else
            {
                response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
            }        }
    }
}
