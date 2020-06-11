package com.naclo.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author NaClO
 * @create 2020/6/11 18:42
 */
public class InitServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        String startTime = "";
        String endTime = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startTime = format.format(startDate);
            endTime = format.format(endDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        servletContext.setAttribute("startTime", startTime);
        servletContext.setAttribute("endTime", endTime);
    }
}
