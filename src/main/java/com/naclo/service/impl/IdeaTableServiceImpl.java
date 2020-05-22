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
import java.util.Map;

public class IdeaTableServiceImpl implements IdeaTableService {

    IdeaService ideaService = new IdeaServiceImpl();
    TeacherService teacherService = new TeacherServiceImpl();
    StudentService studentService = new StudentServiceImpl();

    public List<IdeaTable> queryStudentIdeasByMajor(String major) {
        List<Student> studentList = new ArrayList<>();
        List<IdeaTable> ideaTableList = new ArrayList<>();
        List<Idea> ideaList = new ArrayList<>();
        Map<String, String> teacherMap = teacherService.queryTeachersMapByMajor(major);
        if ("ALL" == major) {
            studentList = studentService.queryAllStudents();
        } else {
            studentList = studentService.queryStudentByMajor(major);
        }
        List<Idea> ideaListAll = ideaService.queryAllIdeas();
        for (Student student : studentList) {
            ideaList = new ArrayList<>();
            String studentId = student.getStudentId();
            for (Idea idea : ideaListAll) {
                if (studentId.equals(idea.getStudentId())) {
                    ideaList.add(idea);
                }
            }
            IdeaTable ideaTable = new IdeaTable();
            ideaTable.setMajorName(student.getStudentMajor());
            ideaTable.setStudentId(studentId);
            ideaTable.setStudentName(student.getStudentName());
            ideaTable.setTeacherName1("未选择");
            ideaTable.setTeacherName2("未选择");
            ideaTable.setTeacherName3("未选择");
            int size = ideaList.size();
            switch (size) {
                case 3:
                    ideaTable.setTeacherName3(teacherMap.get(ideaList.get(2).getTeacherId()));
                case 2:
                    ideaTable.setTeacherName2(teacherMap.get(ideaList.get(1).getTeacherId()));
                case 1:
                    ideaTable.setTeacherName1(teacherMap.get(ideaList.get(0).getTeacherId()));
                default:
            }
            ideaTableList.add(ideaTable);
        }
        return ideaTableList;
    }
}
