package com.naclo.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;


/*中文编码过滤*/
public class CharacterEncodingFilter implements Filter {
    private Logger logger = Logger.getLogger(this.getClass());

    //初始化：web服务器启动，就以及初始化了，随时等待过滤对象出现！
    public void init(FilterConfig filterConfig) throws ServletException {
        //logger.info("CharacterEncodingFilter初始化");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        //response.setContentType("text/html;charset=UTF-8");

        //logger.info("CharacterEncodingFilter执行前....");
        chain.doFilter(request, response);
        //logger.info("CharacterEncodingFilter执行后....");
    }

    //销毁：web服务器关闭的时候，过滤会销毁
    public void destroy() {
        //logger.info("CharacterEncodingFilter销毁");
    }
}
