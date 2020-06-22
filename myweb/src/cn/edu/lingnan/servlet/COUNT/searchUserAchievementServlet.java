package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dao.SalesDAO;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

/**
 * @author admin on 2020-03-17.
 * @version 1.0
 */
class SalesDTOComparator2 implements Comparator<SalesDTO> {
    public int compare(SalesDTO a, SalesDTO b) {
        if(a.getUserid().compareTo(b.getUserid()) < 0){
            return 1;
        }else if(a.getUserid().equals(b.getUserid())){
            return 0;
        }else{
            return -1;
        }
    }
}
public class searchUserAchievementServlet extends HttpServlet {
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
        String date=request.getParameter("year");
        if(month.length()==1)
        {
            month="0"+month;
        }
        if(day.length()==1)
        {
            day="0"+day;
        }
        if(month != null && month != "")
            date = date +"-"+month;
        if(day != null && day != "")
            date = date +"-"+day;
        String clothingidF=request.getParameter("clothingidF");
        String userid=request.getParameter("useridF");
        String areaid=request.getParameter("areaidF");
        Vector<achievementUserDTO> sumSales =new  Vector<achievementUserDTO>();

        if(clothingidF != null && clothingidF != ""){
            Vector<SalesDTO> AllSalesD= SalesDAO.SearchSalesForClothingSales(date,userid,clothingidF,areaid);
            if(AllSalesD.size()==0)
            {
                response.getWriter().print( "<script>alert(\"没有符合条件的信息！\");window.location.href='/admin/achievementUser.jsp'</script>");
                return;

            }
            else {
                Comparator<SalesDTO> cmp =new SalesDTOComparator2();
                Collections.sort(AllSalesD, cmp);
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

            }


        }
        else{
            Vector<FlowSheetDTO> AllSalesD = FlowSheetDAO.SearchFlowsheetMessage(date,null,null,userid,areaid);
            if(AllSalesD.size()==0)
            {
                response.getWriter().print( "<script>alert(\"没有符合条件的信息！\");window.location.href='/admin/achievementUser.jsp'</script>");
                return;

            }
            Comparator<FlowSheetDTO> cmp =new FlowsheetDTOComparator();
            Collections.sort(AllSalesD, cmp);
            String userids = "";
            for(int i = 0,j = 0 ; i < AllSalesD.size(); i++){
                if(sumSales.size() == 0){
                    achievementUserDTO d = new achievementUserDTO();
                    d.setAchievement(AllSalesD.get(i).getPricea());
                    d.setUserid(AllSalesD.get(i).getUserid());
                    sumSales.add(d);
                    userids = AllSalesD.get(i).getUserid();
                }else if(userids.equals(AllSalesD.get(i).getUserid()) ){
                    sumSales.get(j).setAchievement(AllSalesD.get(i).getPricea()+sumSales.get(j).getAchievement());
                }else {
                    j++;
                    achievementUserDTO d = new achievementUserDTO();
                    userids = AllSalesD.get(i).getUserid();
                    d.setAchievement(AllSalesD.get(i).getPricea());
                    d.setUserid(AllSalesD.get(i).getUserid());
                    sumSales.add(d);
                }
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
