package com.jianke.demo.cart.service;

import com.jianke.demo.vo.MallProductVo;

public interface ProductService {

    MallProductVo findBySkuCode(Long skuCode);
}
