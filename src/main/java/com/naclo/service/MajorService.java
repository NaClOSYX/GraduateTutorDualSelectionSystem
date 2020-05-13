package com.naclo.service;


import com.naclo.pojo.Major;

import java.sql.Connection;
import java.util.List;

public interface MajorService {
    //获取所有专业
    List<Major> queryAllMajors();
}
