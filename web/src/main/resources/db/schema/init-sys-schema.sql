/*==============================================================*/
/* DBMS name:      MySQL 5.5                                    */
/* Created on:     2014/10/22 21:41:03                          */
/* Author   :      denghp                                       */
/*==============================================================*/
drop table if exists sys_user;

drop table if exists sys_role;

drop table if exists sys_group;

drop table if exists sys_organization;

drop table if exists sys_group_relation;

drop table if exists sys_job;

drop table if exists sys_permission;

drop table if exists sys_resource;

drop table if exists sys_role_resource_permission;

drop table if exists sys_user_organization_job;

drop table if exists sys_auth;

drop table if exists sys_employee;

drop table if exists sys_employee_organization_job;

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
  id                   bigint not null auto_increment comment '用户主键',
  username             varchar(50) comment '用户名',
  password             varchar(50) default NULL comment '登录密码',
  realname             varchar(50) default NULL comment '真实姓名',
  mobile               varchar(11) default NULL comment '手机号码',
  email                varchar(30) default NULL comment '电子邮箱',
  salt                 varchar(100) default NULL comment '加密盐值',
  deleted              int(1) default 1 comment '删除: 1-正常 0-删除',
  status               varchar(30) default NULL comment '用户状态',
  birthday             timestamp default 0 comment '出生日期',
  gender               char(2) default NULL comment '性别',
  create_time          timestamp default 0 comment '创建时间',
  modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  login_time           timestamp default 0 comment '登录时间',
  last_login_time      timestamp default 0 comment '最近登录时间',
  first_login_time     timestamp default 0 comment '第一次登录时间',
  count                bigint default 0 comment '登录次数',
  primary key (id)
)
  auto_increment = 10000;

alter table sys_user comment '系统用户表';

/*==============================================================*/
/* Index: unique_sys_user_username                              */
/*==============================================================*/
create index unique_sys_user_username on sys_user
(
  username
);

/*==============================================================*/
/* Index: unique_sys_user_email                                 */
/*==============================================================*/
create index unique_sys_user_email on sys_user
(
  email
);

/*==============================================================*/
/* Index: unique_sys_user_mobile                                */
/*==============================================================*/
create index unique_sys_user_mobile on sys_user
(
  mobile
);



/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
  id                   bigint not null auto_increment comment '角色主键',
  name                 varchar(100) default NULL comment '角色名称',
  role                 varchar(100) default NULL comment '角色',
  category             varchar(100) comment '角色分类',
  description          varchar(255) comment '角色描述',
  enabled              int default 1 comment '有效: 1-有效 0-无效',
  create_time          timestamp default 0 comment '创建时间',
  modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  primary key (id)
)
  auto_increment = 1000;

alter table sys_role comment '系统角色表';

/*==============================================================*/
/* Index: idx_sys_role_role                                     */
/*==============================================================*/
create index idx_sys_role_role on sys_role
(
  role
);

/*==============================================================*/
/* Index: idx_sys_role_name                                     */
/*==============================================================*/
create index idx_sys_role_name on sys_role
(
  name
);


/*==============================================================*/
/* Table: sys_group                                             */
/*==============================================================*/
create table sys_group
(
  id                   bigint not null auto_increment comment '组主键',
  name                 varchar(100) default NULL comment '组名称',
  type                 varchar(50) default NULL comment '组类型',
  enabled              int default 1 comment '有效: 1-有效 0-无效',
  default_group        bool default TRUE comment '默认组',
  create_time          timestamp default 0 comment '创建时间',
  modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  description          varchar(255) default NULL comment '组描述',
  primary key (id)
)
  auto_increment = 1000;

alter table sys_group comment '系统用户组表';

/*==============================================================*/
/* Index: idx_sys_group_default_group                           */
/*==============================================================*/
create index idx_sys_group_default_group on sys_group
(
  default_group
);

/*==============================================================*/
/* Table: sys_group_relation                                    */
/*==============================================================*/
create table sys_group_relation
(
  id                   bigint not null auto_increment comment '用户组表主键',
  group_id             bigint default NULL comment '组ID',
  user_id              bigint default NULL comment '用户ID',
  organization_id      bigint default NULL comment '组织机构ID',
  primary key (id)
);

alter table sys_group_relation comment '用户,组,组织机构表';

/*==============================================================*/
/* Index: idx_sys_group                                         */
/*==============================================================*/
create index idx_sys_group on sys_group_relation
(
  group_id
);

/*==============================================================*/
/* Index: idx_sys_user                                          */
/*==============================================================*/
create index idx_sys_user on sys_group_relation
(
  user_id
);

/*==============================================================*/
/* Index: idx_sys_group_relation_organization                   */
/*==============================================================*/
create index idx_sys_group_relation_organization on sys_group_relation
(
  organization_id
);


/*==============================================================*/
/* Table: sys_job                                               */
/*==============================================================*/
create table sys_job
(
  id                   bigint not null auto_increment comment '主键ID',
  name                 varchar(100) default NULL comment '职位名称',
  parent_id            bigint default NULL comment '父级职务',
  parent_ids           varchar(100) default NULL comment '父级职务IDS',
  weight               int default 0 comment '排序权重',
  enabled              int default 1 comment '有效: 1-有效 0-无效',
  icon                 varchar(50) comment '图标',
  description          varchar(255) comment '描述',
  primary key (id)
)
  auto_increment =1000;

alter table sys_job comment '系统职务表';

/*==============================================================*/
/* Index: idx_sys_job_nam                                       */
/*==============================================================*/
create index idx_sys_job_nam on sys_job
(
  name
);

/*==============================================================*/
/* Index: idx_sys_job_parent_id                                 */
/*==============================================================*/
create index idx_sys_job_parent_id on sys_job
(
  parent_id
);

/*==============================================================*/
/* Index: idx_sys_job_parent_ids_weight                         */
/*==============================================================*/
create index idx_sys_job_parent_ids_weight on sys_job
(
  parent_ids,
  weight
);

/*==============================================================*/
/* Table: sys_organization                                      */
/*==============================================================*/
create table sys_organization
(
  id                   bigint not null auto_increment comment '组织机构主键',
  name                 varchar(125) default NULL comment '组织机构名称',
  short_name           varchar(50) default NULL comment '简称',
  category             varchar(50) default NULL comment '分类',
  icon                 varchar(50) default NULL comment '图标',
  weight               int default 0 comment '排序权重',
  parent_id            bigint default NULL comment '父级主键',
  parent_ids           varchar(125) comment '父级主键IDS',
  manager              varchar(50) default NULL comment '负责人',
  assistant_manager    varchar(50) comment '副负责人',
  telephone            varchar(20) default NULL comment '电话',
  fax                  varchar(20) default NULL comment '传真',
  mobile               varchar(11) comment '手机号码',
  description          varchar(255) default NULL comment '描述',
  enabled              int default 1 comment '有效: 1-有效 0-无效',
  address              varchar(255) default NULL comment '地址',
  create_user_id       bigint default NULL comment '创建人ID',
  create_user_name     varchar(50) default NULL comment '创建人名称',
  modify_user_id       bigint default NULL comment '修改人ID',
  modify_user_name     varchar(50) default NULL comment '修改人名称',
  create_time          timestamp default 0 comment '创建时间',
  modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  primary key (id)
)
  auto_increment = 1000;

alter table sys_organization comment '组织机构(部门)表';

/*==============================================================*/
/* Index: idx_sys_organization_name                             */
/*==============================================================*/
create index idx_sys_organization_name on sys_organization
(
  name
);

/*==============================================================*/
/* Index: idx_sys_organization_parent_id                        */
/*==============================================================*/
create index idx_sys_organization_parent_id on sys_organization
(
  parent_id
);

/*==============================================================*/
/* Index: idx_sys_organization_parent_ids_weight                */
/*==============================================================*/
create index idx_sys_organization_parent_ids_weight on sys_organization
(
  weight,
  parent_ids
);

/*==============================================================*/
/* Table: sys_permission                                        */
/*==============================================================*/
create table sys_permission
(
  id                   bigint not null comment '权限主键',
  name                 varchar(100) default NULL comment '权限名称',
  permission           varchar(100) default NULL comment '权限操作',
  description          varchar(255) default NULL comment '权限描述',
  enabled              int default 1 comment '有效: 1-有效 0-无效',
  primary key (id)
)
  auto_increment = 1000;

alter table sys_permission comment '系统权限表';

/*==============================================================*/
/* Index: idx_sys_permission_name                               */
/*==============================================================*/
create index idx_sys_permission_name on sys_permission
(
  name
);

/*==============================================================*/
/* Index: idx_sys_permission_permission                         */
/*==============================================================*/
create index idx_sys_permission_permission on sys_permission
(
  permission
);

/*==============================================================*/
/* Table: sys_resource                                          */
/*==============================================================*/
create table sys_resource
(
  id                   bigint not null auto_increment comment '资源主键',
  name                 varchar(125) default NULL comment '资源名称',
  identity             varchar(100) default NULL comment '资源唯一标识',
  url                  varchar(255) default NULL comment '资源URL',
  parent_id            bigint default NULL comment '父级资源ID',
  parent_ids           varchar(200) default NULL comment '父级资源IDS',
  icon                 varchar(100) default NULL comment '资源图标',
  weight               int default 0 comment '排序权重',
  enabled              int default 1 comment '有效: 1-有效 0-无效',
  create_time          timestamp default 0 comment '创建时间',
  modify_time          timestamp default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '更新时间',
  primary key (id)
)
  auto_increment = 1000;

alter table sys_resource comment '系统资源表';

/*==============================================================*/
/* Index: idx_sys_resource_name                                 */
/*==============================================================*/
create index idx_sys_resource_name on sys_resource
(
  name
);

/*==============================================================*/
/* Index: idx_sys_resource_identity                             */
/*==============================================================*/
create index idx_sys_resource_identity on sys_resource
(
  identity
);

/*==============================================================*/
/* Index: idx_sys_resource_user                                 */
/*==============================================================*/
create index idx_sys_resource_user on sys_resource
(
  url
);

/*==============================================================*/
/* Index: idx_sys_resource_parent_id                            */
/*==============================================================*/
create index idx_sys_resource_parent_id on sys_resource
(
  parent_id
);

/*==============================================================*/
/* Index: idx_sys_resource_parent_ids_weight                    */
/*==============================================================*/
create index idx_sys_resource_parent_ids_weight on sys_resource
(
  weight
);


/*==============================================================*/
/* Table: sys_role_resource_permission                          */
/*==============================================================*/
create table sys_role_resource_permission
(
  id                   bigint not null auto_increment comment '主键',
  role_id              bigint comment '角色ID',
  resource_id          bigint comment '资源ID',
  permission_ids       varchar(100) default NULL comment '操作权限',
  primary key (id)
);

alter table sys_role_resource_permission comment '角色资源权限';

/*==============================================================*/
/* Index: unique_sys_role_resource_permission                   */
/*==============================================================*/
create index unique_sys_role_resource_permission on sys_role_resource_permission
(
  role_id,
  resource_id
);



/*==============================================================*/
/* Table: sys_user_organization_job                             */
/*==============================================================*/
create table sys_user_organization_job
(
  id                   bigint not null auto_increment comment '主键',
  user_id              bigint default null comment '用户ID',
  job_id               bigint default NULL comment '职位ID',
  organization_id      bigint default NULL comment '组织机构ID',
  primary key (id)
);

alter table sys_user_organization_job comment '用户职位组织机构表';

/*==============================================================*/
/* Index: unique_sys_user_organization_job                      */
/*==============================================================*/
create index unique_sys_user_organization_job on sys_user_organization_job
(
  user_id,
  job_id,
  organization_id
);

/*==============================================================*/
/* Table: sys_auth   用户,组,职务,组织机构授权表                   */
/*==============================================================*/
create table `sys_auth`(
  `id`         bigint not null auto_increment,
  `organization_id`       bigint,
  `job_id`       bigint,
  `user_id`      bigint,
  `group_id`     bigint,
  `role_ids`     varchar(500),
  `type`         varchar(50) COMMENT 'user,user_group,organization_job,organization_group',
  constraint `pk_sys_auth` primary key(`id`),
  index `idx_sys_auth_organization` (`organization_id`),
  index `idx_sys_auth_job` (`job_id`),
  index `idx_sys_auth_user` (`user_id`),
  index `idx_sys_auth_group` (`group_id`),
  index `idx_sys_auth_type` (`type`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_auth` auto_increment=1000;;



/*==============================================================*/
/* Table: sys_employee                             */
/*==============================================================*/
create table `sys_employee`(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '员工主键ID',
  `user_id` BIGINT NULL COMMENT '用户主键ID',
  `code` VARCHAR(50) NULL DEFAULT NULL COMMENT '编号,工号',
  `realname` VARCHAR(50) NULL DEFAULT NULL COMMENT '真实姓名',
  `spell` VARCHAR(50) NULL DEFAULT NULL COMMENT '真实姓名拼音',
  `alias` VARCHAR(50) NULL DEFAULT NULL COMMENT '别名',
  `id_card` VARCHAR(50) NULL DEFAULT NULL COMMENT '身份证号码',
  `bank_card` VARCHAR(50) NULL DEFAULT NULL COMMENT '工资卡',
  `gender` VARCHAR(20) NULL DEFAULT NULL COMMENT '性别',
  `birthday` TIMESTAMP DEFAULT 0 COMMENT '出生日期',
  `email` VARCHAR(100) NULL DEFAULT NULL COMMENT '电子邮件',
  `oicq` VARCHAR(100) NULL DEFAULT NULL COMMENT 'QQ号码',
  `mobile` VARCHAR(20) NULL DEFAULT NULL COMMENT '手机号码',
  `telephone` VARCHAR(20) NULL DEFAULT NULL COMMENT '固定电话',
  `office_phone` VARCHAR(20) NULL DEFAULT NULL COMMENT '办公电话',
  `office_zip_code` VARCHAR(20) NULL DEFAULT NULL COMMENT '办公邮编',
  `office_address` VARCHAR(100) NULL DEFAULT NULL COMMENT '办公地址',
  `office_fax` VARCHAR(50) NULL DEFAULT NULL COMMENT '办公传真',
  `age` INT(10) NULL DEFAULT NULL COMMENT '年龄',
  `education` VARCHAR (50) NULL DEFAULT NULL COMMENT '最高学历',
  `school` VARCHAR (50) NULL DEFAULT NULL COMMENT '毕业院校',
  `graduation_date` TIMESTAMP NULL DEFAULT NULL COMMENT '毕业时间',
  `major` VARCHAR (50) NULL DEFAULT NULL COMMENT '所学专业',
  `status` VARCHAR(50) NULL DEFAULT NULL COMMENT '状态',
  `entry_time` TIMESTAMP NULL DEFAULT 0 COMMENT '入职时间' ,
  `dimission_time` TIMESTAMP NULL DEFAULT NULL COMMENT '离职时间' ,
  `create_time` TIMESTAMP DEFAULT 0 COMMENT '创建时间' ,
  `modify_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间' ,
  `create_user_id` BIGINT NULL DEFAULT NULL COMMENT '创建人主键ID',
  `create_user_name` VARCHAR (50) NULL DEFAULT NULL COMMENT '创建人',
  `modify_user_id` BIGINT NULL DEFAULT NULL COMMENT '修改用户主键ID',
  `modify_user_name` BIGINT NULL DEFAULT NULL COMMENT '修改用户',
  constraint `pk_sys_user` primary key(`id`),
  constraint `unique_sys_user_id_card` unique(`id_card`),
  constraint `unique_sys_user_email` unique(`email`),
  constraint `unique_sys_user_oicq` unique(`oicq`),
  constraint `unique_sys_user_mobile` unique(`mobile`),
  index `idx_sys_user_status` (`status`)
) charset=utf8 ENGINE=InnoDB;
alter table `sys_employee` auto_increment=1000;

/*==============================================================*/
/* Table: sys_employee_organization_job                             */
/*==============================================================*/
create table sys_employee_organization_job
(
  id                   bigint not null auto_increment comment '主键',
  employee_id              bigint default null comment '职工，员工ID',
  job_id               bigint default NULL comment '职位ID',
  organization_id      bigint default NULL comment '组织机构ID',
  primary key (id)
);