package cn.edu.lingnan.servlet;

import cn.edu.lingnan.dao.UserDAO;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld1 extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        String userid=request.getParameter("userid");
        String password = request.getParameter("password");
        String authority=request.getParameter("optionsRadios");
        //验证是否获得正确信息
        //System.out.println(userid+" "+password+" "+authority);
        boolean aaaa=UserDAO.checklogin(userid,password,authority);

        if(aaaa)
        {
            Cookie cookes=new Cookie("userid", userid);
            cookes.setMaxAge(60*60*5);
            response.addCookie(cookes);
            HttpSession session=request.getSession();
            session.setAttribute("authority",authority);
            session.setAttribute("userid",userid);
            //System.out.println(authority);
                //System.out.println(authority+"5555");
                if(authority.equals("su"))
                {
                //    response.getWriter().print("window.location.href='/admin/AllUserServlet'</script>");

                    response.sendRedirect(request.getContextPath()+"/admin/AllUserServlet");

                }
                else {
                 //   response.getWriter().print("window.location.href='/allCanAccept/salesServlet'</script>");
                    response.sendRedirect(request.getContextPath()+"/allCanAccept/salesServlet");

                }

        }
        else
        {
            response.sendRedirect(request.getContextPath()+"/error/errorlogin.html");
        }

    }
}