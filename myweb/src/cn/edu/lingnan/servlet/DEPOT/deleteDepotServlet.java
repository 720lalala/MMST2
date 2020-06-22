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

public class deleteDepotServlet extends HttpServlet {
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
        String clothingid=request.getParameter("clothingid");
        String authority=session.getAttribute("authority").toString();
        String userid=request.getParameter("userid");
        boolean aaaa= DepotDAO.DeleteDepotMessage(clothingid,userid);
        if(aaaa)
        {
            Vector<DepotDetailsDTO> AllDepotD= DepotDAO.findAllDepot(userid,authority);
            //System.out.println(AllDepotD.size());
            request.setCharacterEncoding("GB18030");
            session.setAttribute("AllDepotD", AllDepotD);
            // System.out.println(request.getContextPath());
            response.getWriter().print( "<script>alert(\"删除成功!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");
        }
        else{
            response.getWriter().print( "<script>alert(\"系统出错，请再次操作!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");

            // response.sendRedirect(request.getContextPath()+ "error/errorSystem.html");
        }


    }

}


