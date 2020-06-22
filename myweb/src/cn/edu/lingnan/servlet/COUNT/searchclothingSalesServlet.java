package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.SalesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * @author admin on 2020-02-29.
 * @version 1.0
 */

public class searchclothingSalesServlet extends HttpServlet

{
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        HttpSession session = request.getSession();
        String month=request.getParameter("month");
        String day=request.getParameter("day");
        String dete=request.getParameter("year");
        if(month.length()==1)
        {
            month="0"+month;
        }
        if(day.length()==1)
        {
            day="0"+day;
        }
        if(month != null && month != "")
            dete = dete +"-"+month;
        if(day != null && day != "")
            dete = dete +"-"+day;
        String clothingidF=request.getParameter("clothingidF");
        String userid=request.getParameter("useridF");
        Vector<SalesDTO> AllSalesD= SalesDAO.SearchSalesForClothingSales(dete,userid,clothingidF,null);
        if(AllSalesD.size()==0)
        {
            response.getWriter().print( "<script>alert(\"没有符合条件的信息！\");window.location.href='/admin/clothingSalesDetail.jsp'</script>");

        }
        else{
            Comparator<SalesDTO> cmp =new SalesDTOComparator();
            Collections.sort(AllSalesD, cmp);
            Vector<DepotDTO> sumSales =new  Vector<DepotDTO>();
            String clothingid = "";
            for(int i = 0,j = 0 ; i < AllSalesD.size(); i++){
                if(sumSales.size() == 0){
                    DepotDTO d = new DepotDTO();
                    if(AllSalesD.get(i).getDisprice() < 0)
                    {
                        d.setNumbers(0-AllSalesD.get(i).getNumbers());
                    }
                    else d.setNumbers(AllSalesD.get(i).getNumbers());
                    d.setClothingid(AllSalesD.get(i).getClothingid());
                    sumSales.add(d);
                    clothingid = AllSalesD.get(i).getClothingid();
                }else if(clothingid.equals(AllSalesD.get(i).getClothingid()) ){
                    if(AllSalesD.get(i).getDisprice() <0){
                        sumSales.get(j).setNumbers(sumSales.get(j).getNumbers()-AllSalesD.get(i).getNumbers());
                    }
                    else sumSales.get(j).setNumbers(AllSalesD.get(i).getNumbers()+sumSales.get(j).getNumbers());
                }else {
                    j++;
                    DepotDTO d = new DepotDTO();
                    clothingid = AllSalesD.get(i).getClothingid();
                    if(AllSalesD.get(i).getDisprice() < 0)
                    {
                        d.setNumbers(0-AllSalesD.get(i).getNumbers());
                    }
                    else d.setNumbers(AllSalesD.get(i).getNumbers());
                    d.setClothingid(AllSalesD.get(i).getClothingid());
                    sumSales.add(d);
                }
            }
            Comparator<DepotDTO> cmp2 =new DepotDTOComparator();
            Collections.sort(sumSales, cmp2);
            request.setCharacterEncoding("GB18030");
            session.setAttribute("sumSales", sumSales);
            // System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/admin/clothingSalesDetail.jsp");

        }


    }

}
