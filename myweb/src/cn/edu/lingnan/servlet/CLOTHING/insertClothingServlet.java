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
public class insertClothingServlet extends HttpServlet {
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
        String startF=request.getParameter("startF");
        String bianhaoF=request.getParameter("bianhaoF");
        String property=request.getParameter("propertyF");
        String color=request.getParameter("colorF");
       // property= new String(property.getBytes("iso-8859-1"),"GB18030");
       // color= new String(color.getBytes("iso-8859-1"),"GB18030");
//        System.out.println("clololoo:"+color);
        String size=request.getParameter("sizeF");
        String price=request.getParameter("priceF");
        String discount=request.getParameter("discountF");
        String clothindid=startF+bianhaoF+color.substring(0,3)+size.substring(0,2);
//        System.out.println(clothindid+"0000");
        color=color.substring(4);
//        System.out.println(color+"1111");
        size=size.substring(0,2);
//        System.out.println(size+"2222");
//        System.out.println(price+"3333");
        boolean bool= ClothingDAO.insertClothing(clothindid,property,color,size,price,discount);
        if(bool){

            Vector<ClothingDTO> AllClothing=ClothingDAO.findAllClothing();
            //System.out.println(AllUser.size());
            HttpSession session = request.getSession();
            session.setAttribute("AllClothing",AllClothing);

            response.getWriter().print( "<script>alert(\"添加衣服信息成功!\");window.location.href='/admin/clothingmain.jsp'</script>");

        }
        else{
            response.getWriter().print( "<script>alert(\"请确认输入的衣服编号是否存在!\");window.location.href='/admin/clothingmain.jsp'</script>");

        }

    }

}

