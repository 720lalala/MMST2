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

public class modifyUserServlet extends HttpServlet {
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
        String userid=request.getParameter("useridL");
        String password=request.getParameter("passwordF");
        String username=request.getParameter("usernameF");
        //System.out.println(userid+"0000");
       // System.out.println(username+"1111");
       boolean bool= UserDAO.ChangeUserMessage(userid,password,username);
       if(bool){

           Vector<UserDTO> AllUser=UserDAO.findAllUser();
          // System.out.println(AllUser.size());
           HttpSession session = request.getSession();
           session.setAttribute("AllUser",AllUser);
           response.getWriter().print( "<script>alert(\"修改成功！\");window.location.href='adminmain.jsp'</script>"); }
       else{
           response.getWriter().print( "<script>alert(\"输入的值出现错误，请重新操作!\");window.location.href='adminmain.jsp'</script>");
       }

    }

}
