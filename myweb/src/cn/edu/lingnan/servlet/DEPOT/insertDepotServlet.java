package cn.edu.lingnan.servlet.DEPOT;
import cn.edu.lingnan.dao.ClothingDAO;
import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class insertDepotServlet extends HttpServlet {
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
        String userid=request.getParameter("useridI");
        String clothingid=request.getParameter("clothingidI");
       // System.out.println(clothingid);
        int numberss=Integer.valueOf(request.getParameter("numberssI"));
        String authority=session.getAttribute("authority").toString();
        boolean bool= UserDAO.searchOneUser(userid);
        if(bool){
            bool= ClothingDAO.searchOneClothing(clothingid);
            if(bool)
            {
                bool=DepotDAO.InsertDepotMessage(clothingid,userid,numberss);
                if(bool)
                {
                    Vector<DepotDetailsDTO> AllDepotD= DepotDAO.findAllDepot(userid,authority);
                    //System.out.println(AllDepotD.size());
                    request.setCharacterEncoding("GB18030");
                    session.setAttribute("AllDepotD", AllDepotD);
                    // System.out.println(request.getContextPath());
                    response.getWriter().print( "<script>alert(\"记录成功!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");


                }
                else{
                    response.getWriter().print( "<script>alert(\"请确认输入的记录是否存在过!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");

                }
            }
            else {
                response.getWriter().print( "<script>alert(\"请确认输入的衣服编号是否存在!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");
                 }

        }
        else{
            //System.out.println("??");
            response.getWriter().print( "<script>alert(\"请确认输入的店铺编号是否存在!\");window.location.href='/allCanAccept/depotmain.jsp'</script>");
         }

    }

}



