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

public class changeSalesStaffidServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // System.out.println("6666");
        String userid=session.getAttribute("salesUserid").toString();
        String staffid=request.getParameter("staffid");
        staffid=staffid.replace(" ", "");
        String clothingid=request.getParameter("clothingid");
        //System.out.println(staffid+"0001");
        //System.out.println(clothingid+"0001");
        Vector<DepotDetailsDTO> salesDetails = (Vector<DepotDetailsDTO>) session.getAttribute("salesDetails");
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
            //System.out.println("系统出错!");
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }
        }
        else {
            if(StaffDAO.SearchStaffOne(staffid,userid))
            {
                salesDetails.get(i).setStaffid(staffid);
                request.setCharacterEncoding("GB18030");
                //System.out.println(salesDetails.size()+"??");
                session.setAttribute("salesDetails",salesDetails);
                if(sales1.equals(slaes2))
                {
                    response.sendRedirect(request.getContextPath()+"/allCanAccept/sales.jsp");
                }
                else
                {
                    response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
                }
            }
            else {
                if(sales1.equals(slaes2))
                { response.getWriter().print( "<script>alert(\"没有该员工!\");window.location.href='/allCanAccept/sales.jsp'</script>");
                }
                else
                {
                    response.getWriter().print( "<script>alert(\"没有该员工!\");window.location.href='/admin/allSalesmain.jsp'</script>");
                }
            }

        }
    }
}

