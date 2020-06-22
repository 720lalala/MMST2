package cn.edu.lingnan.servlet.CLOTHING;

import cn.edu.lingnan.dao.ApplyuserDAO;
import cn.edu.lingnan.dao.ClothingDAO;
import cn.edu.lingnan.dto.ClothingDTO;
import cn.edu.lingnan.dto.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class AllClothiingServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String url=request.getRequestURI().toString();
        HttpSession session = request.getSession();
        // System.out.println("6666");
        Vector<ClothingDTO> AllClothing= ClothingDAO.findAllClothing();

        //System.out.println(AllClothing.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("AllClothing",AllClothing);
        //System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath()+"clothingmain.jsp");
    }
}
