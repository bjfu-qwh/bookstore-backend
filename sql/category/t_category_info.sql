use book_store;
drop table if exists t_category_info;
create table if not exists t_category_info
(
    id     varchar(32) primary key comment '分类编号',
    name   varchar(100) comment '分类名称',
    parent varchar(32) default ('') comment '该分类对应的父分类编号，空表示无',
    index idx_category_parent (parent)
) default charset = utf8mb4 comment '图书分类表';

insert into t_category_info (id, name, parent)
values ('cate_1', '计算机', '');
insert into t_category_info (id, name, parent)
values ('cate_1_1', 'AI/人工智呢', 'cate_1');
insert into t_category_info(id, name, parent)
values ('cate_1_2', '分布式', 'cate_1');
insert into t_category_info(id, name, parent)
values ('cate_1_3', '数据库系统', 'cate_1');
insert into t_category_info(id, name, parent)
values ('cate_1_4', '操作系统', 'cate_1');
insert into t_category_info(id, name, parent)
values ('cate_1_5', '计算机网络', 'cate_1');
insert into t_category_info(id, name, parent)
values ('cate_1_6', '编程语言', 'cate_1');
insert into t_category_info(id, name, parent)
values ('cate_1_6_1', 'Java', 'cate_1_6');