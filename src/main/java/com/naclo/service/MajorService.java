package com.naclo.service;


import com.naclo.pojo.Major;

import java.util.List;

public interface MajorService {
    //获取所有专业
    List<Major> queryAllMajors();

    //获取所有专业去除All
    List<Major> queryAllMajorsExceptALL();

    //根据专业名查询专业
    List<Major> queryMajorByName(String name);

    //根据专业编号查询专业
    Major queryMajorById(int id);

    //根据专业名查询专业最大人数
    int queryMajorMaxStudents(String name);

    //插入专业
    boolean insertMajor(Major major);

    //修改专业信息
    boolean updateMajor(Major major);

    //根据id删除专业
    boolean deleteMajorById(int id);
}
