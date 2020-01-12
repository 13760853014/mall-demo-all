package com.jianke.demo.cart.service;

import com.jianke.demo.api.ApiCartVo;
import com.jianke.demo.exception.BaseException;
import com.jianke.demo.param.MallCartParam;
import com.jianke.demo.service.BaseMgmtService;
import com.jianke.demo.vo.MallCartVo;

import java.util.List;

/**
 * @author XiaoXiong
 * @since 2018/10/11
 */
public interface ApiCartService extends BaseMgmtService<ApiCartVo, MallCartParam> {

    List<ApiCartVo> addSkuToCart(ApiCartVo vo) throws Exception;

    List<ApiCartVo> findByAccountId(String accountId) throws BaseException;
}
