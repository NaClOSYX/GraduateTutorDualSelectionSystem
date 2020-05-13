package com.naclo.filter;


import com.naclo.utils.Constants;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author NaClO
 * @create 2020/5/2 19:34
 */
public class AdminFilter implements Filter {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        if (session.getAttribute(Constants.USER_SESSION) == null) {
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            if (session.getAttribute(Constants.USER_ROLE).equals(Constants.ROLE_ADMIN)) {
                chain.doFilter(request, response);
            } else {
                resp.sendRedirect(req.getContextPath() + "/error/Unauthorozed.jsp");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
