package com.jianke.demo.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 商城活动优惠券
 *
 * @author huhua
 * @since 2019/3/8
 */
@Data
@Accessors(chain = true)
public class MallProductParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long skuCode;

	private String skuName;

	private String categoryCode;

	private Integer status;

}
