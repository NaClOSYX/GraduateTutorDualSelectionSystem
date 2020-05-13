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