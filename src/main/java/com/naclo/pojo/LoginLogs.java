package com.naclo.pojo;

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
    Date opTime;
    String opIp;
}