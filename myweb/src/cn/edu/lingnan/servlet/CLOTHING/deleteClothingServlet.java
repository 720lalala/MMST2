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

public class deleteClothingServlet extends HttpServlet {
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String clothingid = request.getParameter("clothingid");
       // System.out.println(clothingid + "0000");
        // System.out.println(password+"1111");
        boolean aaaa = ClothingDAO.DeleteClothingMessage(clothingid);
        if (aaaa) {
            Vector<ClothingDTO> AllClothing = ClothingDAO.findAllClothing();
            request.setCharacterEncoding("GB18030");
            session.setAttribute("AllClothing", AllClothing);
//            System.out.println(request.getContextPath());
            response.getWriter().print("<script>alert(\"删除成功!\");window.location.href='/admin/clothingmain.jsp'</script>");
        } else {
            response.getWriter().print("<script>alert(\"该衣服信息还存在关联信息，不能删除!\");window.location.href='/admin/clothingmain.jsp'</script>");

            // response.sendRedirect(request.getContextPath()+ "error/errorSystem.html");
        }
    }

}

