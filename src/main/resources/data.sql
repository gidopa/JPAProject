-- Major 데이터 삽입
INSERT INTO Major (major_id, name) VALUES (1, '경영학과'), (2, '컴퓨터공학');

---- Student 데이터 삽입
INSERT INTO Student (student_id, hakbun, name, password, major_id, city, street, years, semester, status)
VALUES (1, 20000101, 'John Doe', 'password123', 1, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED'),
(2, 20000102, 'Kim Wongi', 'password124', 2, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED'),
(3, 20240101, 'Wongi Kim', 'password125', 1, 'Cityville', '123 Main St', 2024, 2, 'ENROLLED');

-- Credit 데이터 삽입
INSERT INTO Credit (credit_id, credit, student_id) VALUES (1, 4.0, 1), (2, 1.8, 2), (3, 2.0, 3);

-- Professor 테이터 삽입
INSERT INTO Professor(professor_id, login_id, password, name, city, street, major_id)
VALUES (1, 33330101, 'password123', 'pro', 'Cityville', '123 Main St', 1),
(2, 33330102, 'password124', 'pro2', 'Cityville', '123 Main St', 1);

--VALUES (3, 20000103, 'Gido', 'password125', 1, 'Cityville', '123 Main St', 1, 2024, 2, 'ENROLLED'),
--VALUES (4, 20000104, 'Wonbo', 'password126', 1, 'Cityville', '123 Main St', 1, 2024, 2, 'ENROLLED'),
--VALUES (5, 20000105, 'Wongi', 'password127', 1, 'Cityville', '123 Main St', 1, 2024, 2, 'ENROLLED'),


--INSERT INTO Student (hakbun, name, password, major_id, city, street, credit_id, years, semester, status) VALUES
--(20000101, 'John Doe', 'password123', 1, 'Cityville', '123 Main St', 1, 2023, 1, 'ACTIVE'),
--(20010102, 'Jane Smith', 'password456', 2, 'Townsville', '456 Elm St', 2, 2022, 2, 'INACTIVE'),
--(20020103, 'Alice Johnson', 'password789', 3, 'Villagetown', '789 Oak St', 3, 2021, 1, 'ACTIVE'),
--(20030104, 'Bob Brown', 'password101', 4, 'Hamlet', '321 Pine St', 4, 2020, 2, 'INACTIVE'),
--(20040105, 'Charlie Davis', 'password202', 1, 'Metropolis', '654 Maple St', 1, 2019, 1, 'ACTIVE'),
--(20050106, 'Diana Evans', 'password303', 2, 'Smallville', '987 Cedar St', 2, 2018, 2, 'INACTIVE'),
--(20060107, 'Eve Harris', 'password404', 3, 'Lakeside', '432 Birch St', 3, 2017, 1, 'ACTIVE'),
--(20070108, 'Frank Green', 'password505', 4, 'Mountainview', '876 Spruce St', 4, 2016, 2, 'INACTIVE'),
--(20080109, 'Grace Lee', 'password606', 1, 'Rivertown', '543 Ash St', 1, 2015, 1, 'ACTIVE'),
--(20090110, 'Hank Miller', 'password707', 2, 'Seaside', '210 Redwood St', 2, 2014, 2, 'INACTIVE');