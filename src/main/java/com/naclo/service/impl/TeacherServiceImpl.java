package com.naclo.service.impl;

import com.naclo.dao.TeacherDao;
import com.naclo.dao.impl.TeacherDaoImpl;
import com.naclo.pojo.Teacher;
import com.naclo.service.TeacherService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;


public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();

    @Override
    public List<Teacher> queryAllTeachers() {
        Connection connection = DBUtil.getConnection();
        List<Teacher> teacherList = teacherDao.queryAllTeachers(connection);
        DBUtil.closeResource(connection, null, null);
        return teacherList;
    }

    @Override
    public Teacher queryTeacherById(String id) {
        Connection connection = DBUtil.getConnection();
        Teacher teacher = teacherDao.queryTeacherById(connection, id);
        DBUtil.closeResource(connection, null, null);
        return teacher;
    }

    @Override
    public List<Teacher> queryTeacherByMajor(String major) {
        Connection connection = DBUtil.getConnection();
        List<Teacher> teacherList = teacherDao.queryTeacherByMajor(connection, major);
        DBUtil.closeResource(connection, null, null);
        return teacherList;
    }

    @Override
    public List<Teacher> queryTeacherByName(String name) {
        Connection connection = DBUtil.getConnection();
        List<Teacher> teacherList = teacherDao.queryTeacherByName(connection, name);
        DBUtil.closeResource(connection, null, null);
        return teacherList;
    }

    @Override
    public List<Teacher> queryAllTeachersLimit(int startIndex, int pageSize) {
        Connection connection = DBUtil.getConnection();
        List<Teacher> teacherList = teacherDao.queryAllTeachersLimit(connection, startIndex, pageSize);
        DBUtil.closeResource(connection, null, null);
        return teacherList;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        Connection connection = DBUtil.getConnection();
        int i = teacherDao.insertTeacher(connection, teacher);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTeacherPasswordById(String id, String newPwd) {
        Connection connection = DBUtil.getConnection();
        int i = teacherDao.updateTeacherPasswordById(connection, id, newPwd);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateTeacherById(Teacher teacher) {
        Connection connection = DBUtil.getConnection();
        int i = teacherDao.updateTeacherById(connection, teacher);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteTeacherById(String id) {
        Connection connection = DBUtil.getConnection();
        int i = teacherDao.deleteTeacherById(connection, id);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
