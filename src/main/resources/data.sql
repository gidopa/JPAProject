-- Major 데이터 삽입
INSERT INTO Major ( major_id, name) VALUES ( 2,'경영학과'), ( 1,'컴퓨터공학'), (3, '경제학과');

-- Student 데이터 삽입
INSERT INTO student (student_id, hakbun, name, password, major_id, city, street,  years, semester)
VALUES (1, 20000101, 'John Doe', 'password123', 1, 'Cityville', '123 Main St', 2024, 2),
       (2, 20000102, 'Kim Wongi', 'password124', 2, 'Cityville', '123 Main St',  2024, 2),
       (3, 20240103, 'student3', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (4, 20240104, 'student4', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (5, 20240105, 'student5', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (6, 20240106, 'student6', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (7, 20240107, 'student7', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (8, 20240108, 'student8', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (10, 20240109, 'student10', 'password125', 1, 'Cityville', '123 Main St',  2024, 2),
       (9, 20240110, 'student9', 'password125', 1, 'Cityville', '123 Main St',  2024, 2);

-- StudentHistory 데이터 삽입
/*INSERT INTO student_history (student_history_id, student_id, old_Status, new_Status)
VALUES (1, 1, 'ENROLLED', 'ENROLLED'),
       (2, 2, 'ENROLLED', 'ENROLLED'),
       (3, 3, 'ENROLLED', 'ENROLLED'),
       (4, 4, 'ENROLLED', 'ENROLLED'),
       (5, 5, 'ENROLLED', 'ENROLLED'),
       (6, 6, 'ENROLLED', 'ONLEAVE'),
       (7, 1, 'ENROLLED', 'ONLEAVE');*/

-- Credit 데이터 삽입
INSERT INTO Credit (credit_id,student_id, credit) VALUES (1, 1,4.0), (2, 2,1.8), (3, 3,2.0);

-- Professor 데이터 삽입
INSERT INTO Professor (professor_id, login_id, password, name, city, street, major_id)
VALUES (1, 33330101, 'password123', 'pro', 'Cityville', '123 Main St', 1),
       (2, 33330102, 'password124', 'pro2', 'Cityville', '123 Main St', 1);

-- Course 데이터 삽입
INSERT INTO course (course_name, credit, mid_term_weight, final_term_weight, report_weight, professor_id, category, semester, years, file_path)
VALUES ('JAVA', 3, 0.3, 0.3, 0.4, 1, 'MAJOR', 1, 2024, NULL),
       ('Python', 3, 0.4, 0.4, 0.2, 2, 'MAJOR', 1, 2024, NULL);

-- Enroll 데이터 삽입
insert into enroll (course_id, student_id, grade_type)
values (1, 1, 'APLUS'),
       (1, 2, 'BPLUS'),
       (1, 3, 'AZERO'),
       (1, 4, 'CPLUS'),
       (1, 5, 'APLUS'),
       (1, 6, 'APLUS'),
       (1, 7, 'APLUS'),
       (1, 8, 'APLUS'),
       (1, 9, 'APLUS'),
       (1, 10, 'APLUS'),
       (2, 1, 'APLUS');

insert into assessment (enroll_id, assessment_id, mid_term_score, final_term_score, report_score, total_score)
values ( 1,1,90,90,90 ,270),
       ( 2,2,90,95,90,275 ),
       ( 3,3,91,96,90 ,277),
       ( 4,4,92,93,90,275 ),
       ( 5,5,93,91,90,null ),
       ( 6,6,94,97,90,null ),
       ( 7,7,95,96,90,null ),
       ( 8,8,96,93,90 ,null),
       ( 9,9,97,94,90,null),
       ( 10,10,98,97,90 ,null),
       ( 11,11,98,97,90 ,null);




