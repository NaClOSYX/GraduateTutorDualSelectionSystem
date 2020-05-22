package com.naclo.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    @ExcelProperty(value = "管理员编号", index = 0)
    String adminId;
    @ExcelProperty(value = "管理员密码", index = 1)
    String adminPassword;
    @ExcelProperty(value = "管理员专业", index = 2)
    String adminMajor;
}
