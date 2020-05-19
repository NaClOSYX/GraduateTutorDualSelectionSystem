package com.naclo.service;


import com.naclo.pojo.Major;

import java.util.List;

public interface MajorService {
    //获取所有专业
    List<Major> queryAllMajors();

    //根据专业名查询专业
    List<Major> queryMajorByName(String name);
}
