#如果复制到mysql中执行时 加上
#DELIMITER ;
use ace;
delete from `sys_user` where id>=1 and id<=1000;
/*默认admin/123456*/
insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `admin`)
  values
  (1, 'admin', 'admin@ace.com', '13412345671', 'ec21fa1738f39d5312c6df46002d403d', 'yDd1956wn1', sysdate(), 'normal', 1);
insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `admin`)
  values
  (2, 'sys_admin', 'sys_admin@ace.com', '13412345673', '3152e2566dc8075b2b2ac0ee0ee2424d', 'MANHOoCpnb', sysdate(), 'normal', 0);
insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `admin`)
  values
  (3, 'sales_admin', 'sales_admin@ace.com', '13412345672', 'aa54c5ee4922d3ee19db7009f541e0d6', 'hSSixwNQwt', sysdate(), 'normal', 0);

insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `admin`)
  values
  (4, 'purchase_admin', 'purchase_admin@ace.com', '13412345674', 'e75a4379059f918256aa6a2f0b81bfa5', 'iY71e4dtoa', sysdate(), 'normal', 0);

insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `admin`)
  values
  (5, 'stock_admin', 'stock_admin@ace.com', '13412345675', '1c7f3d8e6fa84d92f43c88bd6082b9ce', 'iruPxupgfb', sysdate(), 'normal', 0);

insert into `sys_user`
(`id`, `username`, `email`, `mobile`, `password`, `salt`, `create_time`, `status`, `admin`)
  values
  (6, 'finance_admin', 'finance_admin@ace.com', '13412345676', 'f30527ae456a700c5cfd079b847989fc', '2WQx5LmvlV', sysdate(), 'normal', 0);



delete from `sys_organization` where id>=1 and id<=1000;
insert into `sys_organization`(`id`, `parent_id`,`user_count`, `name`, `status`) values (1,0, 200, '北京ACE系统有限公司', 0);
insert into `sys_organization`(`id`, `parent_id`,`user_count`, `name`, `status`) values (2,1, 200, '上海ACE系统有限公司', 0);
insert into `sys_organization`(`id`, `parent_id`,`user_count`, `name`, `status`) values (3,1, 200, '长沙ACE系统有限公司', 0);


delete from `sys_resource` where id>=1 and id<=1000;
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (1, 0, '0/', 1, '资源', '', '', true);
/** 采购资源 **/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (2, 1, '0/1/', 1, '采购管理', 'purchase', '', true,'icon-shopping-cart');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (3, 2, '0/1/2/', 1, '采购单', 'orderPurchase', '/purchase/orderPurchase', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (4, 2, '0/1/2/', 2, '采购单列表', 'purchaseList', '/purchase/purchaseList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (5, 2, '0/1/2/', 3, '采购退货', 'purchaseReturn', '/purchase/purchaseReturn', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (6, 2, '0/1/2/', 4, '退货单列表', 'purchaseReturnList', '/purchase/purchaseReturnList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (7, 2, '0/1/2/', 5, '供应商账龄报表', 'purchaseAgingSearch', '/purchase/purchaseAgingSearch', true);

/**  销售资源 ****/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (8, 1, '0/1/', 2, '销售管理', 'sales', '', true, 'icon-leaf');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (9, 8, '0/1/8/', 1, '新增销售单', 'addSalesOrder', '/sales/addSalesOrder', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (10, 8, '0/1/8/', 2, '销售单列表', 'salesOrderList', '/sales/salesOrderList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (11, 8, '0/1/8/', 3, '新增销售退货', 'addSalesReturn', '/sales/addSalesReturn', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (12, 8, '0/1/8/', 4, '销售退货列表', 'salesReturnList', '/sales/salesReturnList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (13, 8, '0/1/8/', 5, '客户账龄表', 'salesAgingSearch', '/sales/salesAgingSearch', true);

/** 库存资源 ***/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (14, 1, '0/1/', 3, '库存管理', 'stock', '', true,'icon-home');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (15, 14, '0/1/14/', 1, '新增货品', 'addInv', '/stock/addInv', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (16, 14, '0/1/14/', 2, '库存列表', 'invList', '/stock/invList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (17, 14, '0/1/14/', 3, '新增调拨单', 'addTransfer', '/stock/addTransfer', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (18, 14, '0/1/14/', 4, '调拨单列表', 'transferList', '/stock/transferList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (19, 14, '0/1/14/', 5, '借入单', 'borrowed', '/stock/borrowedOrder', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (20, 14, '0/1/14/', 6, '借出单', 'loan', '/stock/loanOrder', true);

/**  资金流 ---***/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (21, 1, '0/1/', 4, '财务记账', 'finance', '', true, 'icon-usd');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (22, 21, '0/1/21/', 1, '收支流水', 'payments ', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (23, 22, '0/1/21/22/', 1, '日常收支', 'dayAccount', '/finance/payments/dayAccount', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (24, 22, '0/1/21/22/', 2, '收支项目管理', 'accountList', '/finance/payments/accountList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (25, 21, '0/1/21/', 2, '资金往来', 'cashDealing', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (26, 25, '0/1/21/25/', 1, '应收款', 'receivables', '/finance/cashDealing/receivables', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (27, 25, '0/1/21/25/', 2, '应付款', 'payables', '/finance/cashDealing/payables', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (28, 25, '0/1/21/25/', 3, '资金流水', 'cashFlow', '/finance/cashDealing/cashFlow', true);



/**  客户与欠款资源 ***/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (29, 1, '0/1/', 5, '客户与欠款', 'customer', '', true,'icon-user');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (30, 29, '0/1/29/', 1, '新增客户', 'addCustomer', '/customer/addCustomer', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (31, 29, '0/1/29/', 2, '客户列表', 'customerList', '/customer/customerList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (32, 29, '0/1/29/', 3, '客户分类', 'customerCategory', '/customer/customerCategory', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (33, 29, '0/1/29/', 4, '收客户欠款', 'incomeCustomerArrears', '/customer/incomeCustomerArrears', true);


/***  供应商与欠款 ***/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (34, 1, '0/1/', 6, '供应商与欠款', 'supplier', '', true,'icon-group');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (35, 34, '0/1/34/', 1, '新增供应商', 'addSupplier', '/supplier/addSupplier', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (36, 34, '0/1/34/', 2, '供应商列表', 'supplierList', '/supplier/supplierList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (37, 34, '0/1/34/', 3, '供应商分类', 'supplierCategory', '/supplier/supplierCategory', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (38, 34, '0/1/34/', 4, '付供应商欠款', 'paySupplierArrears', '/supplier/paySupplierArrears', true);

/***  大客户批发价 **/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (39, 1, '0/1/', 7, '大客户批发价', 'bigWSPrice', '', true,'icon-desktop');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (40, 39, '0/1/39/', 1, '新增客户批发价', 'addCWSPrice', '/bigWSPrice/addCWSPrice', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (41, 39, '0/1/39/', 2, '客户批发价列表', 'cwsPriceList', '/bigWSPrice/cwsPriceList', true);

/**  经营报告 **/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (42, 1, '0/1/', 8, '经营报告', 'reports', '', true,'icon-bar-chart');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (43, 42, '0/1/42/', 1, '采购', '', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (44, 43, '0/1/42/43/', 1, '采购曲线图', 'purchaseGraphReport', '/reports/purchase/purchaseGraphReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (45, 43, '0/1/42/43/', 2, '采购统计', 'purchaseReport', '/reports/purchase/purchaseReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (46, 42, '0/1/42/', 2, '销售', '', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (47, 46, '0/1/42/46/', 1, '销售曲线图', 'salesGraphReport', '/reports/sales/salesGraphReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (48, 46, '0/1/42/46/', 2, '销售统计', 'salesReport', '/reports/sales/salesGraphReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (49, 46, '0/1/42/46/', 3, '员工销售报表', 'personnelSalesReport', '/reports/sales/personnelSalesReport', true);


insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
    values (53, 42, '0/1/42/', 4, '综合经营状况', 'manage', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
    values (54, 53, '0/1/42/53/', 1, '进销存统计', 'erpReport', '/reports/manage/erpReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
    values (55, 53, '0/1/42/53/', 2, '资金统计报表', 'fundsReport', '/reports/manage/fundsReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
    values (56, 53, '0/1/42/53/', 3, '应付欠款报表', 'payArrearsReport', '/reports/manage/payArrearsReport', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
    values (57, 53, '0/1/42/53/', 4, '供应商账龄表', 'supplierPaymentsReport', '/reports/manage/supPaymentsReport', true);



/**  基础资料 **/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (58, 1, '0/1/', 9, '基础资料', 'baseInfo', '', true,'icon-edit');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (59, 58, '0/1/58/', 1, '商品资料', 'product', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (60, 59, '0/1/58/59/', 1, '新增商品', 'addProductInfo', '/baseInfo/product/addProductInfo', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (61, 59, '0/1/58/59/', 2, '商品分类', 'productCategory', '/baseInfo/product/productCategory', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (62, 59, '0/1/58/59/', 3, '商品列表', 'productList', '/baseInfo/product/productList', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (63, 59, '0/1/58/59/', 4, '单位设置', 'unit', '/baseInfo/product/unit', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (64, 59, '0/1/58/59/', 5, '颜色管理', 'color', '/baseInfo/product/color', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (65, 59, '0/1/58/59/', 6, '尺码管理', 'size', '/baseInfo/product/size', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (66, 59, '0/1/58/59/', 7, '品牌管理', 'brand', '/baseInfo/product/brand', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (67, 58, '0/1/58/', 2, '收付款账户', 'payment', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (68, 67, '0/1/58/67/', 1, '账户管理', 'account', '/baseInfo/payment/account', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (69, 58, '0/1/58/', 3, '仓库管理', '', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (70, 69, '0/1/58/69/', 1, '仓库信息管理', '', '/baseInfo/stock', true);

/**  系统设置 **/
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`,`icon`)
  values (71, 1, '0/1/', 10, '系统设置', 'sys', '', true,'icon-cogs');
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (72, 71, '0/1/71/', 1, '参数设置', 'parameter', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (73, 72, '0/1/71/72', 1, '货币类型', 'currency', '/admin/sys/parameter/currency', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (74, 72, '0/1/72/72', 2, '单据编码规则', 'receipt', '/admin/sys/parameter/receipt', true);

insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (75, 71, '0/1/71/', 2, '公司管理', 'company', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (76, 75, '0/1/71/75/', 1, '公司档案', 'company', '/admin/sys/company/company', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (77, 75, '0/1/71/75/', 2, '职员档案', 'employee', '/admin/sys/company/employee', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (78, 75, '0/1/71/75/', 3, '公司列表', 'personProfile', '/admin/sys/company/list', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (79, 75, '0/1/71/75/', 4, '员工/授权', 'auth', '/admin/sys/auth', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (80, 75, '0/1/71/75/', 5, '账户管理', 'account', '/admin/sys/company/account', true);

insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (81, 71, '0/1/71/', 3, '权限管理', 'auth', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (82, 81, '0/1/71/81/', 1, '权限列表', 'permission', '/admin/sys/permission/permission', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (83, 81, '0/1/71/81/', 2, '角色管理', 'role', '/admin/sys/permission/role', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (84, 81, '0/1/71/81/', 3, '资源列表', 'resource', '/admin/sys/resource', true);

insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (85, 71, '0/1/71/', 4, '用户管理', 'user', '', true);
insert into `sys_resource`(`id`, `parent_id`, `parent_ids`, weight, `name`, `identity`, `url`, `is_show`)
  values (86, 85, '0/1/71/85/', 1, '用户列表', 'userList', '/admin/sys/user', true);



delete from `sys_permission` where id>=1 and id<=1000;
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (1, '所有', '*', '所有数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (2, '新增', 'create', '新增数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (3,  '修改', 'update', '修改数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (4,  '删除', 'delete', '删除数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (5,  '查看', 'view', '查看数据操作的权限', 1);
insert into `sys_permission` (`id`, `name`, `permission`, `description`, `is_show`) values (6,  '审核', 'audit', '审核数据操作的权限', 1);

delete from `sys_role` where id>=1 and id<=1000;
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (1,  '超级管理员', 'admin', '拥有所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (2,  '系统管理员', 'sys_admin', '拥有系统管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (3,  '销售管理员', 'sales_admin', '拥有示例管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (4,  '采购管理员', 'purchase_admin', '拥有维护管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (5,  '库存管理员', 'stock_admin', '拥有新增/查看管理的所有权限', 1);
insert into `sys_role` (`id`, `name`, `role`, `description`, `is_show`) values (6,  '财务管理员', 'finance_admin', '拥有修改/查看管理的所有权限', 1);

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
insert into sys_auth (`id`, `user_id`, `role_ids`, `organization_id`, `type`,`create_time`,`modify_time`)
  values(1, 1, 1, 1, 'user', sysdate(), sysdate());
insert into sys_auth (`id`, `user_id`,  `role_ids`, `organization_id`, `type`,`create_time`,`modify_time`)
  values(2, 2, 2, 1, 'user', sysdate(), sysdate());
insert into sys_auth (`id`, `user_id`,  `role_ids`, `organization_id`, `type`,`create_time`,`modify_time`)
  values(3, 3, 3, 1, 'user', sysdate(), sysdate());
insert into sys_auth (`id`,  `user_id`, `role_ids`, `organization_id`, `type`,`create_time`,`modify_time`)
  values(4, 4, 4, 1, 'user', sysdate(), sysdate());
insert into sys_auth (`id`, `user_id`,  `role_ids`, `organization_id`, `type`,`create_time`,`modify_time`)
  values(5, 5, 5, 1, 'user', sysdate(), sysdate());
insert into sys_auth (`id`, `user_id`,  `role_ids`, `organization_id`, `type`,`create_time`,`modify_time`)
  values(6, 6, 6, 1, 'user', sysdate(), sysdate());


