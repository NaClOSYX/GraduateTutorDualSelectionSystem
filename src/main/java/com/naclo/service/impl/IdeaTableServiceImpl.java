package com.naclo.service.impl;


import com.naclo.pojo.Idea;
import com.naclo.pojo.IdeaTable;
import com.naclo.pojo.Student;
import com.naclo.service.IdeaService;
import com.naclo.service.IdeaTableService;
import com.naclo.service.StudentService;
import com.naclo.service.TeacherService;

import java.util.ArrayList;
import java.util.List;

public class IdeaTableServiceImpl implements IdeaTableService {

    IdeaService ideaService = new IdeaServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    StudentService studentService = new StudentServiceImpl();

    public List<IdeaTable> queryStudentIdeasByMajor(String major) {
        List<Student> studentList = new ArrayList<>();
        List<IdeaTable> ideaTableList = new ArrayList<>();
        if ("ALL" == major) {
            studentList = studentService.queryAllStudents();
        } else {
            studentList = studentService.queryStudentByMajor(major);
        }

        for (Student student : studentList) {
            IdeaTable ideaTable = new IdeaTable();
            ideaTable.setMajorName(student.getStudentMajor());
            ideaTable.setStudentId(student.getStudentId());
            ideaTable.setStudentName(student.getStudentName());
            ideaTable.setTeacherName1("未选择");
            ideaTable.setTeacherName2("未选择");
            ideaTable.setTeacherName3("未选择");
            String studentId = student.getStudentId();
            List<Idea> ideaList = ideaService.queryIdeasByStudentId(studentId);
            int size = ideaList.size();
            switch (size) {
                case 3:
                    ideaTable.setTeacherName2(teacherService.queryTeacherById(ideaList.get(2).getTeacherId()).getTeacherName());
                case 2:
                    ideaTable.setTeacherName2(teacherService.queryTeacherById(ideaList.get(1).getTeacherId()).getTeacherName());
                case 1:
                    ideaTable.setTeacherName1(teacherService.queryTeacherById(ideaList.get(0).getTeacherId()).getTeacherName());
                default:
            }
            ideaTableList.add(ideaTable);
        }
        return ideaTableList;
    }
}
