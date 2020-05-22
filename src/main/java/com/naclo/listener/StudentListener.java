package com.naclo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.naclo.pojo.Student;
import com.naclo.service.StudentService;
import com.naclo.service.impl.StudentServiceImpl;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class StudentListener extends AnalysisEventListener<Student> {
    Logger logger = Logger.getLogger(this.getClass());
    List<Student> studentList = new ArrayList<Student>();

    //每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 5;
    private StudentService studentService;

    public StudentListener() {
        studentService = new StudentServiceImpl();
    }

    @Override
    public void invoke(Student student, AnalysisContext context) {
        logger.info(JSON.toJSONString(student));
        studentList.add(student);
        if (studentList.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            studentList.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        logger.info("所有数据解析完成！");
    }

    private void saveData() {
        logger.info(studentList.size());
        for (Student student : studentList) {
            studentService.insertStudent(student);
        }
        logger.info("存储数据库成功！");
    }
}
