package cn.edu.lingnan.servlet.SALES;

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
public class changeScoreServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        int scoreF=Integer.valueOf(request.getParameter("scoreF"));
        int vipScore =0 ;
        try {
            vipScore = Integer.valueOf(session.getAttribute("vipScore").toString());
        }catch (Exception e){

        }
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
        if(vipScore < scoreF ||scoreF < 0){
            if(sales1.equals(slaes2))
            { response.getWriter().print( "<script>alert(\"扣除积分多于原有积分或扣除积分少于0\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"扣除积分多于原有积分或扣除积分少于0!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }
        }else{
            if(sales1.equals(slaes2))
            {
                //response.sendRedirect(request.getContextPath()+"/allCanAccept/sales.jsp");
                response.getWriter().print( "<script>window.history.go(-1);</script>");

            }
            else
            {
                //response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
                response.getWriter().print( "<script>window.history.go(-1);</script>");
            }
        }



    }
}
