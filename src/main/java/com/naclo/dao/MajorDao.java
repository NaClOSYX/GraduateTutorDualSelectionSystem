package com.naclo.dao;


import com.naclo.pojo.Major;

import java.sql.Connection;
import java.util.List;

public interface MajorDao {
    //获取所有专业
    List<Major> queryAllMajors(Connection connection);

    //根据专业名查询专业
    List<Major> queryMajorByName(Connection connection, String name);
}
