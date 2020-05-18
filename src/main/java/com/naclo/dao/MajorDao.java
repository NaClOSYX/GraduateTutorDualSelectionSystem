package com.naclo.dao;


import com.naclo.pojo.Major;

import java.sql.Connection;
import java.util.List;

public interface MajorDao {
    //获取所有专业
    List<Major> queryAllMajors(Connection connection);
}
