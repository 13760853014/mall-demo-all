package com.jianke.demo.cart.entity;

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
@TableName("mall_cart")
public class MallCart extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户Id
	 */
	private String accountId;

	/**
	 * 产品编码
	 */
	private Long skuCode;

	/**
	 * 产品名称
	 */
	private String skuName;

	/**
	 * 产品价格
	 */
	private Long skuPrice;

	/**
	 * 产品数量
	 */
	private Integer skuNum;

	/**
	 * 产品状态
	 */
	private Integer skuStatus;


}
