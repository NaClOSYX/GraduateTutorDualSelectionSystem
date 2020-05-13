package com.naclo.service.impl;

import com.naclo.dao.MajorDao;
import com.naclo.dao.impl.MajorDaoImpl;
import com.naclo.pojo.Major;
import com.naclo.pojo.Student;
import com.naclo.service.MajorService;
import com.naclo.utils.DBUtil;

import java.sql.Connection;
import java.util.List;

/**
 * @Author NaClO
 * @create 2020/5/9 9:37
 */
public class MajorServiceImpl implements MajorService {
    MajorDao majorDao = new MajorDaoImpl();

    @Override
    public List<Major> queryAllMajors() {
        Connection connection = connection = DBUtil.getConnection();
        List<Major> majorList = majorDao.queryAllMajors(connection);
        DBUtil.closeResource(connection, null, null);
        return majorList;
    }
}
