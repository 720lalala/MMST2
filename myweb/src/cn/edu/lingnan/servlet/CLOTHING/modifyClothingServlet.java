package cn.edu.lingnan.servlet.CLOTHING;

import cn.edu.lingnan.dao.ClothingDAO;
import cn.edu.lingnan.dao.StaffDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.ClothingDTO;
import cn.edu.lingnan.dto.StaffDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class modifyClothingServlet extends HttpServlet {
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
       // request.setCharacterEncoding("GB18030");
        String clothingid=request.getParameter("clothingidFF");
        String propertyFF=request.getParameter("propertyFF");
        String priceFF=request.getParameter("priceFF");
        String discountFF=request.getParameter("discountFF");
//        System.out.println(propertyFF);
       // propertyFF = new String(propertyFF.getBytes("iso-8859-1"),"GB18030");
//        System.out.println(propertyFF);
        boolean bool= ClothingDAO.ChangeClothingMessage(clothingid,propertyFF,priceFF,discountFF);
        if(bool){
            Vector<ClothingDTO> AllClothing=ClothingDAO.findAllClothing();
            //System.out.println(AllUser.size());
            session.setAttribute("AllClothing",AllClothing);
            response.getWriter().print( "<script>alert(\"修改信息成功!\");window.location.href='/admin/clothingmain.jsp'</script>");
            }
            else{
            response.getWriter().print( "<script>alert(\"请确认衣服编号是否存在!\");window.location.href='/admin/clothingmain.jsp'</script>");

            }



    }

}
