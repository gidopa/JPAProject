-- Major 데이터 삽입
INSERT INTO Major (major_id, name) VALUES (1, '경영학과'), (2, '컴퓨터공학'), (3, '경제학과');

---- Student 데이터 삽입
INSERT INTO Student (student_id, hakbun, name, password, major_id, city, street, years, semester, status)
VALUES (1, 20000101, 'John Doe', 'password123', 1, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED'),
       (2, 20000102, 'Kim Wongi', 'password124', 2, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED'),
       (3, 20240101, 'Wongi Kim', 'password125', 1, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED');

-- Credit 데이터 삽입
INSERT INTO Credit (credit_id, credit, student_id) VALUES (1, 4.0, 1), (2, 1.8, 2), (3, 2.0, 3);

INSERT INTO Professor(professor_id, login_id, password, name, city, street, major_id)
VALUES (1, 33330101, 'password123', 'Wongi Kim', 'Cityville', '123 Main St', 1),
       (2, 33330102, 'password124', 'Gido Park', 'Cityville', '123 Main St', 1),
       (3, 33330103, 'password122', 'Wonbo Jang', 'Cityville', '123 Main St', 1);

-- Course 데이터 삽입
INSERT INTO Course (course_name, professor_id, mid_term_weight, final_term_weight, report_weight, credit, category, years, semester) VALUES
    ('Introduction to Statistics', 2, 0.3, 0.5, 0.2, 3, 'MAJOR', 2024, 1);
INSERT INTO Course (course_name, professor_id, mid_term_weight, final_term_weight, report_weight, credit, category, years, semester) VALUES
    ('Financial Management', 1, 0.4, 0.4, 0.2, 3, 'MAJOR', 2024, 1);
INSERT INTO Course (course_name, professor_id, mid_term_weight, final_term_weight, report_weight, credit, category, years, semester) VALUES
    ('Microeconomics', 3, 0.4, 0.5, 0.1, 3, 'MAJOR', 2024, 1);