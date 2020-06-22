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
 * @author admin on 2020-02-27.
 * @version 1.0
 */
public class searchVipByPhoneServlet extends HttpServlet {
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
        String SearchTemp=request.getParameter("searchVip");
        SearchTemp = new String(SearchTemp.getBytes("iso-8859-1"),"GB18030");
        HttpSession session = request.getSession();
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
        // System.out.println(password+"1111");
        VipDTO aaaa= VipDAO.searchVipByPhone(SearchTemp);
        if(aaaa.getPhone() != null)
        {
            //System.out.println(aaaa.size());
            session.setAttribute("vipPhone",aaaa.getPhone());
            session.setAttribute("vipScore",aaaa.getScore());
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"vip客户信息确定\");window.location.href='/allCanAccept/sales.jsp'</script>");            }
            else
            {
                response.getWriter().print( "<script>alert(\"vip客户信息确定\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }

        }
        else{
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"请确认是否有该vip\");window.location.href='/allCanAccept/sales.jsp'</script>");            }
            else
            {
                response.getWriter().print( "<script>alert(\"请确认是否有该vip\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }

        }


    }
}
