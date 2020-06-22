package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.Count.achievementUserDTO;
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
class FlowsheetDTOComparator implements Comparator<FlowSheetDTO> {
    public int compare(FlowSheetDTO a, FlowSheetDTO b) {
        if(a.getUserid().compareTo(b.getUserid()) < 0){
            return 1;
        }else if(a.getUserid().equals(b.getUserid())){
            return 0;
        }else{
            return -1;
        }
    }

}
class achievementUserDTOComparator implements Comparator<achievementUserDTO> {
    public int compare(achievementUserDTO a, achievementUserDTO b) {
        if(a.getAchievement() < b.getAchievement()){
            return 1;
        }else if(a.getAchievement() == b.getAchievement()){
            return 0;
        }else{
            return -1;
        }
    }
}
public class adminAchievementUserServlet extends HttpServlet

{
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("进来了？");
        HttpSession session = request.getSession();
        String authority = session.getAttribute("authority").toString();
        String userid = session.getAttribute("userid").toString();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String startDate = df.format(new Date());
        Vector<SalesDTO> AllSalesD= SalesDAO.SearchSalesForClothingSales(startDate,userid,null,null);
        Comparator<SalesDTO> cmp =new SalesDTOComparator2();
        Collections.sort(AllSalesD, cmp);

        Vector<achievementUserDTO> sumSales =new  Vector<achievementUserDTO>();
        String userids = "";
        for(int i = 0,j = 0 ; i < AllSalesD.size(); i++){
            if(sumSales.size() == 0){
                achievementUserDTO d = new achievementUserDTO();
                d.setAchievement(AllSalesD.get(i).getDisprice());
                d.setUserid(AllSalesD.get(i).getUserid());
                sumSales.add(d);
                userids = AllSalesD.get(i).getUserid();
            }else if(userids.equals(AllSalesD.get(i).getUserid()) ){
                sumSales.get(j).setAchievement(AllSalesD.get(i).getDisprice()+sumSales.get(j).getAchievement());
            }else {
                j++;
                achievementUserDTO d = new achievementUserDTO();
                userids = AllSalesD.get(i).getUserid();
                d.setAchievement(AllSalesD.get(i).getDisprice());
                d.setUserid(AllSalesD.get(i).getUserid());
                sumSales.add(d);
            }
        }
        Comparator<achievementUserDTO> cmp2 =new achievementUserDTOComparator();
        Collections.sort(sumSales, cmp2);
        //System.out.println(Allstaff.size());
        request.setCharacterEncoding("GB18030");
        session.setAttribute("achievementUsers", sumSales);
        // System.out.println(request.getContextPath());
        response.sendRedirect(request.getContextPath() + "/admin/achievementUser.jsp");
    }
}
