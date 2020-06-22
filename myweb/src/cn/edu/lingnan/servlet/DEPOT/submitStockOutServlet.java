package cn.edu.lingnan.servlet.DEPOT;

import cn.edu.lingnan.dao.*;
import cn.edu.lingnan.dto.*;

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
public class submitStockOutServlet extends HttpServlet {
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
        String in_userid=request.getParameter("inUserF");
        String out_userid = session.getAttribute("userid").toString();
        boolean bool1 = UserDAO.searchOneUser(in_userid);
        if(!bool1){
            response.getWriter().print( "<script>alert(\"没有此店铺编号!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");
        }
        else {
            String staffid=request.getParameter("staffidF");
            boolean bool2 =StaffDAO.SearchStaffOne(staffid,out_userid);
            if(!bool2){
                response.getWriter().print( "<script>alert(\"没有此员工编号!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");
            }
            else {
                Date date1 = new Date();
                SimpleDateFormat aaaaa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String out_time = aaaaa.format(date1);
                String id = StockTransferDAO.searchFutureFlowid();
                int numbers=Integer.valueOf(request.getParameter("numbersF"));
                StockTransferDTO a = new StockTransferDTO(id,numbers,out_time,null,staffid,null,out_userid,in_userid,0);
                boolean bool3 = StockTransferDAO.insertStockTransfer(a);
                if(!bool3){
                    response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");

                }else {
                    Vector<DepotDTO> salesDetails=( Vector<DepotDTO>)session.getAttribute("stockOutDetails");
                    int i = 0;
                    while (i<salesDetails.size()){
                        boolean bool4 = StockTransferDAO.insertStockTransferDetail(salesDetails.get(i),id,0);
                        boolean bool5= DepotDAO.StockOutReduceInventory(salesDetails.get(i).getClothingid(),out_userid,salesDetails.get(i).getNumbers());
                        if((!bool4)||(!bool5))
                        {
                            break;
                        }
                        i++;
                    }
                    if(i != salesDetails.size()){
                        response.getWriter().print( "<script>alert(\"系统出错!\");window.location.href='/allCanAccept/stockTransfer.jsp'</script>");
                    }
                    else {
                        session.removeAttribute("stockOutDetails");
                        session.removeAttribute("stockInDetails");
                        response.getWriter().print( "<script>alert(\"出库成功!单号为"+id+"\");window.location.href='stockTransferServlet'</script>");

                    }

                }

            }
        }


    }

}