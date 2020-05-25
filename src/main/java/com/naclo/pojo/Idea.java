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
    //0 学生已加入购物车
    //1 学生已选择
    //2 教师已选择
    //3 教师已拒绝
    //4 管理员已最终确定
    //5 管理员已否决
}
