package cn.edu.lingnan.servlet.VIP;

import cn.edu.lingnan.dao.VipDAO;
import cn.edu.lingnan.dto.VipDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-02-26.
 * @version 1.0
 */
public class changeVipServlet extends HttpServlet {
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
        // request.setCharacterEncoding("GB18030");
        String phone=request.getParameter("phoneF");
        String name=request.getParameter("nameF");
//        System.out.println(propertyFF);
        // propertyFF = new String(propertyFF.getBytes("iso-8859-1"),"GB18030");
//        System.out.println(propertyFF);
            boolean bool= VipDAO.ChangeVipMessage(phone,name);
        if(bool){
            String authority = session.getAttribute("authority").toString();
            Vector<VipDTO> AllVip= VipDAO.findAllVip(authority);
            //System.out.println(AllUser.size());
            session.setAttribute("AllVip",AllVip);
            response.setCharacterEncoding("GB18030");
            response.getWriter().print( "<script>alert(\"修改成功!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");
        }
        else{
            response.getWriter().print( "<script>alert(\"请确认vip姓名是否存在!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");

        }



    }

}
