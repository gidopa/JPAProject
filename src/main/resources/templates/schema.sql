create table course (
        credit integer not null,
        semester integer,
        years integer,
        course_id bigint not null,
        professor_id bigint,
        course_name varchar(255),
        category enum ('GENERAL','MAJOR'),
        primary key (course_id)
    );

create table credit (
        credit float(24) not null,
        credit_id bigint not null,
        primary key (credit_id)
    );

create table enroll (
        course_id bigint,
        enroll_id bigint not null,
        student_id bigint,
        grade enum ('APLUS','AZERO','BPLUS','BZERO','CPLUS','CZERO','DPLUS','DZERO','F','INPROGRESS'),
        primary key (enroll_id)
    );

create table major (
        major_id bigint not null,
        name varchar(255),
        primary key (major_id)
    );

  create table professor (
        login_id bigint unique,
        major_id bigint,
        professor_id bigint not null,
        city varchar(255),
        name varchar(255),
        password varchar(255),
        street varchar(255),
        primary key (professor_id)
    );

create table student (
        semester integer,
        years integer,
        credit_id bigint unique,
        hakbun bigint unique,
        major_id bigint,
        student_id bigint not null,
        city varchar(255),
        name varchar(255),
        password varchar(255),
        street varchar(255),
        status enum ('DROPOUT','ENROLLED','ONLEAVE'),
        primary key (student_id)
    );

alter table if exists course
       add constraint FKqctak3o6xmul2nu2561al3pb5
       foreign key (professor_id)
       references professor;

alter table if exists enroll
       add constraint FKiv2pkft2ab8mqx9ffc4ex4q7e
       foreign key (course_id)
       references course;

 alter table if exists enroll
       add constraint FKr5hq653kinipvyshfdl738ouc
       foreign key (student_id)
       references student;

alter table if exists professor
       add constraint FKrqgvxgr92hlwoq78brjuskb22
       foreign key (major_id)
       references major;

alter table if exists student
       add constraint FKauqqk4ebb5touyklbbvbwiv1f
       foreign key (credit_id)
       references credit;

alter table if exists student
       add constraint FKcml1vvjs3bcqyxdcprjrjd2o0
       foreign key (major_id)
       references major;