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

public class deleteUserServlet extends HttpServlet {
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
        String userid=request.getParameter("useridDelete");
       // System.out.println(userid+"0000");
        // System.out.println(password+"1111");
        boolean aaaa= UserDAO.DeleteUserMessage(userid);
        if(aaaa)
        {
            Vector<UserDTO> AllUser=UserDAO.findAllUser();
            //System.out.println(AllUser.size());
            HttpSession session = request.getSession();
            session.setAttribute("AllUser",AllUser);
            response.getWriter().print( "<script>alert(\"删除成功！\");window.location.href='../admin/adminmain.jsp'</script>");
        }
        else{
            response.getWriter().print( "<script>alert(\"所删除用户存在关联信息，不允许删除!\");window.location.href='../admin/adminmain.jsp'</script>");
            }


    }

}

