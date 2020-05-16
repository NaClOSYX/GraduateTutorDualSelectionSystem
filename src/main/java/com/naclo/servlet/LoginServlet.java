package com.naclo.servlet;

import com.naclo.pojo.User;
import com.naclo.service.UserService;
import com.naclo.service.impl.UserServiceImpl;
import com.naclo.utils.Constants;
import com.naclo.utils.MD5Utils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = null;
        HttpSession session = req.getSession();
        //获取登陆的用户id和密码
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        //在数据库中查询用户
        if (null != userId) {
            user = userService.queryUserById(userId);
        }
        if (null == user.getId()) {//用户不存在
            req.setAttribute("error", "用户不存在");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            if (!user.getPassword().equals(MD5Utils.stringToMD5(password))) {

                req.setAttribute("error", "密码错误");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                logger.info(userId + " login");
                session.setAttribute(Constants.USER_SESSION, userId);
                session.setAttribute(Constants.USER_MAJOR, user.getMajor());
                if (user.getRole().equals("学生")) {
                    session.setAttribute(Constants.USER_ROLE, Constants.ROLE_STUDENT);
                    resp.sendRedirect("student/StudentIndex.jsp");
                } else if (user.getRole().equals("教师")) {
                    session.setAttribute(Constants.USER_ROLE, Constants.ROLE_TEACHER);
                    resp.sendRedirect("teacher/TeacherIndex.jsp");
                } else if (user.getRole().equals("管理员")) {
                    session.setAttribute(Constants.USER_ROLE, Constants.ROLE_ADMIN);
                    resp.sendRedirect("admin/AdminIndex.jsp");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}