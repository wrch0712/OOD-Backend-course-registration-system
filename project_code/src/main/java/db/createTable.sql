/*
This is only a test for connecting MySQL for lab 2 deliverable 3
I just created a simple table Student with 3 columns (id, last_name, first_name) for the test. (See .sql file)
The test runs well
I haven't completed the database design for final project, so I didn't show it here
 */

use regie;

create table if not exists User
(
    user_id integer primary key,
    last_name  varchar(15),
    first_name varchar(15),
    password varchar(20),
    type varchar(15)
);

create table if not exists Building
(
    building_id integer primary key auto_increment,
    building_name  varchar(20),
    address varchar(50),
    post_code varchar(10)
);

create table if not exists Room
(
    room_id integer primary key auto_increment,
    room_name  varchar(20),
    building_id integer,
    floor integer,
    foreign key (building_id) references Building (building_id)
);

create table if not exists Restriction
(
    restriction_id integer primary key auto_increment,
    restriction_name varchar(50)
);

create table if not exists Course
(
    course_id integer primary key auto_increment,
    course_name varchar(20),
    instructor_id integer,
    room_id integer,
    day varchar(10),
    time varchar(20),
    status varchar(20),
    foreign key (instructor_id) references User (user_id),
    foreign key (room_id) references Room (room_id)
);

create table if not exists StudentCourse
(
    studentCourse_id integer primary key auto_increment,
    student_id integer,
    course_id integer,
    letter_grade varchar(5),
    foreign key (student_id) references User (user_id),
    foreign key (course_id) references Course (course_id)
);


create table if not exists StudentRestriction
(
    studentRestriction_id integer primary key auto_increment,
    student_id integer,
    restriction_id integer,
    foreign key (student_id) references User (user_id),
    foreign key (restriction_id) references Restriction (restriction_id)
);

-- Insert sample data into tables
-- Inserting sample data into the User table
INSERT INTO User (user_id, last_name, first_name, password, type)
VALUES
    (123101, 'Smith', 'John', 'password1', 'student'),
    (123102, 'Johnson', 'Sarah', 'password2', 'student'),
    (123103, 'Garcia', 'Maria', 'password3', 'student'),
    (123104, 'Brown', 'David', 'password4', 'student'),
    (123105, 'Jones', 'Emily', 'password5', 'student'),
    (101501, 'Wilson', 'Daniel', 'password1001', 'instructor'),
    (101502, 'Anderson', 'Emma', 'password1002', 'instructor'),
    (101503, 'Martinez', 'Miguel', 'password1003', 'instructor'),
    (101504, 'Jackson', 'Avery', 'password1004', 'instructor'),
    (101505, 'Gomez', 'Sofia', 'password1005', 'instructor'),
    (101506, 'Hernandez', 'Luis', 'password1006', 'instructor'),
    (101507, 'Robinson', 'Ava', 'password1007', 'instructor'),
    (101508, 'Perez', 'Juan', 'password1008', 'instructor'),
    (345601, 'Doe', 'John', 'password701', 'admin'),
    (345602, 'Smith', 'Sarah', 'password702', 'admin'),
    (345603, 'Garcia', 'Maria', 'password703', 'admin');


-- Inserting sample data into the Building table
INSERT INTO Building (building_id, building_name, address, post_code)
VALUES
    (1, 'Science Building', '123 Main St', '12345'),
    (2, 'Arts Building', '456 Elm St', '24252'),
    (3, 'Engineering Building', '789 Oak St', '31323'),
    (4, 'Business Building', '321 Maple St', '40404'),
    (5, 'Library Building', '654 Birch St', '56567'),
    (6, 'Sports Building', '987 Pine St', '66666'),
    (7, 'Music Building', '111 Cedar St', '78934'),
    (8, 'Theater Building', '222 Walnut St', '88866');

-- Inserting sample data into the Room table
INSERT INTO Room (room_id, room_name, building_id, floor)
VALUES
    (1, 'Room 101', 2, 1),
    (2, 'Room 102', 2, 1),
    (3, 'Room 103', 3, 1),
    (4, 'Room 201', 4, 2),
    (5, 'Room 202', 5, 2),
    (6, 'Room 203', 5, 2),
    (7, 'Room 301', 7, 3),
    (8, 'Room 401', 8, 4);

-- Inserting sample data into the Restriction table
INSERT INTO Restriction (restriction_id, restriction_name)
VALUES
    (1, 'library restrictions'),
    (2, 'missing immunization records'),
    (3, 'failure to meet with academic advisor'),
    (4, 'failure to pay tuition bill');

-- Inserting sample data into the Course table
INSERT INTO Course (course_id, course_name, instructor_id, room_id, day, time, status)
VALUES
    (1, 'Mathematics', 101502, 1, 'MWF', '9-10AM', 'On Going'),
    (2, 'Chemistry', 101501, 5, 'TR', '11AM-12:30PM', 'Completed'),
    (3, 'Physics', 101503, 6, 'MWF', '1-2PM', 'Completed'),
    (4, 'English', 101504, 6, 'TR', '9-10:30AM', 'In Coming'),
    (5, 'Computer Science', 101506, 6, 'MWF', '10-11AM', 'Completed'),
    (6, 'History', 101505, 5, 'TR', '1-2:30PM', 'Completed'),
    (7, 'Biology', 101501, 1, 'MWF', '2-3PM', 'On Going'),
    (8, 'Art', 101504, 4, 'TR', '3-4:30PM', 'Completed'),
    (9, 'Economics', 101507, 1, 'MWF', '11AM-12PM', 'In Coming'),
    (10, 'Psychology', 101504, 3, 'TR', '9-10:30AM', 'Completed'),
    (11, 'Sociology', 101507, 7, 'MWF', '2-3PM', 'Completed'),
    (12, 'Political Science', 101504, 4, 'TR', '10-11:30AM', 'In Coming'),
    (13, 'Philosophy', 101508, 3, 'MWF', '3-4PM', 'On Going'),
    (14, 'Anthropology', 101508, 5, 'TR', '1-2:30PM', 'Completed'),
    (15, 'Chinese', 101507, 6, 'TR', '4-5:30PM', 'On Going'),
    (16, 'Writing', 101504, 7, 'TR', '1-2:30PM', 'On Going'),
    (17, 'Law', 101506, 1, 'MWF', '10:30-11PM', 'On Going');

-- Insert sample data into StudentCourse table
INSERT INTO StudentCourse (studentCourse_id, student_id, course_id, letter_grade)
VALUES
    (1, 123101, 1, 'A'),
    (2, 123101, 3, 'B'),
    (3, 123101, 5, 'A-'),
    (4, 123101, 7, NULL),
    (5, 123101, 9, NULL),
    (6, 123101, 11, 'B+'),
    (7, 123101, 13, NULL),
    (8, 123101, 15, 'B-'),
    (9, 123101, 17, 'A'),
    (10, 123101, 2, 'A-'),
    (11, 123101, 4, NULL),
    (12, 123101, 6, 'C+'),
    (13, 123101, 8, 'B'),
    (14, 123101, 10, NULL),
    (15, 123101, 12, NULL),
    (16, 123101, 14, 'A'),
    (17, 123101, 16, NULL),
    (18, 123102, 2, 'A-'),
    (19, 123102, 4, 'B+'),
    (20, 123102, 6, NULL),
    (21, 123102, 8, NULL),
    (22, 123102, 10, 'A'),
    (23, 123102, 12, 'C'),
    (24, 123102, 14, NULL),
    (25, 123102, 16, 'A-'),
    (26, 123102, 1, 'B'),
    (27, 123102, 3, NULL),
    (28, 123102, 5, NULL),
    (29, 123102, 7, 'C+'),
    (30, 123102, 9, 'A'),
    (31, 123102, 11, 'B'),
    (32, 123102, 13, NULL),
    (33, 123102, 15, 'B-'),
    (34, 123102, 17, 'A'),
    (35, 123103, 3, 'A'),
    (36, 123103, 5, 'B+'),
    (37, 123103, 7, NULL),
    (38, 123103, 9, 'B'),
    (39, 123103, 11, 'A-'),
    (40, 123103, 13, 'B-'),
    (41, 123103, 15, NULL),
    (42, 123103, 17, NULL),
    (43, 123103, 2, NULL),
    (44, 123103, 4, 'C'),
    (45, 123103, 6, 'B'),
    (46, 123104, 1, NULL),
    (47, 123104, 2, NULL),
    (48, 123104, 3, 'C'),
    (49, 123104, 5, 'B'),
    (50, 123104, 6, 'A'),
    (51, 123104, 8, NULL),
    (52, 123104, 9, NULL),
    (53, 123104, 10, NULL),
    (54, 123104, 11, NULL),
    (55, 123104, 12, 'B'),
    (56, 123105, 1, 'B'),
    (57, 123105, 2, NULL),
    (58, 123105, 4, 'A'),
    (59, 123105, 5, NULL),
    (60, 123105, 6, 'B'),
    (61, 123105, 7, 'C'),
    (62, 123105, 9, NULL),
    (63, 123105, 10, 'A'),
    (64, 123105, 11, NULL),
    (65, 123105, 13, NULL),
    (66, 123105, 14, NULL),
    (67, 123105, 15, 'B'),
    (68, 123105, 17, 'A');

-- Insert sample data into StudentRestriction table
INSERT INTO StudentRestriction (studentRestriction_id, student_id, restriction_id)
VALUES
    (1, 123103, 1),
    (2, 123103, 2),
    (3, 123104, 4);

