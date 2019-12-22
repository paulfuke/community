create table first_boot.question
(
    id  int auto_increment primary key,
    title         varchar(50)   null,
    description   text  null,
    like_count    int default 0 null comment '点赞数',
    comment_count int default 0 null comment '评论数',
    view_count    int default 0 null comment '阅读数',
    gmt_create    bigint        null comment '创建时间',
    gmt_modified  bigint        null，
    tag           varchar(256)  null comment '标签',
    creator       int           null
);

