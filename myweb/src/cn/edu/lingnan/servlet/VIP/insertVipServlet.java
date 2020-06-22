package cn.edu.lingnan.servlet.VIP;

import cn.edu.lingnan.dao.VipDAO;
import cn.edu.lingnan.dto.VipDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

/**
 * @author admin on 2020-02-26.
 * @version 1.0
 */
public class insertVipServlet extends HttpServlet {
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
    { request.setCharacterEncoding("GB18030");
        String phone=request.getParameter("phoneI");
        String username=request.getParameter("usernameI");
        VipDTO tempvip=new VipDTO();
        tempvip.setPhone(phone);
        tempvip.setName(username);
        Calendar cal = Calendar.getInstance();
        tempvip.setDate(Integer.toString(cal.get(Calendar.YEAR)));
        boolean bool= VipDAO.insertVip(tempvip);
        if(bool){
            HttpSession session = request.getSession();
            String authority = session.getAttribute("authority").toString();
            Vector<VipDTO> AllVip= VipDAO.findAllVip(authority);
            //System.out.println(AllUser.size());
            session.setAttribute("AllVip",AllVip);
            response.getWriter().print( "<script>alert(\"添加成功!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");
        }
        else{
            response.getWriter().print( "<script>alert(\"请确认该vip存在!\");window.location.href='/allCanAccept/aUserVipmain.jsp'</script>");

        }

    }

}
