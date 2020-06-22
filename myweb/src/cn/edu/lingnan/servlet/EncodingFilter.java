package cn.edu.lingnan.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author admin on 2020-02-28.
 * @version 1.0
 */
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("GB18030");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
