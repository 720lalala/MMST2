package cn.edu.lingnan.servlet;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpSession session=request.getSession();
        String authority =null;
        try { authority= session.getAttribute("authority").toString(); }
        catch (Exception e) { authority=null; }
        if(authority==null)
        {
            response.sendRedirect(request.getContextPath()+"/login.jsp");

        }
        else if(authority.equals("pu"))
        {
            response.sendRedirect(request.getContextPath()+"/error/errorAu.html");
        }
        else if(authority.equals("su"))
        {
        //    System.out.println("filterin");
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
