package com.naclo.servlet;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.naclo.pojo.Student;
import com.naclo.pojo.Teacher;
import com.naclo.service.StudentService;
import com.naclo.service.TeacherService;
import com.naclo.service.impl.StudentServiceImpl;
import com.naclo.service.impl.TeacherServiceImpl;
import com.naclo.utils.Constants;
import com.naclo.utils.MD5Utils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    StudentService studentService = new StudentServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("updatePassword".equals(method)) {//更新密码
            updatePassword(req, resp);
        } else if ("validateOldPassword".equals(method)) {//验证旧密码
            validateOldPassword(req, resp);
        } else if ("getTeacherList".equals(method)) {//验证旧密码
            getTeacherList(req, resp);
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

    public void getTeacherList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String studentMajor = (String) (session.getAttribute(Constants.USER_MAJOR));
        List<Teacher> teacherList = teacherService.queryTeacherByMajor(studentMajor);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(teacherList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
}
