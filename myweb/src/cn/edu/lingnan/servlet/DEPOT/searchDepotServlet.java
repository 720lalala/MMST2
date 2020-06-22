package cn.edu.lingnan.servlet.DEPOT;
import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class searchDepotServlet extends HttpServlet {
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        String authority=session.getAttribute("authority").toString();
        String userid=session.getAttribute("userid").toString();
        String SearchTemp=request.getParameter("SearchTemp");
//        SearchTemp = new String(SearchTemp.getBytes("iso-8859-1"),"GB18030");
        Vector<DepotDetailsDTO> AllDepotD= DepotDAO.SearchDepotMessage(SearchTemp,authority,userid);
        if(AllDepotD.size()!=0)
        {
            //System.out.println(AllDepotD.size());
            session.setAttribute("AllDepotD", AllDepotD);
            // System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/allCanAccept/depotmain.jsp");
        }
        else{

            response.getWriter().print( "<script>alert(\"请确认输入的值是否存在!(只能输入店铺编号、衣服数量、衣服)\");window.location.href='/allCanAccept/depotmain.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}
