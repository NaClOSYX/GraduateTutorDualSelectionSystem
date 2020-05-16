package com.naclo.servlet;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.naclo.pojo.Major;
import com.naclo.pojo.Student;
import com.naclo.pojo.StudentListener;
import com.naclo.service.MajorService;
import com.naclo.service.StudentService;
import com.naclo.service.impl.MajorServiceImpl;
import com.naclo.service.impl.StudentServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class AdminServlet extends HttpServlet {
    Logger logger = Logger.getLogger(this.getClass());
    StudentService studentService = new StudentServiceImpl();
    //TeacherService teacherService=new TeacherServiceImpl();
    MajorService majorService = new MajorServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method.equals("getAllStudents")) {//获取所有学生
            getAllStudents(req, resp);
        } else if (method.equals("deleteStudentData")) {//删除学生数据
            deleteStudentData(req, resp);
        } else if (method.equals("updateStudentData")) {//更新学生数据
            updateStudentData(req, resp);
        } else if (method.equals("addStudentData")) {//增加学生数据
            addStudentData(req, resp);
        } else if (method.equals("resetStudentPassword")) {//重置学生密码
            resetStudentPassword(req, resp);
        } else if (method.equals("exportStudentList")) {//导入学生名单
            exportStudentList(req, resp);
        } else if (method.equals("importStudentList")) {//导出学生名单
            importStudentList(req, resp);
        } else if (method.equals("validateStudentId")) {//验证学号是否存在
            validateStudentId(req, resp);
        } else if (method.equals("getStudentById")) {//根据学号获取学生
            getStudentById(req, resp);
        } else if (method.equals("getAllMajors")) {//获取所有专业
            getAllMajors(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void getAllStudents(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> studentList = studentService.queryAllStudents();
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
            req.getRequestDispatcher(req.getContextPath() + "/admin/AdminStudentList.jsp").forward(req, resp);
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
            req.getSession().setAttribute("PRmessage", "信息修改成功");
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
            req.getRequestDispatcher(req.getContextPath() + "/admin/AdminStudentList.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void resetStudentPassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String studentId = req.getParameter("studentId");
        boolean flag = false;
        flag = studentService.updateStudentPasswordById(studentId, studentId);
        if (flag) {
            req.getSession().setAttribute("PRmessage", "密码重置成功");
            req.setAttribute("PRmessage", "密码重置成功");
            resp.sendRedirect(req.getContextPath() + "/admin/AdminStudentList.jsp");
        } else {
            resp.sendRedirect(req.getContextPath() + "/error/error.jsp");
        }
    }

    public void exportStudentList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("exportStudentList");
        List<Student> students = studentService.queryAllStudents();
        ServletOutputStream outputStream = resp.getOutputStream();
        resp.reset();
        resp.setHeader("Content-disposition", "attachment; filename=" + "student.xlsx");
        resp.setContentType("application/ms excel");
        EasyExcel.write(outputStream, Student.class).sheet("学生列表").doWrite(students);
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

    public void getAllMajors(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Major> majorList = majorService.queryAllMajors();
        resp.setContentType("application/json");
        PrintWriter outPrintWriter = resp.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(majorList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
}