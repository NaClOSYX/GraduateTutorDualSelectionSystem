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
        //获取ip
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
                    loginLogsService.insertLoginLogs(new LoginLogs(0, userId, ("ALL".equals(major) ? "研究生院" : major) + "管理员", "用户登陆", new Date(), ip));
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
        //X-Forwarded-For：Squid 服务代理
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //Proxy-Client-IP：apache 服务代理
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //WL-Proxy-Client-IP：weblogic 服务代理
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //HTTP_CLIENT_IP：有些代理服务器
            ip = request.getHeader("HTTP_CLIENT_IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            //X-Real-IP：nginx服务代理
            ip = request.getHeader("X-Real-IP");
        }

        //有些网络通过多层代理，那么获取到的ip就会有多个，一般都是通过逗号（,）分割开来，并且第一个ip为客户端的真实IP
        if (ip != null && ip.length() != 0) {
            ip = ip.split(",")[0];
        }

        //还是不能获取到，最后再通过request.getRemoteAddr();获取
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        // ip配置
        if (ip.equals("127.0.0.1") || ip.endsWith("0:0:0:0:0:0:1")) {
            // 根据网卡取本机配置的IP
            ip = "127.0.0.1";
        }
        return ip;
    }
}