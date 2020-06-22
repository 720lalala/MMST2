package cn.edu.lingnan.servlet.STAFF;

import cn.edu.lingnan.dao.StaffDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.StaffDTO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class insertStaffServlet extends HttpServlet {
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
        //request.setCharacterEncoding("GB18030");
        String userid=request.getParameter("useridI");
        String staffid=userid+request.getParameter("staffidI");
        String staffname=request.getParameter("staffnameI");
        //staffname = new String(staffname.getBytes("iso-8859-1"),"GB18030");
        String authority=session.getAttribute("authority").toString();
        //System.out.println(userid+"0000");
        //System.out.println(authority+"1111");
        StaffDTO tempstaff=new StaffDTO();
        tempstaff.setUserid(userid);
        tempstaff.setStaffid(staffid);
        tempstaff.setStaffname(staffname);
        boolean bool=UserDAO.searchOneUser(userid);
        if(bool){
            bool= StaffDAO.InsertStaffMessage(tempstaff);
            if(bool)
            {

                Vector<StaffDTO> Allstaff= StaffDAO.findAllStaff(userid,authority);
                request.setCharacterEncoding("GB18030");
                session.setAttribute("Allstaff",Allstaff);
                response.getWriter().print( "<script>alert(\"添加数据成功!\");window.location.href='/allCanAccept/staffmain.jsp'</script>");
            }
            else {
               // System.out.println("??");
                response.getWriter().print( "<script>alert(\"请确认输入的数据是否存在过!\");window.location.href='/allCanAccept/staffmain.jsp'</script>");
                //response.getWriter().print("<script> alert(\"请确认输入的店铺id是否存在!\"); </script>");
                //response.sendRedirect(request.getContextPath()+ "/error/errorInput.html");
            }

        }
        else{
            //System.out.println("??");
            response.getWriter().print( "<script>alert(\"请确认输入的店铺编号是否存在!\");window.location.href='/allCanAccept/staffmain.jsp'</script>");
           // response.getWriter().print("<script language='javascript'> alert(\"请确认输入的用户是否存在!\"); </script>");
          //  response.sendRedirect(request.getContextPath()+ "/error/errorInput.html");
        }

    }

}


