use book_store;
drop table if exists t_category_book;
create table if not exists t_category_book
(
    id          bigint auto_increment primary key,
    book_id     varchar(32) comment '图书ID',
    index idx_category_book_id (book_id),
    category_id varchar(20) comment '分类ID',
    index idx_category_id (category_id),
    parent_id   varchar(20) default ('') comment '当前分类词条对应的父分类编号id',
    index idx_parent (parent_id)
) default charset utf8mb4 comment '图书-分类表';