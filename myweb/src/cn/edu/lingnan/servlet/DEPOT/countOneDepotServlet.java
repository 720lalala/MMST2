package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-02-28.
 * @version 1.0
 */
public class countOneDepotServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String clothingid = request.getParameter("SearchTemp");
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<DepotDetailsDTO> AllDepotD = (Vector<DepotDetailsDTO>)session.getAttribute("CountAllDepotD");
        int sumpiece = Integer.valueOf(session.getAttribute("sumpiece").toString());
        int i = 0;
        int size = AllDepotD.size();
        for(; i < size; i++){
            if(AllDepotD.get(i).getClothingid().equals(clothingid)  ){
                if(AllDepotD.get(i).getNumbers() == 1){
                    AllDepotD.remove(i);
                }else {
                    AllDepotD.get(i).setNumbers(AllDepotD.get(i).getNumbers()-1);
                }
                break;
            }
        }
        if(i < size){
            request.setCharacterEncoding("GB18030");
            session.setAttribute("CountAllDepotD", AllDepotD);
            session.setAttribute("sumpiece", sumpiece-1);

            // System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/allCanAccept/countDepot.jsp");
        }
        else {
            response.getWriter().print( "<script>alert(\"没有该衣服编号!\");window.location.href='/allCanAccept/countDepot.jsp'</script>");

        }
    }
}
