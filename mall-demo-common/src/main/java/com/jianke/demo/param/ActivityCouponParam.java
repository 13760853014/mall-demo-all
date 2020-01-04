package com.jianke.demo.param;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * 商城活动优惠券
 *
 * @author huhua
 * @since 2019/3/8
 */
@Data
@Accessors(chain = true)
public class ActivityCouponParam implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Long couponId;

	@NotNull(message = "优惠券名称不能为空")
	private String couponName;

	private Long couponValue;

	private Long minConsume;

	private Integer type;

	private Integer amount;

	@NotNull(message = "创建人不能为空")
	private String createdBy;

	private Date createdDate;

	private String lastModifiedBy;

}
