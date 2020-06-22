package cn.edu.lingnan.servlet.COUNT;

import cn.edu.lingnan.dao.FlowSheetDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.Count.achievementStaffDTO;
import cn.edu.lingnan.dto.Count.achievementUserDTO;
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
public class searchStaffAchievementServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String month = request.getParameter("month");
        String day = request.getParameter("day");
        String date = request.getParameter("year");
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month != null && month != "")
            date = date + "-" + month;
        if (day != null && day != "")
            date = date + "-" + day;
        String userid = request.getParameter("useridF");
        String areaid = request.getParameter("areaidF");
        Vector<achievementStaffDTO> sumSales = new Vector<achievementStaffDTO>();
        Vector<SalesDTO> AllSalesD = SalesDAO.SearchSalesForClothingSales(date, userid, null, areaid);
        if (AllSalesD.size() == 0) {
            response.getWriter().print("<script>alert(\"没有符合条件的信息！\");window.location.href='/admin/achievementStaff.jsp'</script>");
            return;

        } else {
            Comparator<SalesDTO> cmp = new SalesDTOComparator3();
            Collections.sort(AllSalesD, cmp);
            String staffids = "";
            for (int i = 0, j = 0; i < AllSalesD.size(); i++) {
                if (sumSales.size() == 0) {
                    achievementStaffDTO d = new achievementStaffDTO();
                    d.setAchievement(AllSalesD.get(i).getDisprice());
                    d.setStaffid(AllSalesD.get(i).getStaffid());
                    sumSales.add(d);
                    staffids = AllSalesD.get(i).getStaffid();
                } else if (staffids.equals(AllSalesD.get(i).getStaffid())) {
                    sumSales.get(j).setAchievement(AllSalesD.get(i).getDisprice() + sumSales.get(j).getAchievement());
                } else {
                    j++;
                    achievementStaffDTO d = new achievementStaffDTO();
                    staffids = AllSalesD.get(i).getStaffid();
                    d.setAchievement(AllSalesD.get(i).getDisprice());
                    d.setStaffid(AllSalesD.get(i).getStaffid());
                    sumSales.add(d);
                }
            }
            Comparator<achievementStaffDTO> cmp2 = new achievementStaffDTOComparator();
            Collections.sort(sumSales, cmp2);
            //System.out.println(Allstaff.size());
            request.setCharacterEncoding("GB18030");
            session.setAttribute("achievementStaff", sumSales);
            // System.out.println(request.getContextPath());
            response.sendRedirect(request.getContextPath() + "/admin/achievementStaff.jsp");

        }
    }
}
