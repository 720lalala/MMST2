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

public class modifyDepotServlet extends HttpServlet {
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
        request.setCharacterEncoding("GB18030");
        String userid=request.getParameter("useridF");
        String clothingid=request.getParameter("clothingidF");
        //System.out.println(clothingid);
        String numberss=request.getParameter("numberssF");
        String authority=session.getAttribute("authority").toString();
        boolean bool= DepotDAO.ChangeDepotMessage(clothingid,userid,numberss);
        if(bool){
            Vector<DepotDetailsDTO> AllDepotD= DepotDAO.findAllDepot(userid,authority);
            //System.out.println(AllDepotD.size());
            request.setCharacterEncoding("GB18030");
            session.setAttribute("AllDepotD", AllDepotD);
            // System.out.println(request.getContextPath());
            response.getWriter().print( "<script>alert(\"修改信息成功!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");
            }
        else{
            response.getWriter().print( "<script>alert(\"请确认输入的数据是否已经存在!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");
        }

    }

}
