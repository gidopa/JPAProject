-- Major 데이터 삽입
INSERT INTO Major ( major_id, name) VALUES ( 2,'경영학과'), ( 1,'컴퓨터공학');

-- Student 데이터 삽입
INSERT INTO student (student_id, hakbun, name, password, major_id, city, street,  years, semester, status)
VALUES (1, 20000101, 'John Doe', 'password123', 1, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED'),
       (2, 20000102, 'Kim Wongi', 'password124', 2, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (3, 20240103, 'student3', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (4, 20240104, 'student4', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (5, 20240105, 'student5', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (6, 20240106, 'student6', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (7, 20240107, 'student7', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (8, 20240108, 'student8', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (10, 20240109, 'student10', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED'),
       (9, 20240110, 'student9', 'password125', 1, 'Cityville', '123 Main St',  2024, 2, 'ENROLLED');

-- Credit 데이터 삽입
INSERT INTO Credit (credit_id,student_id, credit) VALUES (1, 1,4.0), (2, 2,1.8), (3, 3,2.0);

-- Professor 데이터 삽입
INSERT INTO Professor (professor_id, login_id, password, name, city, street, major_id)
VALUES (1, 33330101, 'password123', 'pro', 'Cityville', '123 Main St', 1),
       (2, 33330102, 'password124', 'pro2', 'Cityville', '123 Main St', 1);

-- Course 데이터 삽입
insert into course (credit, mid_term_weight, final_term_weight, report_weight, course_id, professor_id, course_name)
values ( 3,0.3,0.3,0.4, 1, 1,'JAVA'),
       (3,0.4,0.4,0.2,2,2,'Python');

-- Enroll 데이터 삽입
insert into enroll (course_id, enroll_id, student_id, grade_type)
values ( 1, 1,1, 'APLUS'),
       ( 1, 2,2, 'BPLUS'),
       ( 1, 3,3, 'AZERO'),
       ( 1, 4,4, 'CPLUS'),
       ( 1, 5,5, 'APLUS'),
       ( 1, 6,6, 'APLUS'),
       ( 1, 7,7, 'APLUS'),
       ( 1, 8,8, 'APLUS'),
       ( 1, 9,9, 'APLUS'),
       ( 1, 10,10, 'DPLUS'),
       (2,11,1,'DPLUS');

insert into assessment (enroll_id, assessment_id, mid_term_score, final_term_score, report_score, total_score)
values ( 1,1,90,90,90 ,270),
       ( 2,2,91,91,91,275 ),
       ( 3,3,92,92,92 ,277),
       ( 4,4,93,93,93,275 ),
       ( 5,5,94,94,94,null ),
       ( 6,6,95,95,95,null ),
       ( 7,7,96,96,96,null ),
       ( 8,8,97,97,97 ,null),
       ( 9,9,98,98,98,null),
       ( 10,10,99,99,99 ,null),
       ( 11,11,98,97,90 ,null);




