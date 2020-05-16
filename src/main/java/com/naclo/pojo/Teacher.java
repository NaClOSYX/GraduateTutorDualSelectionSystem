package com.naclo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    @ExcelProperty(value = "工号", index = 0)
    String teacherId;
    @ExcelProperty(value = "姓名", index = 1)
    String teacherName;
    @ExcelProperty(value = "密码", index = 2)
    String teacherPassword;
    @ExcelProperty(value = "专业", index = 3)
    String teacherMajor;
    @ExcelProperty(value = "简介", index = 4)
    String teacherIntroduce;
}
