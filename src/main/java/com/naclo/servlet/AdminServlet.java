package com.naclo.servlet;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.naclo.listener.AdminListener;
import com.naclo.listener.StudentListener;
import com.naclo.listener.TeacherListener;
import com.naclo.pojo.*;
import com.naclo.service.*;
import com.naclo.service.impl.*;
import com.naclo.utils.Constants;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AdminServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    StudentService studentService = new StudentServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    MajorService majorService = new MajorServiceImpl();
    AdminService adminService = new AdminServiceImpl();
    IdeaTableService ideaTableService = new IdeaTableServiceImpl();
    IdeaViewService ideaViewService = new IdeaViewServiceImpl();
    IdeaService ideaService = new IdeaServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if ("getAllStudents".equals(method)) {//获取所有学生
            getAllStudents(req, resp);
        } else if ("deleteStudentData".equals(method)) {//删除学生数据
            deleteStudentData(req, resp);
        } else if ("updateStudentData".equals(method)) {//更新学生数据
            updateStudentData(req, resp);
        } else if ("addStudentData".equals(method)) {//增加学生数据
            addStudentData(req, resp);
        } else if ("resetStudentPassword".equals(method)) {//重置学生密码
            resetStudentPassword(req, resp);
        } else if ("exportStudentList".equals(method)) {//导出学生名单
            exportStudentList(req, resp);
        } else if ("importStudentList".equals(method)) {//导入学生名单
            importStudentList(req, resp);
        } else if ("validateStudentId".equals(method)) {//验证学号是否存在
            validateStudentId(req, resp);
        } else if ("getStudentById".equals(method)) {//根据学号获取学生
            getStudentById(req, resp);
        } else if ("getAllTeachers".equals(method)) {//获取所有导师
            getAllTeachers(req, resp);
        } else if ("deleteTeacherData".equals(method)) {//删除导师数据
            deleteTeacherData(req, resp);
        } else if ("updateTeacherData".equals(method)) {//修改导师数据
            updateTeacherData(req, resp);
        } else if ("addTeacherData".equals(method)) {//添加导师数据
            addTeacherData(req, resp);
        } else if ("resetTeacherPassword".equals(method)) {//重置导师密码
            resetTeacherPassword(req, resp);
        } else if ("exportTeacherList".equals(method)) {//导出教师名单
            exportTeacherList(req, resp);
        } else if ("importTeacherList".equals(method)) {//导入教师名单
            importTeacherList(req, resp);
        } else if ("validateTeacherId".equals(method)) {//验证工号是否存在
            validateTeacherId(req, resp);
        } else if ("getTeacherById".equals(method)) {//根据工号获取导师
            getTeacherById(req, resp);
        } else if ("getAllAdmins".equals(method)) {//获取所有管理员
            getAllAdmins(req, resp);
        } else if ("deleteAdminData".equals(method)) {//删除管理员数据
            deleteAdminData(req, resp);
        } else if ("updateAdminData".equals(method)) {//修改管理员数据
            updateAdminData(req, resp);
        } else if ("addAdminData".equals(method)) {//添加管理员数据
            addAdminData(req, resp);
        } else if ("resetAdminPassword".equals(method)) {//重置管理员密码
            resetAdminPassword(req, resp);
        } else if ("exportAdminList".equals(method)) {//导出管理员名单
            exportAdminList(req, resp);
        } else if ("importAdminList".equals(method)) {//导入管理员名单
            importAdminList(req, resp);
        } else if ("validateAdminId".equals(method)) {//验证工号是否存在
            validateAdminId(req, resp);
        } else if ("getAllMajors".equals(method)) {//获取所有专业
            getAllMajors(req, resp);
        } else if ("getAllMajorsExceptALL".equals(method)) {//获取所有专业
            getAllMajorsExceptALL(req, resp);
        } else if ("addMajorData".equals(method)) {//增加专业数据
            addMajorData(req, resp);
        } else if ("deleteMajorData".equals(method)) {//删除专业数据
            deleteMajorData(req, resp);
        } else if ("updateMajorData".equals(method)) {//修改专业数据
            updateMajorData(req, resp);
        } else if ("validateMajorName".equals(method)) {//验证专业名是否存在
            validateMajorName(req, resp);
        } else if ("getMajorById".equals(method)) {//根据编号获取专业
            getMajorById(req, resp);
        } else if ("getIdeaTableList".equals(method)) {//获取志愿表
            getIdeaTableList(req, resp);
        } else if ("exportIdeaList".equals(method)) {//导出志愿表
            exportIdeaList(req, resp);
        } else if ("getStudentTeacherList".equals(method)) {//获取学生导师对
            getStudentTeacherList(req, resp);
        } else if ("getTeacherSelectStudentList".equals(method)) {//获取学生导师对
            getTeacherSelectStudentList(req, resp);
        } else if ("adminAuditTeacherDecide".equals(method)) {//获取学生导师对
            adminAuditTeacherDecide(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void getAllStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            studentList = studentService.queryAllStudents();
        } else {
            studentList = studentService.queryStudentByMajor(major);
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(studentList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void deleteStudentData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        boolean flag = false;
        flag = studentService.deleteStudentById(studentId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "删除学生成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminStudentList.jsp");
            //req.getRequestDispatcher(req.getContextPath() + "/admin/AdminStudentList.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void updateStudentData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String studentName = req.getParameter("studentName");
        String studentMajor = req.getParameter("studentMajor");
        boolean flag = false;
        Student student = new Student(studentId, studentName, null, studentMajor);
        flag = studentService.updateStudentById(student);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "信息修改成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminStudentList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void addStudentData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        String studentName = req.getParameter("studentName");
        String studentMajor = req.getParameter("studentMajor");
        boolean flag = false;
        Student student = new Student(studentId, studentName, null, studentMajor);
        flag = studentService.insertStudent(student);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "插入学生成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminStudentList.jsp");
            //req.getRequestDispatcher(req.getContextPath() + "/admin/AdminStudentList.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void resetStudentPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        boolean flag = false;
        flag = studentService.updateStudentPasswordById(studentId, studentId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "密码重置成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminStudentList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void exportStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            studentList = studentService.queryAllStudents();
        } else {
            studentList = studentService.queryStudentByMajor(major);
        }
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=" + "student.xlsx");
        resp.setContentType("application/ms excel");
        EasyExcel.write(outputStream, Student.class).sheet("学生列表").doWrite(studentList);
        outputStream.flush();
        outputStream.close();
    }

    public void importStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 上传文件存储目录
        String UPLOAD_DIRECTORY = "upload";

        // 上传配置
        int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
        int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
        int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
        // 检测是否为多媒体上传
        if (!ServletFileUpload.isMultipartContent(req)) {
            // 如果不是则停止
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }

        // 配置上传参数
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        // 设置临时存储目录
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

        // 设置最大文件上传值
        upload.setFileSizeMax(MAX_FILE_SIZE);

        // 设置最大请求值 (包含文件和表单数据)
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // 中文处理
        upload.setHeaderEncoding("UTF-8");

        // 构造临时路径来存储上传的文件
        // 这个路径相对当前应用的目录
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;

        // 如果目录不存在则创建
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            // 解析请求的内容提取文件数据
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                // 迭代表单数据
                for (FileItem item : formItems) {
                    // 处理不在表单中的字段
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        // 在控制台输出文件的上传路径
                        logger.info(filePath);
                        // 保存文件到硬盘
                        item.write(storeFile);
                        EasyExcel.read(storeFile, Student.class, new StudentListener()).sheet().doRead();
                        File delFile = new File(filePath);
                        delFile.delete();
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void validateStudentId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("validateStudentId");
        String studentId = req.getParameter("studentId");
        System.out.println(studentId);
        Map<String, String> resultMap = new HashMap<String, String>();

        if (StringUtils.isNullOrEmpty(studentId)) {//学号输入为空
            resultMap.put("result", "error");
        } else if (studentId.length() != 8) {//长度不为8位
            resultMap.put("result", "length");
        } else if (!(Pattern.compile("[0-9]*")).matcher(studentId).matches()) {//不全为数字
            resultMap.put("result", "length");
        } else {
            Student student = studentService.queryStudentById(studentId);
            System.out.println(student);
            if (StringUtils.isNullOrEmpty(student.getStudentId())) {//学号不存在
                resultMap.put("result", "true");
            } else {//学号存在
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        System.out.println(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getStudentById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        Student student = studentService.queryStudentById(studentId);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(student));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getAllTeachers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            teacherList = teacherService.queryAllTeachers();
        } else {
            teacherList = teacherService.queryTeacherByMajor(major);
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(teacherList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void deleteTeacherData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        boolean flag = false;
        flag = teacherService.deleteTeacherById(teacherId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "删除导师成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminTeacherList.jsp");
            //req.getRequestDispatcher(req.getContextPath() + "/admin/AdminTeacherList.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void updateTeacherData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        String teacherName = req.getParameter("teacherName");
        String teacherMajor = req.getParameter("teacherMajor");
        String teacherIntroduce = req.getParameter("teacherIntroduce");
        boolean flag = false;
        Teacher teacher = new Teacher(teacherId, teacherName, null, teacherMajor, teacherIntroduce);
        flag = teacherService.updateTeacherById(teacher);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "信息修改成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminTeacherList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void addTeacherData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        String teacherName = req.getParameter("teacherName");
        String teacherMajor = req.getParameter("teacherMajor");
        String teacherIntroduce = req.getParameter("teacherIntroduce");
        boolean flag = false;
        Teacher teacher = new Teacher(teacherId, teacherName, null, teacherMajor, teacherIntroduce);
        flag = teacherService.insertTeacher(teacher);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "插入导师成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminTeacherList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void resetTeacherPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        boolean flag = false;
        flag = teacherService.updateTeacherPasswordById(teacherId, teacherId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "密码重置成功");
            req.setAttribute(Constants.STATE_MESSAGE, "密码重置成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminTeacherList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void exportTeacherList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Teacher> teacherList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            teacherList = teacherService.queryAllTeachers();
        } else {
            teacherList = teacherService.queryTeacherByMajor(major);
        }
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=" + "teacher.xlsx");
        resp.setContentType("application/ms excel");
        EasyExcel.write(outputStream, Teacher.class).sheet("教师列表").doWrite(teacherList);
        outputStream.flush();
        outputStream.close();
    }

    public void importTeacherList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String UPLOAD_DIRECTORY = "upload";
        int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
        int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
        int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
        if (!ServletFileUpload.isMultipartContent(req)) {
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        logger.info(filePath);
                        item.write(storeFile);
                        EasyExcel.read(storeFile, Teacher.class, new TeacherListener()).sheet().doRead();
                        File delFile = new File(filePath);
                        delFile.delete();
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void validateTeacherId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("validateTeacherId");
        String teacherId = req.getParameter("teacherId");
        System.out.println(teacherId);
        Map<String, String> resultMap = new HashMap<String, String>();
        if (StringUtils.isNullOrEmpty(teacherId)) {//工号输入为空
            resultMap.put("result", "error");
        } else if (teacherId.length() != 10) {//长度不为10位
            resultMap.put("result", "length");
        } else if (!(Pattern.compile("[0-9]*")).matcher(teacherId).matches()) {//不全为数字
            resultMap.put("result", "length");
        } else {
            Teacher teacher = teacherService.queryTeacherById(teacherId);
            System.out.println(teacher);
            if (StringUtils.isNullOrEmpty(teacher.getTeacherId())) {//工号不存在
                resultMap.put("result", "true");
            } else {//工号存在
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        System.out.println(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getTeacherById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacherId = req.getParameter("teacherId");
        Teacher teacher = teacherService.queryTeacherById(teacherId);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(teacher));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getAllAdmins(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> adminList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            adminList = adminService.queryAllAdmins();
        } else {
            adminList = adminService.queryAllAdmins();
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(adminList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void deleteAdminData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminId = req.getParameter("adminId");
        boolean flag = false;
        flag = adminService.deleteAdminById(adminId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "删除管理员成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminAdminList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void updateAdminData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminId = req.getParameter("adminId");
        String adminMajor = req.getParameter("adminMajor");
        boolean flag = false;
        Admin admin = new Admin(adminId, null, adminMajor);
        flag = adminService.updateAdminById(admin);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "信息修改成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminAdminList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void addAdminData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminId = req.getParameter("adminId");
        String adminMajor = req.getParameter("adminMajor");
        boolean flag = false;
        Admin admin = new Admin(adminId, null, adminMajor);
        flag = adminService.insertAdmin(admin);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "插入管理员成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminAdminList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void resetAdminPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String adminId = req.getParameter("adminId");
        boolean flag = false;
        flag = adminService.updateAdminPasswordById(adminId, adminId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "密码重置成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminAdminList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void exportAdminList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Admin> adminList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        adminList = adminService.queryAllAdmins();
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=" + "admin.xlsx");
        resp.setContentType("application/ms excel");
        EasyExcel.write(outputStream, Admin.class).sheet("管理员列表").doWrite(adminList);
        outputStream.flush();
        outputStream.close();
    }

    public void importAdminList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String UPLOAD_DIRECTORY = "upload";
        int MEMORY_THRESHOLD = 1024 * 1024 * 3;  // 3MB
        int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
        int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB
        if (!ServletFileUpload.isMultipartContent(req)) {
            PrintWriter writer = resp.getWriter();
            writer.println("Error: 表单必须包含 enctype=multipart/form-data");
            writer.flush();
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(MEMORY_THRESHOLD);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        upload.setHeaderEncoding("UTF-8");
        String uploadPath = getServletContext().getRealPath("/") + File.separator + UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(req);
            if (formItems != null && formItems.size() > 0) {
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);
                        logger.info(filePath);
                        item.write(storeFile);
                        EasyExcel.read(storeFile, Admin.class, new AdminListener()).sheet().doRead();
                        File delFile = new File(filePath);
                        delFile.delete();
                    }
                }
            }
        } catch (Exception ex) {
            req.setAttribute("message",
                    "错误信息: " + ex.getMessage());
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    public void validateAdminId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("validateAdminId");
        String adminId = req.getParameter("adminId");
        System.out.println(adminId);
        Map<String, String> resultMap = new HashMap<String, String>();

        if (StringUtils.isNullOrEmpty(adminId)) {//用户名输入为空
            resultMap.put("result", "error");
        } else {
            Admin admin = adminService.queryAdminsByAdminId(adminId);
            System.out.println(admin);
            if (StringUtils.isNullOrEmpty(admin.getAdminId())) {//用户名不存在
                resultMap.put("result", "true");
            } else {//用户名存在
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        System.out.println(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getAllMajors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Major> majorList = majorService.queryAllMajors();
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(majorList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getAllMajorsExceptALL(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Major> majorList = majorService.queryAllMajorsExceptALL();
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(majorList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void addMajorData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String majorName = req.getParameter("majorName");
        int studentMax = Integer.parseInt(req.getParameter("studentMax"));
        boolean flag = false;
        Major major = new Major(0, majorName, studentMax);
        flag = majorService.insertMajor(major);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "插入专业成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminMajorList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void updateMajorData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int majorId = Integer.parseInt(req.getParameter("majorId"));
        String majorName = req.getParameter("majorName");
        int studentMax = Integer.parseInt(req.getParameter("studentMax"));
        boolean flag = false;
        Major major = new Major(majorId, majorName, studentMax);
        flag = majorService.updateMajor(major);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "信息修改成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminMajorList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void deleteMajorData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int majorId = Integer.parseInt(req.getParameter("majorId"));
        boolean flag = false;
        flag = majorService.deleteMajorById(majorId);
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "删除专业成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminMajorList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void validateMajorName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String majorName = req.getParameter("majorName");
        Map<String, String> resultMap = new HashMap<String, String>();

        if (StringUtils.isNullOrEmpty(majorName)) {//专业输入为空
            resultMap.put("result", "error");
        } else {
            List<Major> majorList = majorService.queryMajorByName(majorName);
            if (majorList.size() == 0) {//专业不存在
                resultMap.put("result", "true");
            } else {//专业存在
                resultMap.put("result", "false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        System.out.println(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getMajorById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int majorId = Integer.parseInt(req.getParameter("majorId"));
        Major major = majorService.queryMajorById(majorId);
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(major));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getIdeaTableList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<IdeaTable> ideaTableList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            ideaTableList = ideaTableService.queryStudentIdeasByMajor("ALL");
        } else {
            ideaTableList = ideaTableService.queryStudentIdeasByMajor(major);
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(ideaTableList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void exportIdeaList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<IdeaTable> ideaTableList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            ideaTableList = ideaTableService.queryStudentIdeasByMajor("ALL");
        } else {
            ideaTableList = ideaTableService.queryStudentIdeasByMajor(major);

        }
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=" + "idea.xlsx");
        resp.setContentType("application/ms excel");
        EasyExcel.write(outputStream, IdeaTable.class).sheet("志愿列表").doWrite(ideaTableList);
        outputStream.flush();
        outputStream.close();
    }

    public void getStudentTeacherList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<IdeaView> ideaViewList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            ideaViewList = ideaViewService.queryIdeas(null, null, null, 5);
        } else {
            ideaViewList = ideaViewService.queryIdeas(null, null, major, 5);
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(ideaViewList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void getTeacherSelectStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<IdeaView> ideaViewList = new ArrayList<>();
        String major = (String) (req.getSession().getAttribute(Constants.USER_MAJOR));
        if ("ALL".equals(major)) {
            ideaViewList = ideaViewService.queryIdeas(null, null, null, 2);
        } else {
            ideaViewList = ideaViewService.queryIdeas(null, null, major, 2);
        }
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(ideaViewList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    public void adminAuditTeacherDecide(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] ideaIds = req.getParameterValues("ideaId");
        boolean flag = true;
        for (String ideaId : ideaIds) {
            flag &= ideaService.updateIdeaStateByIdeaId(Integer.parseInt(ideaId), 5);
        }
        if (flag) {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "选择成功");
        } else {
            req.getSession().setAttribute(Constants.STATE_MESSAGE, "选择失败");
        }

        resp.sendRedirect(req.getContextPath() + "/admin/AdminAuditTeacherShow.jsp");
    }
}