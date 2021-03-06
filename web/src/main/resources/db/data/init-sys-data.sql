#如果复制到mysql中执行时 加上
#DELIMITER ;
use ace;
delete from `sys_user` where id>=1 and id<=1000;
/*默认admin/123456*/
insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `deleted`)
  values
  (1, 'admin', 'admin@ace.com', '13412345671', 'ec21fa1738f39d5312c6df46002d403d', 'yDd1956wn1', sysdate(), 'normal', 0);
insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `deleted`)
  values
  (2, 'sys_admin', 'sys_admin@ace.com', '13412345673', '3152e2566dc8075b2b2ac0ee0ee2424d', 'MANHOoCpnb', sysdate(), 'normal', 0);
insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `deleted`)
  values
  (3, 'sales_admin', 'sales_admin@ace.com', '13412345672', 'aa54c5ee4922d3ee19db7009f541e0d6', 'hSSixwNQwt', sysdate(), 'normal', 0);

insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `deleted`)
  values
  (4, 'purchase_admin', 'purchase_admin@ace.com', '13412345674', 'e75a4379059f918256aa6a2f0b81bfa5', 'iY71e4dtoa', sysdate(), 'normal', 0);

insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `deleted`)
  values
  (5, 'stock_admin', 'stock_admin@ace.com', '13412345675', '1c7f3d8e6fa84d92f43c88bd6082b9ce', 'iruPxupgfb', sysdate(), 'normal', 0);

insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `deleted`)
  values
  (6, 'finance_admin', 'finance_admin@ace.com', '13412345676', 'f30527ae456a700c5cfd079b847989fc', '2WQx5LmvlV', sysdate(), 'normal', 0);



delete from `sys_organization` where id>=1 and id<=1000;
insert into `sys_organization`(`id`, `parent_id`, `name`) values (1,0, '北京ACE系统有限公司');
insert into `sys_organization`(`id`, `parent_id`, `name`) values (2,1, '上海ACE系统有限公司');
insert into `sys_organization`(`id`, `parent_id`, `name`) values (3,1, '长沙ACE系统有限公司');


delete from `sys_resources` where id>=1 and id<=1000;
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (1, 0, '0/', 1, '资源', '', '', 1);
/** 采购资源 **/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (2, 1, '0/1/', 1, '采购管理', 'purchase', '', 1,'icon-shopping-cart');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (3, 2, '0/1/2/', 1, '采购单', 'orderPurchase', '/purchase/orderPurchase', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (4, 2, '0/1/2/', 2, '采购单列表', 'purchaseList', '/purchase/purchaseList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (5, 2, '0/1/2/', 3, '采购退货', 'purchaseReturn', '/purchase/purchaseReturn', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (6, 2, '0/1/2/', 4, '退货单列表', 'purchaseReturnList', '/purchase/purchaseReturnList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (7, 2, '0/1/2/', 5, '供应商账龄报表', 'purchaseAgingSearch', '/purchase/purchaseAgingSearch', 1);

/**  销售资源 ****/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (8, 1, '0/1/', 2, '销售管理', 'sales', '', 1, 'icon-leaf');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (9, 8, '0/1/8/', 1, '新增销售单', 'addSalesOrder', '/sales/addSalesOrder', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (10, 8, '0/1/8/', 2, '销售单列表', 'salesOrderList', '/sales/salesOrderList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (11, 8, '0/1/8/', 3, '新增销售退货', 'addSalesReturn', '/sales/addSalesReturn', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (12, 8, '0/1/8/', 4, '销售退货列表', 'salesReturnList', '/sales/salesReturnList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (13, 8, '0/1/8/', 5, '客户账龄表', 'salesAgingSearch', '/sales/salesAgingSearch', 1);

/** 库存资源 ***/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (14, 1, '0/1/', 3, '库存管理', 'stock', '', 1,'icon-home');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (15, 14, '0/1/14/', 1, '新增货品', 'addInv', '/stock/addInv', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (16, 14, '0/1/14/', 2, '库存列表', 'invList', '/stock/invList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (17, 14, '0/1/14/', 3, '新增调拨单', 'addTransfer', '/stock/addTransfer', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (18, 14, '0/1/14/', 4, '调拨单列表', 'transferList', '/stock/transferList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (19, 14, '0/1/14/', 5, '借入单', 'borrowed', '/stock/borrowedOrder', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (20, 14, '0/1/14/', 6, '借出单', 'loan', '/stock/loanOrder', 1);

/**  资金流 ---***/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (21, 1, '0/1/', 4, '财务记账', 'finance', '', 1, 'icon-usd');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (22, 21, '0/1/21/', 1, '收支流水', 'payments ', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (23, 22, '0/1/21/22/', 1, '日常收支', 'dayAccount', '/finance/payments/dayAccount', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (24, 22, '0/1/21/22/', 2, '收支项目管理', 'accountList', '/finance/payments/accountList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (25, 21, '0/1/21/', 2, '资金往来', 'cashDealing', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (26, 25, '0/1/21/25/', 1, '应收款', 'receivables', '/finance/cashDealing/receivables', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (27, 25, '0/1/21/25/', 2, '应付款', 'payables', '/finance/cashDealing/payables', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (28, 25, '0/1/21/25/', 3, '资金流水', 'cashFlow', '/finance/cashDealing/cashFlow', 1);



/**  客户与欠款资源 ***/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (29, 1, '0/1/', 5, '客户与欠款', 'customer', '', 1,'icon-user');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (30, 29, '0/1/29/', 1, '新增客户', 'addCustomer', '/customer/addCustomer', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (31, 29, '0/1/29/', 2, '客户列表', 'customerList', '/customer/customerList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (32, 29, '0/1/29/', 3, '客户分类', 'customerCategory', '/customer/customerCategory', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (33, 29, '0/1/29/', 4, '收客户欠款', 'incomeCustomerArrears', '/customer/incomeCustomerArrears', 1);


/***  供应商与欠款 ***/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (34, 1, '0/1/', 6, '供应商与欠款', 'supplier', '', 1,'icon-group');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (35, 34, '0/1/34/', 1, '新增供应商', 'addSupplier', '/supplier/addSupplier', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (36, 34, '0/1/34/', 2, '供应商列表', 'supplierList', '/supplier/supplierList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (37, 34, '0/1/34/', 3, '供应商分类', 'supplierCategory', '/supplier/supplierCategory', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (38, 34, '0/1/34/', 4, '付供应商欠款', 'paySupplierArrears', '/supplier/paySupplierArrears', 1);

/***  大客户批发价 **/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (39, 1, '0/1/', 7, '大客户批发价', 'bigWSPrice', '', 1,'icon-desktop');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (40, 39, '0/1/39/', 1, '新增客户批发价', 'addCWSPrice', '/bigWSPrice/addCWSPrice', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (41, 39, '0/1/39/', 2, '客户批发价列表', 'cwsPriceList', '/bigWSPrice/cwsPriceList', 1);

/**  经营报告 **/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (42, 1, '0/1/', 8, '经营报告', 'reports', '', 1,'icon-bar-chart');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (43, 42, '0/1/42/', 1, '采购', '', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (44, 43, '0/1/42/43/', 1, '采购曲线图', 'purchaseGraphReport', '/reports/purchase/purchaseGraphReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (45, 43, '0/1/42/43/', 2, '采购统计', 'purchaseReport', '/reports/purchase/purchaseReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (46, 42, '0/1/42/', 2, '销售', '', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (47, 46, '0/1/42/46/', 1, '销售曲线图', 'salesGraphReport', '/reports/sales/salesGraphReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (48, 46, '0/1/42/46/', 2, '销售统计', 'salesReport', '/reports/sales/salesGraphReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (49, 46, '0/1/42/46/', 3, '员工销售报表', 'personnelSalesReport', '/reports/sales/personnelSalesReport', 1);


insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
    values (53, 42, '0/1/42/', 4, '综合经营状况', 'manage', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
    values (54, 53, '0/1/42/53/', 1, '进销存统计', 'erpReport', '/reports/manage/erpReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
    values (55, 53, '0/1/42/53/', 2, '资金统计报表', 'fundsReport', '/reports/manage/fundsReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
    values (56, 53, '0/1/42/53/', 3, '应付欠款报表', 'payArrearsReport', '/reports/manage/payArrearsReport', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
    values (57, 53, '0/1/42/53/', 4, '供应商账龄表', 'supplierPaymentsReport', '/reports/manage/supPaymentsReport', 1);



/**  基础资料 **/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (58, 1, '0/1/', 9, '基础资料', 'baseInfo', '', 1,'icon-edit');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (59, 58, '0/1/58/', 1, '商品资料', 'product', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (60, 59, '0/1/58/59/', 1, '新增商品', 'addProductInfo', '/baseInfo/product/addProductInfo', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (61, 59, '0/1/58/59/', 2, '商品分类', 'productCategory', '/baseInfo/product/productCategory', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (62, 59, '0/1/58/59/', 3, '商品列表', 'productList', '/baseInfo/product/productList', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (63, 59, '0/1/58/59/', 4, '单位设置', 'unit', '/baseInfo/product/unit', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (64, 59, '0/1/58/59/', 5, '颜色管理', 'color', '/baseInfo/product/color', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (65, 59, '0/1/58/59/', 6, '尺码管理', 'size', '/baseInfo/product/size', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (66, 59, '0/1/58/59/', 7, '品牌管理', 'brand', '/baseInfo/product/brand', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (67, 58, '0/1/58/', 2, '收付款账户', 'payment', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (68, 67, '0/1/58/67/', 1, '账户管理', 'account', '/baseInfo/payment/account', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (69, 58, '0/1/58/', 3, '仓库管理', '', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (70, 69, '0/1/58/69/', 1, '仓库信息管理', '', '/baseInfo/stock', 1);

/**  系统设置 **/
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`,`icon`)
  values (71, 1, '0/1/', 10, '系统设置', 'sys', '', 1,'icon-cogs');
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (72, 71, '0/1/71/', 1, '参数设置', 'parameter', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (73, 72, '0/1/71/72', 1, '货币类型', 'currency', '/admin/sys/parameter/currency', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (74, 72, '0/1/72/72', 2, '单据编码规则', 'receipt', '/admin/sys/parameter/receipt', 1);

insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (75, 71, '0/1/71/', 2, '公司管理', 'company', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (76, 75, '0/1/71/75/', 1, '公司档案', 'company', '/admin/sys/company/company', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (77, 75, '0/1/71/75/', 2, '职员档案', 'employee', '/admin/sys/company/employee', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (78, 75, '0/1/71/75/', 3, '公司列表', 'personProfile', '/admin/sys/company/list', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (79, 75, '0/1/71/75/', 4, '员工/授权', 'auth', '/admin/sys/auth', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (80, 75, '0/1/71/75/', 5, '账户管理', 'account', '/admin/sys/company/account', 1);

insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (81, 71, '0/1/71/', 3, '权限管理', 'auth', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (82, 81, '0/1/71/81/', 1, '权限列表', 'permission', '/admin/sys/permission/permission', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (83, 81, '0/1/71/81/', 2, '角色管理', 'role', '/admin/sys/permission/role', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (84, 81, '0/1/71/81/', 3, '资源列表', 'resource', '/admin/sys/resources', 1);

insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (85, 71, '0/1/71/', 4, '用户管理', 'user', '', 1);
insert into `sys_resources`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `enabled`)
  values (86, 85, '0/1/71/85/', 1, '用户列表', 'userList', '/admin/sys/user', 1);



delete from `sys_permission` where id>=1 and id<=1000;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `enabled`) values (1, '所有', '*', '所有数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `enabled`) values (2, '新增', 'create', '新增数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `enabled`) values (3,  '修改', 'update', '修改数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `enabled`) values (4,  '删除', 'delete', '删除数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `enabled`) values (5,  '查看', 'view', '查看数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `enabled`) values (6,  '审核', 'audit', '审核数据操作的权限', 1);

delete from `sys_role` where id>=1 and id<=1000;
insert into `sys_role` (`id`, `name`, `role`, `description`, `enabled`) values (1,  '超级管理员', 'admin', '拥有所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `enabled`) values (2,  '系统管理员', 'sys_admin', '拥有系统管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `enabled`) values (3,  '销售管理员', 'sales_admin', '拥有示例管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `enabled`) values (4,  '采购管理员', 'purchase_admin', '拥有维护管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `enabled`) values (5,  '库存管理员', 'stock_admin', '拥有新增/查看管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `enabled`) values (6,  '财务管理员', 'finance_admin', '拥有修改/查看管理的所有权限', 1);

delete from `sys_role_resource_permission` where id>=1 and id<=1000;
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(1, 1, 2, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(2, 1, 8, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(3, 1, 14, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(4, 1, 21, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(5, 1, 29, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(6, 1, 34, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(7, 1, 42, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(8, 1, 53, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(9, 1, 58, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(10, 1, 71, '1');


insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(11, 2, 2, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(12, 2, 8, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(13, 2, 14, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(14, 2, 21, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(15, 2, 29, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(16, 2, 34, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(17, 2, 42, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(18, 2, 53, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(19, 2, 58, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(20, 2, 72, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(21, 2, 75, '1');

insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(22, 3, 8, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(23, 4, 2, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(24, 5, 14, '1');

insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(25, 6, 21, '1');
insert into `sys_role_resource_permission` (`id`, `role_id`, `resource_id`, `permission_ids`) values(26, 6, 34, '1');


delete from `sys_auth` where id>=1 and id<=1000;
insert into `sys_auth` (`id`, `user_id`, `role_ids`) values(1, 1, 1);
insert into `sys_auth` (`id`, `user_id`, `role_ids`) values(2, 2, 2);
insert into `sys_auth` (`id`, `user_id`, `role_ids`) values(3, 3, 3);
insert into `sys_auth` (`id`, `user_id`, `role_ids`) values(4, 4, 4);
insert into `sys_auth` (`id`, `user_id`, `role_ids`) values(5, 5, 5);
insert into `sys_auth` (`id`, `user_id`, `role_ids`) values(6, 6, 6);

insert into `sys_user_organization_job`(`user_id`,`organization_id`) values(1,1);
insert into `sys_user_organization_job`(`user_id`,`organization_id`) values(2,1);
insert into `sys_user_organization_job`(`user_id`,`organization_id`) values(3,3);