package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class SalesOneServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        //System.out.println("6666");
        String userid=session.getAttribute("salesUserid").toString();
        String clothingid=request.getParameter("clothingidF");
        //System.out.println(clothingid);
        Vector<DepotDetailsDTO> salesDetails=( Vector<DepotDetailsDTO>)session.getAttribute("salesDetails");
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
        boolean bool= DepotDAO.JudgeInsert(salesDetails,clothingid,userid);
        if(bool)
        {
            int i=0;
            DepotDetailsDTO aa=new DepotDetailsDTO();
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
                    System.out.println("进来了？3");
//                    for(i=0;i<salesDetails.size();i++)
//                    {
//                        //System.out.println(salesDetails.get(i).getClothingid()+" "+salesDetails.get(i).getNumbers());
//                    }
                    DepotDetailsDTO aaa=DepotDAO.SearchOneDepotDetail(clothingid,userid);
                   System.out.println("aaa.c"+aaa.getClothingid());
                    aaa.setNumbers(1);
                    salesDetails.add(aaa);
                    System.out.println(salesDetails.size());
                }
            request.setCharacterEncoding("GB18030");
            //System.out.println(salesDetails.size()+"???");
//            for(i=0;i<salesDetails.size();i++)
//            {
//                System.out.println(salesDetails.get(i).getClothingid()+" "+salesDetails.get(i).getNumbers());
//            }
            session.setAttribute("salesDetails",salesDetails);
            if(sales1.equals(slaes2))
            {
                response.sendRedirect(request.getContextPath()+"/allCanAccept/sales.jsp");
            }
            else
            {
                response.sendRedirect(request.getContextPath()+"/admin/allSalesmain.jsp");
            }
        }
        else {
           // System.out.println("进来了？5");
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"请确认输入的衣服编号是否有库存!\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"请确认输入的衣服编号是否有库存!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }
        }

    }
}