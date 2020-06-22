package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dto.DepotDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-04-08.
 * @version 1.0
 */
public class inOneStockServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String clothingid = request.getParameter("clothingTemp");
        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        Vector<DepotDTO> AllDepotD = (Vector<DepotDTO>)session.getAttribute("stockInDetails");
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
            session.setAttribute("stockInDetails", AllDepotD);

            // System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/allCanAccept/stockTransfer.jsp");
        }
        else {
            response.getWriter().print( "<script>alert(\"出入库单号没有该衣服编号信息!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");

        }
    }
}
