DROP VIEW IF EXISTS user;
CREATE VIEW user AS
SELECT studentId Id, studentPassword password, studentMajor majior, '学生' AS role
FROM student
UNION
SELECT teacherId Id, teacherPassword password, teacherMajor majior, '导师' AS role
FROM teacher
UNION
SELECT adminId Id, adminPassword password, adminMajor majior, '管理员' AS role
FROM admin;

SELECT *
from user;


DROP VIEW IF EXISTS studentTeacherTable;
create view studentTeacherTable AS
select I.majorName   majorName,
       S.studentId   studentId,
       S.studentName studentName,
       T.teacherId   teacherId,
       T.teacherName teacherName
from student S,
     teacher T,
     idea I
where I.studentId = S.studentId
  and I.teacherId = T.teacherId
  and I.state = 2;