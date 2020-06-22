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
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Vector;

/**
 * @author admin on 2020-02-29.
 * @version 1.0
 */
class DepotDTOComparator implements Comparator<DepotDTO> {
    public int compare(DepotDTO a, DepotDTO b) {
        if(a.getNumbers() < b.getNumbers()){
            return 1;
        }else if(a.getNumbers() == b.getNumbers()){
            return 0;
        }else{
            return -1;
        }
    }
}
 class SalesDTOComparator implements Comparator<SalesDTO> {
    public int compare(SalesDTO a, SalesDTO b) {
        if(a.getClothingid().compareTo(b.getClothingid()) < 0){
            return 1;
        }else if(a.getClothingid().equals(b.getClothingid())){
            return 0;
        }else{
            return -1;
        }
    }
}
public class clothingSalesServlet extends HttpServlet

    {
        @Override
        public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        // System.out.println("进来了？");
        HttpSession session = request.getSession();
        String authority = session.getAttribute("authority").toString();
        String userid = session.getAttribute("userid").toString();

        //System.out.println("6666");
        //System.out.println(authority+"  "+userid);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String startDate = df.format(new Date());
        Vector<SalesDTO> AllSalesD = SalesDAO.SearchSalesForClothingSales(startDate,null,null ,null);
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
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("sumSales", sumSales);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/admin/clothingSalesDetail.jsp");
    }
}
