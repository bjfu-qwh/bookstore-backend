use book_store;
drop table if exists t_ums_user;
create table if not exists t_ums_user
(
    id          varchar(32) primary key default (replace(uuid(), '-', '')) comment '基于UUID的用户主键',
    username    varchar(60) comment '用户昵称',
    password    varchar(64) comment '基于SHA256加密后的密码',
    email       varchar(80) comment '基于AES加密后的邮箱',
    index idx_user_email (email),
    phone       varchar(80) comment '基于AES加密后的手机号码',
    index idx_user_phone (phone),
    role        varchar(10) comment '账号角色',
    index idx_user_role (role),
    url         varchar(255) comment '用户头像地址',
    create_time datetime                default (now()) comment '注册时间',
    last_visit  datetime                default (null) comment '上次登录时间'
) default charset utf8mb4 comment '系统账号表';