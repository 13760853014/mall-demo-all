package com.jianke.demo.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jianke.demo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
public class MallAccountVo extends BaseEntityVo<Long> implements Serializable {

	private static final long serialVersionUID = 1L;

	String accountId;

	@NotBlank(message = "用户名不能为空")
	String username;

    @NotBlank(message = "密码不能为空")
	String password;

	Integer phone;

	String email;
	/**
	 * 状态 0禁用 1可用
	 */
	private Integer status;

}
