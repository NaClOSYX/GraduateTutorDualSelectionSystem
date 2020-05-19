package com.naclo.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;

/*中文编码过滤*/
public class CharacterEncodingFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
