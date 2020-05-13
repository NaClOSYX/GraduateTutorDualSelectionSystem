package com.naclo.servlet;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.naclo.dao.StudentDao;
import com.naclo.dao.impl.StudentDaoImpl;
import com.naclo.pojo.Student;
import com.naclo.service.StudentService;
import com.naclo.service.impl.StudentServiceImpl;
import com.naclo.utils.Constants;
import com.naclo.utils.MD5Utils;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class StudentServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    StudentService studentService = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method.equals("updatePassword")) {
            updatePassword(req, resp);
        } else if (method.equals("validateOldPassword")) {
            validateOldPassword(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getSession().getAttribute(Constants.USER_SESSION).toString();
        String newPassword = req.getParameter("newPassword");
        boolean flag = false;
        flag = studentService.updateStudentPasswordById(studentId, newPassword);
        if (flag) {
            req.setAttribute("message", "修改密码成功,请退出并使用新密码重新登录！");
            req.getSession().removeAttribute(Constants.USER_SESSION);
            req.getSession().removeAttribute(Constants.USER_ROLE);
            req.getSession().removeAttribute(Constants.USER_MAJOR);
        } else {
            req.setAttribute("message", "修改密码失败！");
        }
        req.getRequestDispatcher(req.getContextPath() + "/student/StudentUpdatePassword.jsp").forward(req, resp);

    }

    public void validateOldPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getSession().getAttribute(Constants.USER_SESSION).toString();
        String oldPassword = req.getParameter("oldPassword");
        String oldPwd = MD5Utils.stringToMD5(oldPassword);
        Student student = studentService.queryStudentById(studentId);
        Map<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(oldPwd)) {//旧密码输入为空
            resultMap.put("result", "error");
        } else {
            if (oldPwd.equals(student.getStudentPassword())) {//旧密码输入正确
                resultMap.put("result", "true");
            } else {//旧密码输入不正确
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
}
