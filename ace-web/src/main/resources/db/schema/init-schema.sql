drop table if exists `sys_user`;;
drop table if exists `sys_organization`;;
drop table if exists `sys_job`;;
drop table if exists `sys_user_organization_job`;;
drop table if exists `sys_resource`;;
drop table if exists `sys_permission`;;
drop table if exists `sys_role`;;
drop table if exists `sys_role_resource_permission`;;
drop table if exists `sys_user_roles`;;
drop table if exists `sys_group`;;
drop table if exists `sys_group_relation`;;
drop table if exists `sys_trade_code`;;

##user
create table `sys_user`(
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) NULL DEFAULT NULL ,
  `email` VARCHAR(100) NULL DEFAULT NULL ,
  `mobile_phone_number` VARCHAR(20) NULL DEFAULT NULL ,
  `password` VARCHAR(100) NULL DEFAULT NULL ,
  `salt` VARCHAR(10) NULL DEFAULT NULL ,
  `status` VARCHAR(50) NULL DEFAULT NULL ,
  `organization_id` BIGINT NULL DEFAULT NULL ,
  `organization_name` VARCHAR(255) NULL DEFAULT NULL ,
  `admin` BOOL NULL DEFAULT NULL ,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间' ,
  `login_time` DATETIME NULL DEFAULT NULL COMMENT '登录时间' ,
  `last_login_time` DATETIME NULL DEFAULT NULL COMMENT '上次登录时间' ,
  `count` BIGINT(20) NULL DEFAULT NULL COMMENT '登录次数' ,
  constraint `pk_sys_user` primary key(`id`),
  constraint `unique_sys_user_username` unique(`username`),
  constraint `unique_sys_user_email` unique(`email`),
  constraint `unique_sys_user_mobile_phone_number` unique(`mobile_phone_number`),
  index `idx_sys_user_status` (`status`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_user` auto_increment=1000;;


create table `sys_organization`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '组织,机构ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '组织名称' ,
  `type` VARCHAR(20) NULL DEFAULT NULL COMMENT '类型' ,
  `icon` VARCHAR(200) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  `parent_id` bigint,
  `parent_ids`  varchar(200) default '',
  `user_count` INT(11) NULL DEFAULT NULL COMMENT '用户数' ,
  `description` VARCHAR(511) NULL DEFAULT NULL COMMENT '组织描述' ,
  `address` VARCHAR(255) NULL DEFAULT NULL COMMENT '地址' ,
  `url` VARCHAR(200) NULL DEFAULT NULL COMMENT 'URL' ,
  `end_date` DATETIME NULL DEFAULT NULL COMMENT '结束时间' ,
  `telephone` VARCHAR(50) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL COMMENT '状态' ,
  `trade_code_id` INT(11) NULL DEFAULT NULL COMMENT '组织类型ID',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间' ,
  constraint `pk_sys_organization` primary key(`id`),
  index `idx_sys_organization_name` (`name`),
  index `idx_sys_organization_trade_code` (`trade_code_id`),
  index `idx_sys_organization_parent_id` (`parent_id`),
  index `idx_sys_organization_parent_ids_weight` (`parent_ids`, `weight`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_organization` auto_increment=10000;;

CREATE TABLE `sys_job` (
  `id` BIGINT NOT NULL AUTO_INCREMENT comment '职位ID',
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '职位名称',
  `parent_id` BIGINT NULL DEFAULT NULL COMMENT '父级ID',
  `parent_ids` VARCHAR(200) NULL DEFAULT NULL COMMENT '父级IDS',
  `weight`    int(11),
  `is_show` BOOL NULL DEFAULT NULL COMMENT '是否展示',
  `icon` VARCHAR(200) NULL DEFAULT NULL COMMENT '图标',
  constraint `pk_sys_job` primary key(`id`),
  index `idx_sys_job_nam` (`name`),
  index `idx_sys_job_parent_id` (`parent_id`),
  index `idx_sys_job_parent_ids_weight` (`parent_ids`, `weight`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COMMENT = '职位表';;
alter table `sys_job` auto_increment=1000;;


create table `sys_user_organization_job`(
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `user_id` BIGINT(20) NOT NULL ,
  `organization_id` BIGINT(20) NOT NULL ,
  `job_id` BIGINT(20) NULL DEFAULT NULL ,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  constraint `pk_sys_user_organization_job` primary key(`id`),
  constraint `unique_sys_user_organization_job` unique(`user_id`, `organization_id`, `job_id`)
) charset=utf8 ENGINE=InnoDB;;

create table `sys_resource`(
  `id`         bigint not null auto_increment comment '资源ID',
  `name`      varchar(100) comment '资源名称',
  `identity`  varchar(100) comment '表识',
  `url`      varchar(200) comment 'url地址',
  `parent_id` bigint comment '父级资源ID',
  `parent_ids`  varchar(200) default '' comment '父级资源IDS',
  `icon`       varchar(200) comment '资源图标',
  `weight`    int comment '权重',
  `is_show`   bool comment '是否展示',
  constraint `pk_sys_resource` primary key(`id`),
  index `idx_sys_resource_name` (`name`),
  index `idx_sys_resource_identity` (`identity`),
  index `idx_sys_resource_user` (`url`),
  index `idx_sys_resource_parent_id` (`parent_id`),
  index `idx_sys_resource_parent_ids_weight` (`parent_ids`, `weight`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_resource` auto_increment=1000;;


create table `sys_permission`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '权限名称' ,
  `permission` VARCHAR(100) NULL DEFAULT NULL COMMENT '权限' ,
  `description` VARCHAR(200) NULL DEFAULT NULL COMMENT '描述' ,
  `is_show` BOOL NULL DEFAULT NULL ,
  constraint `pk_sys_permission` primary key(`id`),
  index idx_sys_permission_name (`name`),
  index idx_sys_permission_permission (`permission`),
  index idx_sys_permission_show (`is_show`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_permission` auto_increment=1000;;

create table `sys_role`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '角色名称' ,
  `role` VARCHAR(100) NULL DEFAULT NULL COMMENT '角色' ,
  `description` VARCHAR(200) NULL DEFAULT NULL COMMENT '描述',
  `is_show` BOOL DEFAULT NULL ,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间' ,
  constraint `pk_sys_role` primary key(`id`),
  index `idx_sys_role_name` (`name`),
  index `idx_sys_role_role` (`role`),
  index `idx_sys_role_show` (`is_show`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_role` auto_increment=1000;;


create table `sys_role_resource_permission`(
  `id`         bigint not null auto_increment,
  `role_id`   bigint,
  `resource_id` bigint,
  `permission_ids` varchar(500),
  constraint `pk_sys_role_resource_permission` primary key(`id`),
  constraint `unique_sys_role_resource_permission` unique(`role_id`, `resource_id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_role_resource_permission` auto_increment=1000;;


/**
 * user与role organization 的关系表
 */
create table `sys_user_roles`(
  `id`         bigint not null auto_increment,
  `user_id`        bigint,
  `role_ids` VARCHAR(500) NULL DEFAULT NULL ,
  `organization_id`       bigint,
  `job_id`       bigint,
  `group_id`       bigint,
  `type`           varchar(50),
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间' ,
  constraint `pk_user_roles` primary key(`id`),
  index `idx_sys_user_roles_organization` (`organization_id`),
  index `idx_sys_user_roles_type` (`type`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_user_roles` auto_increment=1000;;


CREATE  TABLE `sys_group` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '组ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '组名称',
  `type` VARCHAR(45) NULL DEFAULT NULL COMMENT '类型',
  `is_show` BOOL NULL DEFAULT NULL COMMENT '是否显示' ,
  `default_group` BOOL NULL DEFAULT NULL COMMENT '默认组' ,
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间' ,
  `modify_time` TIMESTAMP NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间' ,
  `description` VARCHAR(255) NULL DEFAULT NULL COMMENT '描述' ,
  constraint `pk_sys_group` primary key(`id`),
  index `idx_sys_group_type` (`type`),
  index `idx_sys_group_show` (`is_show`),
  index `idx_sys_group_default_group` (`default_group`)
)charset=utf8 ENGINE=InnoDB;;

CREATE  TABLE `sys_group_relation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT ,
  `group_id` BIGINT NULL DEFAULT NULL ,
  `organization_id` BIGINT NULL DEFAULT NULL ,
  `user_id` BIGINT NULL DEFAULT NULL ,
  constraint `pk_sys_group_relation` primary key(`id`),
  index `idx_sys_group_relation_group` (`group_id`),
  index `idx_sys_group_relation_organization` (`organization_id`),
  index `idx_sys_group_relation_user` (`user_id`)
) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8 COMMENT = '用户,组,组织关联表';;

/**
 * 类型,代码
 */
create table `sys_trade_code` (
  `id`         bigint not null auto_increment,
  `name`      varchar(100),
  `identity`  varchar(100),
  `parent_id` bigint,
  `weight`    int,
  `is_show`       bool,
  constraint `pk_sys_resource` primary key(`id`),
  index `idx_sys_resource_name` (`name`),
  index `idx_sys_resource_identity` (`identity`),
  index `idx_sys_resource_parent_id` (`parent_id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_trade_code` auto_increment=1000;;

