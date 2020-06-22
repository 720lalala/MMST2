package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.StaffDAO;
import cn.edu.lingnan.dao.StockTransferDAO;
import cn.edu.lingnan.dao.UserDAO;
import cn.edu.lingnan.dto.DepotDTO;
import cn.edu.lingnan.dto.StockTransferDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

/**
 * @author admin on 2020-04-08.
 * @version 1.0
 */
public class submitStockInServlet extends HttpServlet {
    @Override
    public void init()throws ServletException {}
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        doGet(request,response);
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        // request.setCharacterEncoding("GB18030");
        HttpSession session = request.getSession();
        String staffid=request.getParameter("staffidI");
        String in_userid=session.getAttribute("userid").toString();
        String id=session.getAttribute("idTemp").toString();
        boolean bool1 = StaffDAO.SearchStaffOne(staffid,in_userid);
        if(!bool1){
            response.getWriter().print( "<script>alert(\"没有此员工编号!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");
        }
        else {
            Vector<DepotDTO> AllDepotD = StockTransferDAO.findStockTransferDetails(in_userid,id);
            Vector<DepotDTO> salesDetails=( Vector<DepotDTO>)session.getAttribute("stockInDetails");
            int i = 0;
            while (i<salesDetails.size()) {
                int j = 0;
                while (j < AllDepotD.size()) {
                    if (AllDepotD.get(j).getClothingid().equals(salesDetails.get(i).getClothingid())) {
                        AllDepotD.get(j).setNumbers(AllDepotD.get(j).getNumbers()-salesDetails.get(i).getNumbers());
                        break;
                    }
                    j++;
                }
                i++;
            }
            i = 0 ;
            while (i < AllDepotD.size()) {
                boolean bool2 = StockTransferDAO.changeStockTransferDetail(AllDepotD.get(i),id,1);
                boolean bool3 = DepotDAO.InsertInventory(AllDepotD.get(i).getClothingid(),in_userid,AllDepotD.get(i).getNumbers());
                if((!bool2)||(!bool3)) break;
                i++;
            }
            if(i != AllDepotD.size()) {
                response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='stockTransferServlet'</script>");
            }else {
                i = 0;
                while (i<salesDetails.size()) {
                    boolean bool4 = StockTransferDAO.insertStockTransferDetail(salesDetails.get(i),id,2);

                    if(!bool4) break;
                    i++;
                }if(i != salesDetails.size()) {
                    response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='stockTransferServlet'</script>");
                }else {
                    Date date1 = new Date();
                    SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String in_time = aaaaa.format(date1);
                    int status = 1;
                    if(salesDetails.size()!=0) {
                        status = 2;
                    }
                    StockTransferDTO a = new StockTransferDTO();
                    a.setId(id);
                    a.setInUserid(in_userid);
                    a.setInTime(in_time);
                    a.setStatus(status);
                    a.setInStaffid(staffid);

                    boolean bool5 =StockTransferDAO.changeStockTransfer(a);
                    if(!bool5){
                        response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='stockTransferServlet'</script>");
                    }else {
                        session.removeAttribute("stockOutDetails");
                        session.removeAttribute("stockInDetails");
                        session.removeAttribute("idTemp");
                        response.getWriter().print( "<script>alert(\"入库成功!\");window.location.href='stockTransferServlet'</script>");

                    }
                }

            }
        }


    }

}
