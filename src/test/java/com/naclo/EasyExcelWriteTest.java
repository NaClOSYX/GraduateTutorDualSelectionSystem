package com.naclo;

import com.alibaba.excel.EasyExcel;
import com.naclo.pojo.DemoData;
import com.naclo.pojo.Student;
import com.naclo.service.StudentService;
import com.naclo.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class EasyExcelWriteTest {
    StudentService studentService = new StudentServiceImpl();

    @Test
    public void writeTest() {
        //实现excel写的操作
        //1 设置写入文件夹地址和excel文件名称
        String filename = "D:\\MyPrograms\\Java\\IdeaProjects\\GraduateTutorDualSelectionSystem\\write.xlsx";
        // 2 调用easyexcel里面的方法实现写操作
        // write方法两个参数：第一个参数文件路径名称，第二个参数实体类class
        EasyExcel.write(filename, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    @Test
    public void writeTest2() {
        List<Student> students = studentService.queryAllStudents();
        String filename = "D:\\MyPrograms\\Java\\IdeaProjects\\GraduateTutorDualSelectionSystem\\student.xlsx";
        EasyExcel.write(filename, Student.class).sheet("学生列表").doWrite(students);
    }

    //创建方法返回list集合
    private static List<DemoData> getData() {
        List<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setSno(i);
            data.setSname("lucy" + i);
            list.add(data);
        }
        return list;
    }
}