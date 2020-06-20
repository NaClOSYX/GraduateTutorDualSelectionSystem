package com.naclo.servlet;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.naclo.pojo.Idea;
import com.naclo.pojo.Student;
import com.naclo.pojo.Teacher;
import com.naclo.service.IdeaService;
import com.naclo.service.IdeaViewService;
import com.naclo.service.StudentService;
import com.naclo.service.TeacherService;
import com.naclo.service.impl.IdeaServiceImpl;
import com.naclo.service.impl.IdeaViewServiceImpl;
import com.naclo.service.impl.StudentServiceImpl;
import com.naclo.service.impl.TeacherServiceImpl;
import com.naclo.utils.Constants;
import com.naclo.utils.MD5Util;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    StudentService studentService = new StudentServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    IdeaService ideaService = new IdeaServiceImpl();
    IdeaViewService ideaViewService = new IdeaViewServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("updatePassword".equals(method)) {//更新密码
            updatePassword(req, resp);
        } else if ("validateOldPassword".equals(method)) {//验证旧密码
            validateOldPassword(req, resp);
        } else if ("getTeacherList".equals(method)) {//获取教师列表
            getTeacherList(req, resp);
        } else if ("studentChooseTeacher".equals(method)) {//选择老师
            studentChooseTeacher(req, resp);
        } else if ("changeIdea".equals(method)) {//修改志愿
            changeIdea(req, resp);
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
            req.getSession().removeAttribute(Constants.MAJOR_MAX_STUDENTS);
        } else {
            req.setAttribute("message", "修改密码失败！");
        }
        req.getRequestDispatcher(req.getContextPath() + "/student/StudentUpdatePassword.jsp").forward(req, resp);

    }

    public void validateOldPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getSession().getAttribute(Constants.USER_SESSION).toString();
        String oldPassword = req.getParameter("oldPassword");
        String oldPwd = MD5Util.stringToMD5(oldPassword);
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

    public void studentChooseTeacher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String studentId = (String) (session.getAttribute(Constants.USER_SESSION));
        String major = (String) (session.getAttribute(Constants.USER_MAJOR));
        String teacherId1 = req.getParameter("teacherId1");
        String teacherId2 = req.getParameter("teacherId2");
        String teacherId3 = req.getParameter("teacherId3");

        int count = ideaService.queryIdeasByStudentIdCount(studentId);
        if (count >= 3) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "您已经选择过导师了");
            resp.sendRedirect(req.getContextPath() + "/student/StudentChooseTeacher.jsp");
        } else {
            boolean f1 = ideaService.insertIdea(new Idea(0, major, studentId, teacherId1, new Date(), 1));
            boolean f2 = ideaService.insertIdea(new Idea(0, major, studentId, teacherId2, new Date(), 1));
            boolean f3 = ideaService.insertIdea(new Idea(0, major, studentId, teacherId3, new Date(), 1));
            if (f1 == true && f2 == true && f3 == true) {
                req.getSession().setAttribute(Constants.STATE_MESSAGE, "插入成功");
            } else {
                req.getSession().setAttribute(Constants.STATE_MESSAGE, "插入失败");
            }
            resp.sendRedirect(req.getContextPath() + "/student/StudentChooseTeacher.jsp");
        }
    }

    public void changeIdea(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String studentId = (String) (session.getAttribute(Constants.USER_SESSION));
        String major = (String) (session.getAttribute(Constants.USER_MAJOR));
        String oldTeacher = req.getParameter("oldTeacher");
        String newTeacher = req.getParameter("newTeacher");
        String oldTeacherId = teacherService.queryTeacherByName(oldTeacher).get(0).getTeacherId();
        String newTeacherId = teacherService.queryTeacherByName(newTeacher).get(0).getTeacherId();
        boolean flag = false;
        int ideaId = ideaViewService.queryIdeas(studentId, oldTeacherId, major, -1).get(0).getIdeaId();
        flag = ideaService.updateIdeaTeacherByIdeaId(ideaId, newTeacherId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "修改成功");
        } else {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "修改成功");
        }
        resp.sendRedirect(req.getContextPath() + "/student/StudentManageIdeas.jsp");
    }
}
