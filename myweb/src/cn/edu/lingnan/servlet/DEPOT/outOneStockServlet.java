package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.DepotDetailsDTO;
import cn.edu.lingnan.dto.SalesDTO;

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
public class outOneStockServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String userid=session.getAttribute("userid").toString();
        String clothingid=request.getParameter("OutOneStockTemp");
        Vector<DepotDTO> salesDetails=( Vector<DepotDTO>)session.getAttribute("stockOutDetails");
        if(salesDetails==null){
            salesDetails = new Vector<DepotDTO>();
        }
        boolean bool= DepotDAO.JudgeInsertForStock(salesDetails,clothingid,userid);
        if(bool)
        {
            int i=0;
            DepotDTO aa=new DepotDTO();
            while (salesDetails.size()>i)
            {
                //System.out.println("进来了？1");
                aa=salesDetails.get(i);
                if(aa.getClothingid().equals(clothingid))
                {
                    break;
                }
                i++;

            }
            if(i!=salesDetails.size())
            {
                //System.out.println("进来了？2");
                salesDetails.get(i).setNumbers(salesDetails.get(i).getNumbers()+1);
            }
            else {
                DepotDTO aaa=new DepotDTO();
                aaa.setClothingid(clothingid);
                aaa.setNumbers(1);
                salesDetails.add(aaa);
            }
            request.setCharacterEncoding("GB18030");
            session.setAttribute("salesDetails",salesDetails);
            response.sendRedirect(request.getContextPath()+"/allCanAccept/stockTransfer.jsp");

        }
        else {
            response.getWriter().print( "<script>alert(\"请确认输入的衣服编号是否有库存!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");

        }

    }
}
