INSERT INTO student
VALUES ('20171422', '史杨霄', MD5('20171422'), '软件工程');
INSERT INTO student
VALUES ('20170001', '张三', MD5('20170001'), '电气工程');
INSERT INTO student
VALUES ('20170002', '李四', MD5('20170002'), '控制工程');
INSERT INTO student
VALUES ('20170003', '王五', MD5('20170003'), '计算机技术');


INSERT INTO teacher
VALUES ('1234567890', '周平', MD5('1234567890'), '软件工程', '周平，最屌的老师');
INSERT INTO teacher
VALUES ('1111111111', '张四', MD5('1111111111'), '电气工程', '张四，最屌的老师');
INSERT INTO teacher
VALUES ('2222222222', '李三', MD5('2222222222'), '计算机技术', '李三，最屌的老师');
INSERT INTO teacher
VALUES ('3333333333', '赵七', MD5('3333333333'), '控制工程', '赵七，最屌的老师');

INSERT INTO admin
VALUES ('admin', MD5('admin'), '软件工程');
INSERT INTO admin
VALUES ('sadmin', MD5('cadmin'), 'All');


Insert into major
values (0, 'ALL', 0);
Insert into major
values (1, '电气工程', 0);
Insert into major
values (2, '动力工程', 0);
Insert into major
values (3, '控制工程', 0);
Insert into major
values (4, '工程管理', 0);
Insert into major
values (5, '物理学', 0);
Insert into major
values (6, '化学工程与技术', 0);
Insert into major
values (7, '信息与通信工程', 0);
Insert into major
values (8, '计算机技术', 0);
Insert into major
values (9, '软件工程', 0);