use book_store;
drop table if exists t_author_info;
create table if not exists t_author_info
(
    id     bigint auto_increment primary key comment '主键',
    name   varchar(100) comment '作家姓名',
    index idx_author_name (name) comment '作家姓名经常需要被查询',
    nation varchar(20)                                        default ('中国') comment '作家所属国家,如中国',
    url    varchar(255) comment '作家头像的图片链接'          default (null),
    brief  varchar(255) comment '关于作家的简介,实现为富文本' default (null)
) default charset utf8mb4 comment '作家信息表';
