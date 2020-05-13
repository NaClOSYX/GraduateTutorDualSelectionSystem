package com.naclo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @ExcelProperty(value = "学号", index = 0)
    String studentId;
    @ExcelProperty(value = "姓名", index = 1)
    String studentName;
    @ExcelProperty(value = "密码", index = 2)
    String studentPassword;
    @ExcelProperty(value = "专业", index = 3)
    String studentMajor;
}
