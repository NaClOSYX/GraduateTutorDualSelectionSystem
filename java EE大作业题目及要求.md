# java EE大作业题目及要求

自己独立完成，任何人不得将自己的报告与别人共享（不得复制拷贝）

## 题目：研究生导师双选系统 B/S架构

* 研究生入学时需要选择自己的导师，导师也需要选择学生；管理员（各个学院相应专业）也需要管理学生，导师数据，最终确定学生导师

* 每个研究生选导师有三个志愿；导师所带学生上限由管理员设定；

## 系统用户

* 学生：
  * 学号、姓名、密码、所报考专业；
  * 选择导师（三个志愿必填），查看导师选择情况，最终导师；
  * 个人信息维护等；
* 导师：。。。；依据上限，选定学生（已经选择了该导师的学生）

* 管理员（学院级管理员）：
  * 学生基本信息批量导入（单个录入），维护；
  * 导师数据批量导入（单个录入），维护；
  * 专业，设定导师上限；
  * 查看学生选择导师情况，导师选择学生情况，最终确定学生导师；（只限于管理本院的招生专业）
  * 导出志愿表，选择导师最终结果（只限于管理本院的招生专业）
* 研究生院管理员：
  * 管理（增加，维护）学院管理员；
  * 查看学生选择导师情况，导师选择学生情况，最终确定学生导师；（只限于管理本院的招生专业）
  * 学生基本信息批量导入（单个录入），维护；
  * 导师数据批量导入（单个录入），维护；
  * 导出志愿表，选择导师最终结果

系统目标愿景

系统需求分析（最终使用者描述需求，功能性需求，非功能性（并发，安全，可靠，日志，事务））

* 自然语言描述及使用UML，用例图，。。。

系统设计（概要设计、详细设计）

* 数据库架构
* 技术架构设计MVC

系统测试、部署

* 功能











### 数据库架构

学生（学号，姓名，密码，专业）

老师（工号，姓名，密码，专业）

管理员（工号，密码，专业）

专业（编号，专业）

学生意向表（学号，工号，专业号，时间，状态）



view

导师表（学号，工号，专业号）







```sql
#学生表
DROP TABLE IF EXISTS student;
CREATE TABLE student
(
    studentId       varchar(10) PRIMARY KEY,
    studentName     varchar(30),
    studentPassword varchar(100),
    studentMajor    varchar(30)
);
#教师表
DROP TABLE IF EXISTS teacher;
CREATE TABLE teacher
(
    teacherId        varchar(10) PRIMARY KEY,
    teacherName      varchar(30),
    teacherPassword  varchar(100),
    teacherMajor     varchar(30),
    teacherINtroduce varchar(255)
);

#管理员表，major为0的是研究室院管理员
DROP TABLE IF EXISTS admin;
CREATE TABLE admin
(
    adminId       varchar(10) PRIMARY KEY,
    adminPassword varchar(100),
    adminMajor    varchar(30)
);

#专业表
DROP TABLE IF EXISTS major;
CREATE TABLE major
(
    majorId    int(2) PRIMARY KEY,
    majorName  varchar(30),
    studentMax int(2)
);


DROP VIEW IF EXISTS user;
CREATE VIEW user AS
SELECT studentId Id,studentPassword password,studentMajor majior,'学生' AS role FROM student
UNION
SELECT teacherId Id,teacherPassword password,teacherMajor majior,'导师' AS role FROM teacher
UNION
SELECT adminId Id,adminPassword password,adminMajor majior,'管理员' AS role FROM admin;

SELECT * from user;



INSERT INTO student VALUES('20171422','史杨霄',MD5('20171422'),'软件工程');
INSERT INTO student VALUES('20170001','张三',MD5('20170001'),'电气工程');
INSERT INTO student VALUES('20170002','李四',MD5('20170002'),'控制工程');
INSERT INTO student VALUES('20170003','王五',MD5('20170003'),'计算机技术');


INSERT INTO teacher VALUES('1234567890','周平',MD5('1234567890'),'软件工程','周平，最屌的老师');
INSERT INTO teacher VALUES('1111111111','张四',MD5('1111111111'),'电气工程','张四，最屌的老师');
INSERT INTO teacher VALUES('2222222222','李三',MD5('2222222222'),'计算机技术','李三，最屌的老师');
INSERT INTO teacher VALUES('3333333333','赵七',MD5('3333333333'),'控制工程','赵七，最屌的老师');

INSERT INTO admin VALUES('admin',MD5('admin'),'软件工程');
INSERT INTO admin VALUES('sadmin',MD5('cadmin'),'All');


Insert into major values(0,'ALL',0);
Insert into major values(1,'电气工程',0);
Insert into major values(2,'动力工程',0);
Insert into major values(3,'控制工程',0);
Insert into major values(4,'工程管理',0);
Insert into major values(5,'物理学',0);
Insert into major values(6,'化学工程与技术',0);
Insert into major values(7,'信息与通信工程',0);
Insert into major values(8,'计算机技术',0);
Insert into major values(9,'软件工程',0);
```

