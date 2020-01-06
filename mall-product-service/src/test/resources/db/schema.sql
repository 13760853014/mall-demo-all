-- ----------------------------
-- Table structure for activity_coupon
-- ----------------------------
DROP TABLE IF EXISTS mall_product;
CREATE TABLE mall_product (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  sku_code bigint(20) NOT NULL COMMENT '产品编码',
  sku_name varchar(128) DEFAULT NULL COMMENT '产品名称',
  cost_price bigint(20) NOT NULL COMMENT '成本价',
  line_price bigint(20) NOT NULL COMMENT '划线价',
  category_code varchar(24) NOT NULL COMMENT '分类编码',
  category_name varchar(128) NOT NULL COMMENT '分类名称',
  image varchar(128) DEFAULT NULL COMMENT '产品图片',
  inventory int NOT NULL COMMENT '库存',
  status tinyint DEFAULT 1 COMMENT '状态 0下架 1上架',
  created_date datetime NOT NULL COMMENT '创建时间',
  last_modified_date datetime NOT NULL COMMENT '最后修改时间',
  created_by varchar(20) NOT NULL COMMENT '创建人',
  last_modified_by varchar(20) NOT NULL COMMENT '最后修改人',
  record_version tinyint(3) NOT NULL DEFAULT 0 COMMENT '版本号',
  delete_flag tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除,1:删除)',
  PRIMARY KEY (id) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='产品表';