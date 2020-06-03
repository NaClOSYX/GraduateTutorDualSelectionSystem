package com.naclo.servlet;


import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.naclo.pojo.IdeaView;
import com.naclo.pojo.Teacher;
import com.naclo.service.*;
import com.naclo.service.impl.*;
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


public class TeacherServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    StudentService studentService = new StudentServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    IdeaService ideaService = new IdeaServiceImpl();
    IdeaTableService ideaTableService = new IdeaTableServiceImpl();
    IdeaViewService ideaViewService = new IdeaViewServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("updatePassword".equals(method)) {//更新密码
            updatePassword(req, resp);
        } else if ("validateOldPassword".equals(method)) {//验证旧密码
            validateOldPassword(req, resp);
        } else if ("getStudentList".equals(method)) {//获取学生列表
            getStudentList(req, resp);
        } else if ("getStudentListDecided".equals(method)) {//获取选定的学生列表
            getStudentListDecided(req, resp);
        } else if ("teacherChooseStudent".equals(method)) {//获取选定的学生列表
            teacherChooseStudent(req, resp);
        } else if ("getStudentListFinally".equals(method)) {//获取最终的学生列表
            getStudentListFinally(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getSession().getAttribute(Constants.USER_SESSION).toString();
        String newPassword = req.getParameter("newPassword");
        boolean flag = false;
        flag = teacherService.updateTeacherPasswordById(teacherId, newPassword);
        if (flag) {
            req.setAttribute("message", "修改密码成功,请退出并使用新密码重新登录！");
            req.getSession().removeAttribute(Constants.USER_SESSION);
            req.getSession().removeAttribute(Constants.USER_ROLE);
            req.getSession().removeAttribute(Constants.USER_MAJOR);
            req.getSession().removeAttribute(Constants.MAJOR_MAX_STUDENTS);
        } else {
            req.setAttribute("message", "修改密码失败！");
        }
        req.getRequestDispatcher(req.getContextPath() + "/teacher/TeacherUpdatePassword.jsp").forward(req, resp);

    }

    public void validateOldPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getSession().getAttribute(Constants.USER_SESSION).toString();
        String oldPassword = req.getParameter("oldPassword");
        String oldPwd = MD5Utils.stringToMD5(oldPassword);
        Teacher teacher = teacherService.queryTeacherById(teacherId);
        Map<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(oldPwd)) {//旧密码输入为空
            resultMap.put("result", "error");
        } else {
            if (oldPwd.equals(teacher.getTeacherPassword())) {//旧密码输入正确
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

    public void getStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String teacherId = (String) (session.getAttribute(Constants.USER_SESSION));
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas(null, teacherId, null, 1);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(ideaViewList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getStudentListDecided(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String teacherId = (String) (session.getAttribute(Constants.USER_SESSION));
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas(null, teacherId, null, 2);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(ideaViewList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getStudentListFinally(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String teacherId = (String) (session.getAttribute(Constants.USER_SESSION));
        List<IdeaView> ideaViewList = ideaViewService.queryIdeas(null, teacherId, null, 5);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(ideaViewList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void teacherChooseStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int majorMaxStudents = (int) (session.getAttribute(Constants.MAJOR_MAX_STUDENTS));
        String teacherId = (String) (session.getAttribute(Constants.USER_SESSION));
        int teacherStudentsCount = ideaService.queryIdeasByTeacherIdCount(teacherId);
        String[] studentIds = req.getParameterValues("studentId");
        int studentLength = studentIds.length;
        if (studentLength + teacherStudentsCount > majorMaxStudents) {
            String msg = "您已经拥有了" + teacherStudentsCount + "位学生" + "还能选择" + (majorMaxStudents - teacherStudentsCount) + "位学生，请重新选择";
            req.getSession().setAttribute(Constants.STATE_MESSAGE, msg);
        } else {
            boolean flag = true;
            for (String studentId : studentIds) {
                flag &= ideaService.updateIdeaStateById(studentId, null, 4);
                flag &= ideaService.updateIdeaStateById(studentId, teacherId, 2);
            }
            if (flag) {
                req.getSession().setAttribute(Constants.STATE_MESSAGE, "选择成功");
            } else {
                req.getSession().setAttribute(Constants.STATE_MESSAGE, "选择失败");
            }
        }
        resp.sendRedirect(req.getContextPath() + "/teacher/TeacherChooseStudent.jsp");
    }
}