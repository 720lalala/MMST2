package cn.edu.lingnan.servlet.CLOTHING;
import cn.edu.lingnan.dao.ClothingDAO;
import cn.edu.lingnan.dto.ClothingDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class searchClothingServlet extends HttpServlet {
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
//        request.setCharacterEncoding("GB18030");
        String SearchTemp=request.getParameter("SearchTemp");
//        SearchTemp = new String(SearchTemp.getBytes("iso-8859-1"),"GB18030");

        // System.out.println(password+"1111");
        Vector<ClothingDTO> aaaa= ClothingDAO.SearchClothingMessage(SearchTemp);
        if(aaaa.size()!=0)
        {
//            System.out.println(aaaa.size());
            HttpSession session = request.getSession();
            session.setAttribute("AllClothing",aaaa);
            response.sendRedirect(request.getContextPath()+"/admin/clothingmain.jsp");
        }
        else{

            response.getWriter().print( "<script>alert(\"请确认输入的值是否存在!\");window.location.href='/admin/clothingmain.jsp'</script>");
            //   response.sendRedirect(request.getContextPath()+"/admin/usermain.jsp");

        }


    }

}