package com.jianke.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 商城活动优惠券
 *
 * @author huhua
 * @since 2019/3/8
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("activity_coupon")
public class ActivityCoupon extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long couponId;

	private String couponName;

	private Long couponValue;

	private Long minConsume;

	private Integer type;

	private Integer amount;

	private Date startDrawDate;

	private Date endDrawDate;

	private Byte relativeFlag;

	private Integer relativeDays;

}
