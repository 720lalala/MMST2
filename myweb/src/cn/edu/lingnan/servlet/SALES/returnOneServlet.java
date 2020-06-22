package cn.edu.lingnan.servlet.SALES;

import cn.edu.lingnan.dao.DepotDAO;
import cn.edu.lingnan.dao.SalesDAO;
import cn.edu.lingnan.dto.DepotDetailsDTO;
import cn.edu.lingnan.dto.SalesDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Vector;

/**
 * @author admin on 2020-04-07.
 * @version 1.0
 */
public class returnOneServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String flowid_return=session.getAttribute("flowid_return").toString();
        String clothingid=request.getParameter("TclothingidF");
        Vector<SalesDTO> returnDetails=( Vector<SalesDTO>)session.getAttribute("returnDetails");
        Vector<SalesDTO> flowid_returnDetails = SalesDAO.SearchSomeSalesByFlowid(flowid_return);
        boolean result = false;
        if(flowid_returnDetails.size()!=0){
            for(int i = 0; i < flowid_returnDetails.size(); i++)
                if(flowid_returnDetails.get(i).getClothingid().equals(clothingid)){
                    int numbers = flowid_returnDetails.get(i).getNumbers()-flowid_returnDetails.get(i).getState();
                    if( numbers <= 0) {
                        result = false;
                        break;
                    }
                    int j = 0;
                    if(returnDetails == null){
                        returnDetails = new Vector<SalesDTO>();
                        SalesDTO sales = new SalesDTO();
                        sales.setClothingid(clothingid);
                        sales.setDisprice(flowid_returnDetails.get(i).getDisprice()/flowid_returnDetails.get(i).getNumbers());
                        sales.setNumbers(1);
                        sales.setUserid(flowid_returnDetails.get(i).getUserid());
                        sales.setStaffid(flowid_returnDetails.get(i).getStaffid());
                        returnDetails.add(sales);
                        result = true;
                        break;
                    }
                    for (; j<returnDetails.size();j++){
                        if(returnDetails.get(j).getClothingid().equals(clothingid)){
                            if(numbers == returnDetails.get(j).getNumbers())
                                break;
                            returnDetails.get(j).setDisprice( returnDetails.get(j).getDisprice()+flowid_returnDetails.get(i).getDisprice()/flowid_returnDetails.get(i).getNumbers());
                            returnDetails.get(j).setNumbers(returnDetails.get(j).getNumbers()+1);
                            result = true;
                            break;
                        }
                    }
                    if(j == returnDetails.size()){
                        SalesDTO sales = new SalesDTO();
                        sales.setClothingid(clothingid);
                        sales.setDisprice(flowid_returnDetails.get(i).getDisprice()/flowid_returnDetails.get(i).getNumbers());
                        sales.setNumbers(1);
                        sales.setUserid(flowid_returnDetails.get(i).getUserid());
                        sales.setStaffid(flowid_returnDetails.get(i).getStaffid());
                        returnDetails.add(sales);
                        result = true;
                    }
                    break;
                }
        }
        if(result)
        {
            session.setAttribute("returnDetails",returnDetails);
            response.sendRedirect(request.getContextPath()+"/allCanAccept/returnsPolicy.jsp");
        }
        else {
            // System.out.println("进来了？5");
            response.getWriter().print( "<script>alert(\"请确认输入的衣服编号是否有误!\");window.location.href='/allCanAccept/returnsPolicy.jsp'</script>");
           }

    }
}