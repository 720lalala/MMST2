package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author admin on 2020-02-28.
 * @version 1.0
 */
public class startCountDepotServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // System.out.println("6666");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String startDate = df.format(new Date());// new Date()为获取当前系统时间
        request.setCharacterEncoding("GB18030");
        session.setAttribute("startDate",startDate);
        String userid = session.getAttribute("userid").toString();
        Vector<DepotDetailsDTO> AllDepotD = DepotDAO.findAllDepotByAdmin(userid);
        int sumpiece = 0;
        for(int i = 0 ;i<AllDepotD.size(); i++ )
            sumpiece += AllDepotD.get(i).getNumbers();
        session.setAttribute("sumpiece", sumpiece);
        request.setCharacterEncoding("GB18030");
        session.setAttribute("CountAllDepotD", AllDepotD);
//        String vipPhone = null;
//        try {
//            vipPhone = session.getAttribute("vipPhone").toString();
//        }catch (Exception e){}
//        if(vipPhone != null && vipPhone != ""){
//            response.getWriter().print( "<script>alert(\"vip客户信息确定\");window.location.href='/allCanAccept/sales.jsp'</script>");
//        }
        response.sendRedirect(request.getContextPath()+"/allCanAccept/countDepot.jsp");
    }
}
