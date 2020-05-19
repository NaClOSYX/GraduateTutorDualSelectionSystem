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
}
