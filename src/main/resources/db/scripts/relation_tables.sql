create table listener_courses
(
    listener_id bigint,
    course_id   bigint,
    primary key (listener_id, course_id),
    constraint listener_fk foreign key (listener_id) references listeners (id),
    constraint course_fk foreign key (course_id) references courses (id)
);