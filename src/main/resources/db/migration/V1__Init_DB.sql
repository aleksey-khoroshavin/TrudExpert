create table courses
(
    id            bigserial     not null,
    cost          numeric(7, 2) not null,
    description   TEXT          not null,
    studying_time int8          not null,
    primary key (id)
);

create table groups
(
    id               bigserial not null,
    number           int8      not null,
    study_end_date   timestamp not null,
    study_start_date timestamp not null,
    primary key (id)
);

create table listeners
(
    id                           bigserial not null,
    address                      TEXT,
    citizenship_code             int8,
    date_of_birth                date,
    driver_license               varchar(50),
    education_document           TEXT,
    education_document_issued_at date,
    education_surname            varchar(100),
    education_type               text,
    gender                       varchar(50),
    name                         varchar(80),
    passport_issued_at           date,
    passport_issued_by           TEXT,
    passport_number              varchar(70),
    passport_series              varchar(50),
    patronymic                   varchar(100),
    phone_number                 varchar(100),
    snils                        varchar(50),
    surname                      varchar(100),
    primary key (id)
);

create table organization_agent
(
    organization_id int8 not null,
    document        TEXT,
    name            varchar(80),
    patronymic      varchar(100),
    post            TEXT,
    surname         varchar(100),
    primary key (organization_id)
);

create table organization_listener
(
    listener_id     int8 not null,
    organization_id int8 not null,
    post            TEXT,
    primary key (listener_id, organization_id)
);

create table organizations
(
    id                    bigserial not null,
    bik                   varchar(50),
    checking_account      TEXT,
    correspondent_account TEXT,
    email                 varchar(50),
    fact_address          TEXT,
    inn                   varchar(50),
    kpp                   varchar(50),
    law_address           TEXT,
    name                  TEXT,
    okpo                  varchar(50),
    okved                 varchar(50),
    orgn                  varchar(50),
    phone                 varchar(50),
    primary key (id)
);

alter table organizations
    add constraint org_uniq_name unique (name);

alter table organization_agent
    add constraint organization_fk foreign key (organization_id) references organizations;

alter table organization_listener
    add constraint listener_fk foreign key (listener_id) references listeners;

alter table organization_listener
    add constraint organization_fk foreign key (organization_id) references organizations;

create table listener_courses
(
    listener_id bigint,
    course_id   bigint,
    primary key (listener_id, course_id),
    constraint listener_fk foreign key (listener_id) references listeners (id),
    constraint course_fk foreign key (course_id) references courses (id)
);

create table group_courses
(
    course_id bigint,
    group_id  bigint,
    constraint group_course_pk primary key (course_id, group_id),
    constraint course_fk foreign key (course_id) references courses (id),
    constraint group_fk foreign key (group_id) references groups (id)
);

create table listener_groups
(
    listener_id bigint,
    group_id    bigint,
    constraint listener_group_pk primary key (listener_id, group_id),
    constraint listener_fk foreign key (listener_id) references listeners (id),
    constraint group_fk foreign key (group_id) references groups (id)
);