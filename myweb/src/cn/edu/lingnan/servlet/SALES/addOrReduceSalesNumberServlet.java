package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

public class addOrReduceSalesNumberServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        // System.out.println("6666");
        String userid=session.getAttribute("salesUserid").toString();
        Vector<DepotDetailsDTO> salesDetails=( Vector<DepotDetailsDTO>)session.getAttribute("salesDetails");
        int number=Integer.parseInt(request.getParameter("numbers"));
        String clothingid=request.getParameter("clothingid");
        //System.out.println("cl:"+clothingid);
        String action=request.getParameter("action");
        String sales1=session.getAttribute("salesUserid").toString();
        String slaes2=session.getAttribute("userid").toString();
        int i=0;
        //System.out.println("xxx"+salesDetails.size());
        while(salesDetails.size()>i)
        {
            if(salesDetails.get(i).getClothingid().equals(clothingid))
            {
               System.out.println("jinru?");
                break;
            }

            i++;
        }
        if(salesDetails.size()==i)
        {
            //System.out.println("系统出错!");
            if(sales1.equals(slaes2))
            {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/sales.jsp'</script>");
            }
            else
            {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/admin/allSalesmain.jsp'</script>");
            }
        }
        if(action.compareTo("-")==0)
        {

            salesDetails.get(i).setNumbers(number-1);
            if(salesDetails.get(i).getNumbers()<=0)
                salesDetails.remove(i);
            request.setCharacterEncoding("GB18030");
            //System.out.println(salesDetails.size()+"???");
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
            boolean bool= DepotDAO.JudgeInsert(salesDetails,clothingid,userid);
            if(bool)
            {
                salesDetails.get(i).setNumbers(salesDetails.get(i).getNumbers()+1);
                request.setCharacterEncoding("GB18030");
                //System.out.println(salesDetails.size()+"???");
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
            else
            {
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
}

