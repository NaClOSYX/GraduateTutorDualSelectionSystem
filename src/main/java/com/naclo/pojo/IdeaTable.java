package com.naclo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdeaTable {
    @ExcelProperty(value = "学号", index = 0)
    String studentId;
    @ExcelProperty(value = "学生姓名", index = 1)
    String studentName;
    @ExcelProperty(value = "专业", index = 2)
    String majorName;
    @ExcelProperty(value = "志愿1", index = 3)
    String teacherName1;
    @ExcelProperty(value = "志愿2", index = 4)
    String teacherName2;
    @ExcelProperty(value = "志愿3", index = 5)
    String teacherName3;
}
