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

public class insertAllSalesServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Vector<DepotDetailsDTO> salesDetails = (Vector<DepotDetailsDTO>) session.getAttribute("salesDetails");
        String staffid =request.getParameter("staffidI");
        String userid=session.getAttribute("salesUserid").toString();
        int i = 0;
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
        //System.out.println("xxx"+salesDetails.size());

        if(StaffDAO.SearchStaffOne(staffid,userid))
        {
            while (salesDetails.size()>i) {
                salesDetails.get(i).setStaffid(staffid);
                i++;
            }

            request.setCharacterEncoding("GB18030");
          //  System.out.println(salesDetails.size()+"??");
            session.setAttribute("salesDetails",salesDetails);
            if(sales1.equals(slaes2))
            {
                response.sendRedirect(request.getContextPath()+"/allCanAccept/sales.jsp");
            }
            else
            {
                response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
            }        }
        else {
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"没有该员工!\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"没有该员工!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }            }

        }

}