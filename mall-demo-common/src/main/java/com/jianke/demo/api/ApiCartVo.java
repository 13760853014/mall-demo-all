package com.jianke.demo.api;

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
public class ApiCartVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

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
