package com.jianke.demo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author huhua
 * @since 2019/3/8
 */
@Data
@Accessors(chain = true)
public class ActivityCouponVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

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

	private String createdBy;

	private Date createdDate;

	private String lastModifiedBy;

	private Date lastModifiedDate;

	private Integer recordVersion;

	private Integer deleteFlag;


}
