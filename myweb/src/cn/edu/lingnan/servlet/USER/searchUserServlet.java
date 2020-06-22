package cn.edu.lingnan.servlet.USER;

import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class searchUserServlet extends HttpServlet {
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
        String SearchTemp=request.getParameter("SearchTemp");
        // System.out.println(password+"1111");
        Vector<UserDTO> aaaa= UserDAO.SearchUserMessage(SearchTemp);
        if(aaaa.size()!=0)
        {
            //System.out.println(aaaa.size());
            HttpSession session = request.getSession();
            session.setAttribute("AllUser",aaaa);
            response.sendRedirect(request.getContextPath()+"adminmain.jsp");
        }
        else{

            response.getWriter().print( "<script>alert(\"请确认输入的值是否存在!\");window.location.href='adminmain.jsp'</script>");
         //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}