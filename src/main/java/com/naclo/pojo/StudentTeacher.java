package com.naclo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentTeacher {
    String majorName;
    String studentId;
    String studentName;
    String teacherId;
    String teacherName;
}
