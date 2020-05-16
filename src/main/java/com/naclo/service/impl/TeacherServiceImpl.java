package com.naclo.service.impl;

import com.naclo.dao.TeacherDao;
import com.naclo.dao.impl.TeacherDaoImpl;
import com.naclo.service.TeacherService;


public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();
}
