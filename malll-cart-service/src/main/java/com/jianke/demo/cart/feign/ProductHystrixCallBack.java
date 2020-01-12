package com.jianke.demo.cart.feign;

import com.jianke.demo.vo.MallProductVo;
import org.springframework.stereotype.Component;

@Component
public class ProductHystrixCallBack implements ProductHystrixClient {
    @Override
    public MallProductVo findBySkuCode(Long skuCode) throws Exception {
        return null;
    }
}
