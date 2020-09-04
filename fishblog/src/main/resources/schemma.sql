drop table b_comment if exists;
drop table b_reply if exists;
drop table b_user if exists;
drop table b_article if exists;

create table b_comment(
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    from_user_id bigint,
    article_id bigint,
    content varchar(1000),
    primary key(id)
)

create table b_reply(
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    comment_id bigint,
    from_user_id bigint,
    content varchar(1000),
    primary key(id)
)

create table b_user(
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    username varchar(60),
    password varchar(100),
    role integer not null,
    primary key(id)
)

create table b_article(
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    content varchar(10000),
    title varchar(100),
    user_id bigint,
    primary key(id)
)