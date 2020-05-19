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
    teacherIntroduce varchar(255)
);

#管理员表，major为ALL的是研究生院管理员
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

#志愿表
DROP TABLE IF EXISTS idea;
CREATE TABLE idea
(
    ideaId    int(10) auto_increment primary key,
    majorName varchar(30),
    studentId varchar(10),
    teacherId varchar(10),
    time      datetime,
    state     int(1),
    foreign key (studentId) references student (studentId),
    foreign key (teacherId) references teacher (teacherId)
);