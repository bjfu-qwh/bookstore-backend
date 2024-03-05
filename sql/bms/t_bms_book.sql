use book_store;
drop table if exists t_bms_book;
create table if not exists t_bms_book
(
    id          bigint unsigned comment '数据表主键' primary key auto_increment,
    isbn        varchar(20) comment 'ISBN',
    index idx_book_isbn (isbn),
    book_name   varchar(255) comment '图书名',
    index idx_book_name (book_name),
    press       varchar(80) comment '图书出版社',
    index idx_book_press (press),
    category_id bigint comment '图书根分类ID,-1表示暂无分类' default (-1),
    price       decimal(10, 2)                               default 0.00 comment '图书价格',
    edition     varchar(30) comment '版本描述',
    published   date comment '出版时间',
    amount      int comment '库存'                           default (0),
    type        varchar(15) comment '装帧',
    status      varchar(15)                                  default '在售' comment '图书状态'
) default charset utf8mb4 comment '图书信息表';