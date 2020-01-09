package com.jianke.demo.product.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jianke.demo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商城活动优惠券
 *
 * @author huhua
 * @since 2019/3/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mall_product")
public class MallProduct extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 产品编码
	 */
	private Long skuCode;

	/**
	 * 产品名称
	 */
	private String skuName;

	/**
	 * 成本价
	 */
	private Long costPrice;

	/**
	 * 划线价
	 */
	private Long linePrice;

	/**
	 * 分类编码
	 */
	private String categoryCode;

	/**
	 * 分类名称
	 */
	private String categoryName;

	/**
	 * 产品图片
	 */
	private String image;

	/**
	 * 库存
	 */
	private Integer inventory;

	/**
	 * 状态 0下架 1上架
	 */
	private Integer status;

}
