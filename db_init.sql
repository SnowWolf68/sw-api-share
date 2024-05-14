CREATE TABLE `sw_api` (
	`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
	`name` varchar(256) NOT NULL COMMENT '名称',
	`description` varchar(256) DEFAULT NULL COMMENT'描述',
	`url` varchar(512) NOT NULL COMMENT '接口地址',
	`resquest_header` text COMMENT '请求头',
	`reponse_header` text COMMENT '响应头',
	`status` int NOT NULL DEFAULT '0' COMMENT '接口状态（0-关闭，1-开启）',
	`method` varchar(256) NOT NULL COMMENT '请求类型',
	`user_id` bigint NOT NULL COMMENT '创建人',
	`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	`update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	`is_delete` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除（0-未删，1-已删）',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

drop table user;
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    username     varchar(256)                           null comment '用户昵称',
    password varchar(512)                           not null comment '密码',
    user_account  varchar(256)                           not null comment '账号',
    user_avatar   varchar(1024)                          null comment '用户头像',
    gender       tinyint                                null comment '性别',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user / admin',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint      default 0                 not null comment '是否删除',
    constraint uni_userAccount unique (user_account)
) comment '用户';
