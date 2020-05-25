package com.naclo.service.impl;

import com.naclo.dao.MajorDao;
import com.naclo.dao.impl.MajorDaoImpl;
import com.naclo.pojo.Major;
import com.naclo.service.MajorService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;


public class MajorServiceImpl implements MajorService {
    MajorDao majorDao = new MajorDaoImpl();

    @Override
    public List<Major> queryAllMajors() {
        Connection connection = DBUtil.getConnection();
        List<Major> majorList = majorDao.queryAllMajors(connection);
        DBUtil.closeResource(connection, null, null);
        return majorList;
    }

    @Override
    public List<Major> queryAllMajorsExceptALL() {
        Connection connection = DBUtil.getConnection();
        List<Major> majorList = majorDao.queryAllMajorsExceptALL(connection);
        DBUtil.closeResource(connection, null, null);
        return majorList;
    }

    @Override
    public List<Major> queryMajorByName(String name) {
        Connection connection = DBUtil.getConnection();
        List<Major> majorList = majorDao.queryMajorByName(connection, name);
        DBUtil.closeResource(connection, null, null);
        return majorList;
    }

    @Override
    public Major queryMajorById(int id) {
        Connection connection = DBUtil.getConnection();
        Major major = majorDao.queryMajorById(connection, id);
        DBUtil.closeResource(connection, null, null);
        return major;
    }

    @Override
    public int queryMajorMaxStudents(String name) {
        Connection connection = DBUtil.getConnection();
        int i = majorDao.queryMajorMaxStudents(connection, name);
        DBUtil.closeResource(connection, null, null);
        return i;
    }

    @Override
    public boolean insertMajor(Major major) {
        Connection connection = DBUtil.getConnection();
        int i = majorDao.insertMajor(connection, major);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean updateMajor(Major major) {
        Connection connection = DBUtil.getConnection();
        int i = majorDao.updateMajor(connection, major);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteMajorById(int id) {
        Connection connection = DBUtil.getConnection();
        int i = majorDao.deleteMajorById(connection, id);
        DBUtil.closeResource(connection, null, null);
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
