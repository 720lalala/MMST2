package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dto.DepotDetailsDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * @author admin on 2020-02-28.
 * @version 1.0
 */
public class endCountDepotServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        Vector<DepotDetailsDTO> resultList = (Vector<DepotDetailsDTO>)session.getAttribute("CountAllDepotD");
        String startDate = session.getAttribute("startDate").toString();
        String userid = session.getAttribute("userid").toString();
        // 定义标题（第一行）
        String title ="店铺"+ userid+ " 盘点结果 "+startDate.substring(0,10);

        // 定义列名（第二行）
        String[] rowsName = new String[]{"序号","编号","属性","颜色","价格","件数"};

        // 定义主题内容（第三行起）
        List<Object[]> dataList = new ArrayList<Object[]>();

        // 定义每一行的临时变量，并放入数据
        Object[] objs = null;
        for (int i = 0; i < resultList.size(); i++) {
            objs = new Object[rowsName.length];
            objs[0] = i;
            objs[1] = resultList.get(i).getClothingid();
            objs[2] = resultList.get(i).getProperty();
            objs[3] = resultList.get(i).getColor();
            objs[4] = resultList.get(i).getPrice();
            objs[5] = resultList.get(i).getNumbers();
            dataList.add(objs);
        }

        // 定义Excel文件名
        String fileName="盘点结果"+String.valueOf(System.currentTimeMillis()).substring(4,13)+".xls";

        // 创建CommonExcel对象，调用downloadExcel()对象导出Excel
        CommonExcel ex = new CommonExcel(title, rowsName, dataList,response,fileName);
        try {
            ex.downloadExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.removeAttribute("startDate");
        response.getWriter().print( "<script>alert(\"盘点成功！\");window.location.href='/allCanAccept/countDepot.jsp'</script>");

    }

}
