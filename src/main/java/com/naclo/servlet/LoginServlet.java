package com.naclo.servlet;

import com.naclo.pojo.LoginLogs;
import com.naclo.pojo.User;
import com.naclo.service.LoginLogsService;
import com.naclo.service.MajorService;
import com.naclo.service.UserService;
import com.naclo.service.impl.LoginLogsServiceImpl;
import com.naclo.service.impl.MajorServiceImpl;
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
import java.util.Date;


public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    UserService userService = new UserServiceImpl();
    MajorService majorService = new MajorServiceImpl();
    LoginLogsService loginLogsService = new LoginLogsServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = null;
        HttpSession session = req.getSession();
        //获取登陆的用户id和密码
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        String ip = getIpAddr(req);

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
                String major = user.getMajor();
                int majorMaxStudents = majorService.queryMajorMaxStudents(major);
                session.setAttribute(Constants.USER_SESSION, userId);
                session.setAttribute(Constants.USER_MAJOR, major);
                session.setAttribute(Constants.MAJOR_MAX_STUDENTS, majorMaxStudents);
                if ("学生".equals(user.getRole())) {
                    session.setAttribute(Constants.USER_ROLE, Constants.ROLE_STUDENT);
                    loginLogsService.insertLoginLogs(new LoginLogs(0, userId, "学生", "用户登陆", new Date(), ip));
                    resp.sendRedirect("student/StudentIndex.jsp");
                } else if ("导师".equals(user.getRole())) {
                    session.setAttribute(Constants.USER_ROLE, Constants.ROLE_TEACHER);
                    loginLogsService.insertLoginLogs(new LoginLogs(0, userId, "教师", "用户登陆", new Date(), ip));
                    resp.sendRedirect("teacher/TeacherIndex.jsp");
                } else if ("管理员".equals(user.getRole())) {
                    session.setAttribute(Constants.USER_ROLE, Constants.ROLE_ADMIN);
                    loginLogsService.insertLoginLogs(new LoginLogs(0, userId, major + "管理员", "用户登陆", new Date(), ip));
                    resp.sendRedirect("admin/AdminIndex.jsp");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (null == ip || 0 == ip.length() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}