package com.naclo.service.impl;

import com.naclo.dao.TeacherDao;
import com.naclo.dao.impl.TeacherDaoImpl;
import com.naclo.pojo.Teacher;
import com.naclo.service.TeacherService;

/**
 * @Author NaClO
 * @create 2020/5/7 16:42
 */
public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();
}
