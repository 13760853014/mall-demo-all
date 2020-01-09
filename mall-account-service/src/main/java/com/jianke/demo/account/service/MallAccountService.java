package com.jianke.demo.account.service;

import com.jianke.demo.param.MallAccountParam;
import com.jianke.demo.service.BaseMgmtService;
import com.jianke.demo.vo.MallAccountVo;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author XiaoXiong
 * @since 2018/10/11
 */
public interface MallAccountService extends BaseMgmtService<MallAccountVo, MallAccountParam> {

    MallAccountVo register(MallAccountVo accountVo) throws Exception;

    MallAccountVo login(MallAccountVo accountVo) throws Exception;
}
