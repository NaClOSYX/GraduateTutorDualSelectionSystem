package com.naclo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Idea {
    int ideaId;
    String majorName;
    String studentId;
    String teacherId;
    Date time;
    int state;
    //1 学生选择
    //2 教师选择
    //3 教师拒绝
    //4 已有教师选择
    //5 管理确定
    //6 管理否决
}
