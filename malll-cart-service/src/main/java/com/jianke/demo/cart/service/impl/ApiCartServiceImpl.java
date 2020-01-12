package com.jianke.demo.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jianke.demo.api.ApiCartVo;
import com.jianke.demo.auth.utils.SecurityUtils;
import com.jianke.demo.cart.entity.MallCart;
import com.jianke.demo.cart.feign.ProductFeignClient;
import com.jianke.demo.cart.feign.ProductHystrixClient;
import com.jianke.demo.cart.mapper.MallCartMapper;
import com.jianke.demo.cart.service.ApiCartService;
import com.jianke.demo.cart.service.ProductService;
import com.jianke.demo.exception.BaseException;
import com.jianke.demo.exception.ValidateException;
import com.jianke.demo.param.MallCartParam;
import com.jianke.demo.service.BaseQueryServiceAdapter;
import com.jianke.demo.utils.BeanUtil;
import com.jianke.demo.utils.ValidatorUtil;
import com.jianke.demo.vo.MallProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:27
 **/
@Service
public class ApiCartServiceImpl extends BaseQueryServiceAdapter<ApiCartVo, MallCartParam> implements ApiCartService {

    @Autowired
    private MallCartMapper mallCartMapper;

    @Autowired
    private ProductService productService;

    @Override
    public ApiCartVo findById(Long id) throws BaseException {
        MallCart Cart = mallCartMapper.selectById(id);
        return BeanUtil.convert(Cart, ApiCartVo.class);
    }


    @Override
    public List<ApiCartVo> addSkuToCart(ApiCartVo vo) throws Exception {
        String accountId = SecurityUtils.getAccountId();
        MallProductVo productVo = productService.findBySkuCode(vo.getSkuCode());
        if (productVo == null) {
            throw new BaseException("产品" + vo.getSkuCode() + "不存在");
        }

        MallCart cart = mallCartMapper.selectOne(new QueryWrapper<MallCart>().eq("account_id", accountId).eq("sku_code", vo.getSkuCode()));
        if (cart != null) {
            cart.setSkuNum(cart.getSkuNum() + vo.getSkuNum());
            cart.setSkuPrice(productVo.getLinePrice());
            mallCartMapper.updateById(cart);
            return findByAccountId(accountId);
        }

        cart = new MallCart();
        cart.setAccountId(accountId);
        cart.setSkuCode(vo.getSkuCode());
        cart.setSkuNum(vo.getSkuNum());
        cart.setCreatedBy(accountId);
        cart.setCreatedDate(new Date());
        cart.setSkuName(productVo.getSkuName());
        cart.setSkuPrice(productVo.getLinePrice());
        mallCartMapper.insert(cart);
        return findByAccountId(accountId);
    }

    @Override
    public List<ApiCartVo> findByAccountId(String accountId) throws BaseException {
        List<MallCart> carts = mallCartMapper.selectList(new QueryWrapper<MallCart>().eq("account_id", accountId));
        return BeanUtil.convert(carts, ApiCartVo.class);
    }



}
