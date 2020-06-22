package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.StaffDAO;
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
public class insertReturnSalesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Vector<DepotDetailsDTO> salesDetails = (Vector<DepotDetailsDTO>) session.getAttribute("salesDetails");
        String staffid =request.getParameter("staffidI");
        String userid=session.getAttribute("salesUserid").toString();
        int i = 0;
       if(StaffDAO.SearchStaffOne(staffid,userid))
        {
            while (salesDetails.size()>i) {
                salesDetails.get(i).setStaffid(staffid);
                i++;
            }

            request.setCharacterEncoding("GB18030");
            session.setAttribute("salesDetails",salesDetails);
            session.setAttribute("staffid",staffid);
            response.sendRedirect(request.getContextPath()+"/allCanAccept/returnsPolicy.jsp");
        }
        else {
            response.getWriter().print( "<script>alert(\"没有该员工!\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");

       }

    }

}
