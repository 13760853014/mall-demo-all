-- ----------------------------
-- Table structure for activity_coupon
-- ----------------------------
DROP TABLE IF EXISTS `activity_coupon`;
CREATE TABLE `activity_coupon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coupon_id` bigint(20) NOT NULL COMMENT '优惠券活动ID',
  `coupon_name` varchar(128) DEFAULT NULL COMMENT '优惠券名称',
  `type` tinyint(2) NOT NULL COMMENT '卡券类型:(1:分享即获得, 2: 老用户下单奖励分享者, 3: 新用户下单奖励分享者, 4: 被分享者通用奖励, 5:被分享者新用户奖励)',
  `amount` int(8) NOT NULL DEFAULT 0 COMMENT '数量',
  `created_date` datetime NOT NULL COMMENT '创建时间',
  `last_modified_date` datetime NOT NULL COMMENT '最后修改时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建人',
  `last_modified_by` varchar(20) NOT NULL COMMENT '最后修改人',
  `record_version` tinyint(3) NOT NULL DEFAULT 0 COMMENT '版本号',
  `delete_flag` tinyint(2) NOT NULL DEFAULT 0 COMMENT '是否删除(0:未删除,1:删除)',
  `coupon_value` bigint(10) DEFAULT NULL COMMENT '优惠券面额',
  `start_draw_date` datetime DEFAULT NULL COMMENT '优惠券使用开始',
  `end_draw_date` datetime DEFAULT NULL COMMENT '优惠券使用结束时间',
  `min_consume` bigint(20) DEFAULT NULL COMMENT '优惠券最低使用金额',
  `relative_flag` tinyint(2) DEFAULT NULL COMMENT '有效时间标志',
  `relative_days` int(10) DEFAULT NULL COMMENT '有效时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='商城活动优惠券关联表';