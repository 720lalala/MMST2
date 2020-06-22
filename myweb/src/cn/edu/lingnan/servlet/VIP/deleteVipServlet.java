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
public class deleteVipServlet extends HttpServlet {
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
        String name=request.getParameter("vipDelete");
        // System.out.println(userid+"0000");
        // System.out.println(password+"1111");
        boolean aaaa= VipDAO.DeleteVipMessage(name);
        if(aaaa)
        {
            HttpSession session = request.getSession();
            String authority = session.getAttribute("authority").toString();
            Vector<VipDTO> AllVip= VipDAO.findAllVip(authority);
            //System.out.println(AllUser.size());
            session.setAttribute("AllVip",AllVip);
            response.getWriter().print( "<script>alert(\"删除成功!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");
        }
        else{
            response.getWriter().print( "<script>alert(\"系统出错，请再次操作!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");
        }


    }

}

