SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for trade_order
-- ----------------------------
DROP TABLE IF EXISTS `trade_order`;
CREATE TABLE `trade_order` (
  `id` varchar(64) NOT NULL,
  `order_number` varchar(64) NOT NULL COMMENT '������',
  `statue` int(10) NOT NULL COMMENT '-1表示订单软删除，0表示洽谈中，2表示订单确认，3表示等待付款，4表示等待发货，5表示交易完成',
  `supply_user_id` varchar(64) DEFAULT NULL COMMENT '���id',
  `supply_user_name` varchar(255) DEFAULT NULL COMMENT '���name',
  `demand_user_id` varchar(64) DEFAULT NULL COMMENT '��id',
  `demand_name` varchar(255) DEFAULT NULL COMMENT '��name',
  `add_date` datetime DEFAULT NULL COMMENT '�µ�ʱ��',
  `ship_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `invoice_no` varchar(255) DEFAULT NULL COMMENT '��Ʊ��',
  `finish_time` datetime DEFAULT NULL COMMENT '���ʱ��',
  `goods_amount` decimal(10,2) DEFAULT NULL COMMENT '订单的成交数量',
  `evaluation_status` int(1) unsigned DEFAULT '0',
  `evaluation_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `evaluation_content` text COMMENT '��������',
  `receive_province` varchar(64) DEFAULT NULL COMMENT '�ջ�ʡ��',
  `receive_city` varchar(64) DEFAULT NULL COMMENT '�ջ�����',
  `receive_area` varchar(64) DEFAULT NULL COMMENT '�ջ�����',
  `receive_detail_area` varchar(64) DEFAULT NULL COMMENT '�ջ���ϸ����',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `supply_commission` double(255,0) DEFAULT NULL COMMENT '供应商费用',
  `demand_returncash` double(255,0) DEFAULT NULL COMMENT '采购商返现',
  `cash_pledge` double(255,0) DEFAULT NULL COMMENT '采购商押金',
  `goods_account_payable` double(255,0) DEFAULT NULL COMMENT '采购商应付款',
  `distribution` varchar(255) DEFAULT NULL COMMENT '配送方式',
  `payment` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `paymenttype` varchar(255) DEFAULT NULL COMMENT '付款类型',
  `goods_total_money` double(255,0) DEFAULT NULL COMMENT '产品总金额',
  `distribution_fee` double(255,0) DEFAULT NULL COMMENT '配送费用',
  `poundage` double(255,0) DEFAULT NULL COMMENT '支付手续费',
  `ordertotal` double(255,0) DEFAULT NULL COMMENT '订单总额',
  `transactiontype` varchar(255) DEFAULT NULL COMMENT '交易方式	1-合同交易，2-委托交易，3-自主交易，4-信托交易，5，现货交易，6，期货交易',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Table structure for trade_order_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_log`;
CREATE TABLE `trade_order_log` (
  `id` varchar(64) NOT NULL,
  `order_number` varchar(64) NOT NULL COMMENT '������',
  `statue` int(10) NOT NULL COMMENT '����״̬(�����ֵ�)',
  `supply_user_id` varchar(64) DEFAULT NULL COMMENT '���id',
  `supply_user_name` varchar(255) DEFAULT NULL COMMENT '���name',
  `demand_user_id` varchar(64) DEFAULT NULL COMMENT '��id',
  `demand_name` varchar(255) DEFAULT NULL COMMENT '��name',
  `add_date` datetime DEFAULT NULL COMMENT '�µ�ʱ��',
  `ship_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `invoice_no` varchar(255) DEFAULT NULL COMMENT '��Ʊ��',
  `finish_time` datetime DEFAULT NULL COMMENT '���ʱ��',
  `goods_amount` decimal(10,2) DEFAULT NULL COMMENT '�ܼ�Ǯ',
  `evaluation_status` int(1) unsigned DEFAULT '0',
  `evaluation_time` datetime DEFAULT NULL COMMENT '����ʱ��',
  `evaluation_content` text COMMENT '��������',
  `receive_province` varchar(64) DEFAULT NULL COMMENT '�ջ�ʡ��',
  `receive_city` varchar(64) DEFAULT NULL COMMENT '�ջ�����',
  `receive_area` varchar(64) DEFAULT NULL COMMENT '�ջ�����',
  `receive_detail_area` varchar(64) DEFAULT NULL COMMENT '�ջ���ϸ����',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `supply_commission` double(255,0) DEFAULT NULL COMMENT '供应商费用',
  `demand_returncash` double(255,0) DEFAULT NULL COMMENT '采购商返现',
  `cash_pledge` double(255,0) DEFAULT NULL COMMENT '采购商押金',
  `goods_account_payable` double(255,0) DEFAULT NULL COMMENT '采购商应付款',
  `distribution` varchar(255) DEFAULT NULL COMMENT '配送方式',
  `payment` varchar(255) DEFAULT NULL COMMENT '支付方式',
  `paymenttype` varchar(255) DEFAULT NULL COMMENT '付款类型',
  `goods_total_money` double(255,0) DEFAULT NULL COMMENT '产品总金额',
  `distribution_fee` double(255,0) DEFAULT NULL COMMENT '配送费用',
  `poundage` double(255,0) DEFAULT NULL COMMENT '支付手续费',
  `ordertotal` double(255,0) DEFAULT NULL COMMENT '订单总额',
  `transactiontype` varchar(255) DEFAULT NULL COMMENT '交易方式	1-合同交易，2-委托交易，3-自主交易，4-信托交易，5，现货交易，6，期货交易',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='������';

-- ----------------------------
-- Table structure for trade_order_supply_demand
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_supply_demand`;
CREATE TABLE `trade_order_supply_demand` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(64) NOT NULL COMMENT '����Id',
  `supply_id` varchar(64) DEFAULT '0',
  `demand_id` varchar(64) DEFAULT '0',
  `total_price` decimal(10,2) DEFAULT '1.00',
  `per_price` decimal(10,2) DEFAULT '0.00',
  `number` decimal(10,2) DEFAULT NULL,
  `image_path` varchar(128) DEFAULT '0' COMMENT 'ͼƬ',
  `price_unit_id` int(10) DEFAULT NULL,
  `number_unit_id` int(10) unsigned DEFAULT '0',
  `price_unit_value` varchar(64) DEFAULT NULL,
  `number_unit_value` varchar(64) DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `goods_name` varchar(64) DEFAULT NULL,
  `pgoods_name` varchar(64) DEFAULT NULL,
  `spec` varchar(64) DEFAULT NULL COMMENT '规格',
  `delflag` varchar(64) DEFAULT NULL COMMENT '删除标记（0：待确认；-1：删除；1：已确认）',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_56` (`supply_id`),
  KEY `FK_Reference_58` (`demand_id`),
  KEY `FK_Reference_52` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����������Ϣ��';

-- ----------------------------
-- Table structure for trade_order_supply_demand_log
-- ----------------------------
DROP TABLE IF EXISTS `trade_order_supply_demand_log`;
CREATE TABLE `trade_order_supply_demand_log` (
  `id` varchar(64) NOT NULL,
  `order_id` varchar(64) NOT NULL COMMENT '����Id',
  `supply_id` varchar(64) DEFAULT '0',
  `demand_id` varchar(64) DEFAULT '0',
  `total_price` decimal(10,2) DEFAULT '1.00',
  `per_price` decimal(10,2) DEFAULT '0.00',
  `number` decimal(10,2) DEFAULT NULL,
  `image_path` varchar(128) DEFAULT '0' COMMENT 'ͼƬ',
  `price_unit_id` int(10) DEFAULT NULL,
  `number_unit_id` int(10) unsigned DEFAULT '0',
  `price_unit_value` varchar(64) DEFAULT NULL,
  `number_unit_value` varchar(64) DEFAULT '0',
  `remarks` varchar(255) DEFAULT NULL,
  `goods_name` varchar(64) DEFAULT NULL,
  `pgoods_name` varchar(64) DEFAULT NULL,
  `spec` varchar(64) DEFAULT NULL COMMENT '规格',
  `delflag` varchar(64) DEFAULT NULL COMMENT '删除标记（0：待确认；-1：删除；1：已确认）',
  `update_date` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_56` (`supply_id`),
  KEY `FK_Reference_58` (`demand_id`),
  KEY `FK_Reference_52` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='����������Ϣ��';