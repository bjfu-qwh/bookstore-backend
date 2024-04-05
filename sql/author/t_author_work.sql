use book_store;
drop table if exists t_author_work;
create table if not exists t_author_work
(
    id        bigint primary key auto_increment comment '数据表主键',
    author_id bigint comment '作家ID',
    index idx_author_id (author_id),
    work_id   varchar(32) comment '作品编号',
    index idx_work_id (work_id)
) default charset utf8mb4 comment '作家作品表';