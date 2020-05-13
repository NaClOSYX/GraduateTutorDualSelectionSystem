package com.naclo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    String teacherId;
    String teacherName;
    String teacherPassword;
    String teacherMajor;
    String teacherINtroduce;
}
