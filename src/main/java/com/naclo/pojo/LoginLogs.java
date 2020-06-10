package com.naclo.pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginLogs {
    int logId;
    String userId;
    String userRole;
    String userOp;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    Date opTime;
    String opIp;
}