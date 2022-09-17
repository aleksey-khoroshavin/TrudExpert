create table group_courses(
    course_id bigint,
    group_id bigint,
    constraint group_course_pk primary key (course_id, group_id),
    constraint course_fk foreign key (course_id) references courses(id),
    constraint group_fk foreign key (group_id) references groups(id)
);

create table listener_groups(
    listener_id bigint,
    group_id bigint,
    constraint listener_group_pk primary key (listener_id, group_id),
    constraint listener_fk foreign key (listener_id) references listeners(id),
    constraint group_fk foreign key (group_id) references groups(id)
);