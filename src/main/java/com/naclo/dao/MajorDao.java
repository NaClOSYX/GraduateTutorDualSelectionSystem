package com.naclo.dao;


import com.naclo.pojo.Major;

import java.sql.Connection;
import java.util.List;

public interface MajorDao {
    //获取所有专业
    List<Major> queryAllMajors(Connection connection);

    //获取所有专业去除All
    List<Major> queryAllMajorsExceptALL(Connection connection);

    //根据专业名查询专业
    List<Major> queryMajorByName(Connection connection, String name);

    //根据专业编号查询专业
    Major queryMajorById(Connection connection, int id);

    //根据专业名查询专业最大人数
    int queryMajorMaxStudents(Connection connection, String name);

    //插入专业
    int insertMajor(Connection connection, Major major);

    //修改专业信息
    int updateMajor(Connection connection, Major major);

    //根据id删除专业
    int deleteMajorById(Connection connection, int id);
}
