package com.jianke.demo.account.entity;

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
@TableName("mall_account")
public class MallAccount extends BaseEntity<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	String accountId;

	String username;

	String password;

	Integer phone;

	String email;
	/**
	 * 状态 0禁用 1可用
	 */
	private Integer status;

}
