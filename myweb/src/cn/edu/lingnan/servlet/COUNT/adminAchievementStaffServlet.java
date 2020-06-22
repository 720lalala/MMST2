package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.Count.achievementStaffDTO;
import cn.edu.lingnan.dto.Count.achievementUserDTO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.FlowSheetDTO;
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
 * @author admin on 2020-03-17.
 * @version 1.0
 */
class SalesDTOComparator3 implements Comparator<SalesDTO> {
    public int compare(SalesDTO a, SalesDTO b) {
        if(a.getStaffid().compareTo(b.getStaffid()) < 0){
            return 1;
        }else if(a.getStaffid().equals(b.getStaffid())){
            return 0;
        }else{
            return -1;
        }
    }
}
class achievementStaffDTOComparator implements Comparator<achievementStaffDTO> {
    public int compare(achievementStaffDTO a, achievementStaffDTO b) {
        if(a.getAchievement() < b.getAchievement()){
            return 1;
        }else if(a.getAchievement() == b.getAchievement()){
            return 0;
        }else{
            return -1;
        }
    }
}
public class adminAchievementStaffServlet extends HttpServlet

{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String startDate = df.format(new Date());
        Vector<SalesDTO> AllSalesD = SalesDAO.SearchSalesForClothingSales(startDate,null,null ,null);
        Comparator<SalesDTO> cmp =new SalesDTOComparator3();
        Collections.sort(AllSalesD, cmp);

        Vector<achievementStaffDTO> sumSales =new  Vector<achievementStaffDTO>();
        String staffids = "";
        for(int i = 0,j = 0 ; i < AllSalesD.size(); i++){
            if(sumSales.size() == 0){
                achievementStaffDTO d = new achievementStaffDTO();
                d.setAchievement(AllSalesD.get(i).getDisprice());
                d.setStaffid(AllSalesD.get(i).getStaffid());
                sumSales.add(d);
                staffids = AllSalesD.get(i).getStaffid();
            }else if(staffids.equals(AllSalesD.get(i).getStaffid()) ){
                sumSales.get(j).setAchievement(AllSalesD.get(i).getDisprice()+sumSales.get(j).getAchievement());
            }else {
                j++;
                achievementStaffDTO d = new achievementStaffDTO();
                staffids = AllSalesD.get(i).getStaffid();
                d.setAchievement(AllSalesD.get(i).getDisprice());
                d.setStaffid(AllSalesD.get(i).getStaffid());
                sumSales.add(d);
            }
        }
        Comparator<achievementStaffDTO> cmp2 =new achievementStaffDTOComparator();
        Collections.sort(sumSales, cmp2);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("achievementStaff", sumSales);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/admin/achievementStaff.jsp");
    }
}
