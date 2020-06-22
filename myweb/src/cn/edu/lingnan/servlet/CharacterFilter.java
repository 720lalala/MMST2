package cn.edu.lingnan.servlet;

import javax.servlet.*;
import java.io.File;
import java.io.IOException;

public class CharacterFilter implements Filter {
    String newCharSet=null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        newCharSet=filterConfig.getInitParameter("newCharSet");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(this.newCharSet);
        servletResponse.setCharacterEncoding(this.newCharSet);
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
