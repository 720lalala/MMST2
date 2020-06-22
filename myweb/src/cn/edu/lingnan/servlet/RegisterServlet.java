package cn.edu.lingnan.servlet;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet{
    @Override
    public void init()throws ServletException{}
    @Override
    public void doPost(HttpServletRequest request,HttpServletResponse response)
        throws ServletException,IOException{
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        request.setCharacterEncoding("GB18030");
        String userid=request.getParameter("userid");
        String password = request.getParameter("password");
        String username = request.getParameter("provinces")+request.getParameter("remarksInformation");
        String authority = request.getParameter("optionsRadios");

        //验证是否获得正确信息
        //System.out.println(userid+" "+password+" "+username+" "+authority);
        UserDTO user=new UserDTO();
        user.setUserid(userid);
        user.setPassword(password);
        user.setUsername(username);
        user.setAuthority(authority);
        boolean aaaa= UserDAO.register(user);
        if(aaaa)
        {
            response.getWriter().print("<script> alert(\"已提交申请，等待管理员审核！\"); window.location.href='login.jsp'</script>");
        }
        else
        {
            response.getWriter().print("<script> alert(\"已有人申请此账号，请重新申请！\"); window.location.href='register.jsp'</script>");
        }

    }
}
