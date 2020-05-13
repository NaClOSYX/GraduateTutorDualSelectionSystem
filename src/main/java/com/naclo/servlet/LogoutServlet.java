package com.naclo.servlet;

import com.naclo.utils.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author NaClO
 * @create 2020/4/30 16:08
 */
public class LogoutServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute(Constants.USER_SESSION) != null) {
            String userId = session.getAttribute(Constants.USER_SESSION).toString();
            session.removeAttribute(Constants.USER_SESSION);
            session.removeAttribute(Constants.USER_ROLE);
            session.removeAttribute(Constants.USER_MAJOR);
            logger.info(userId + " logout");
        }
        resp.sendRedirect(req.getContextPath() + "/alreadyLogout.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
