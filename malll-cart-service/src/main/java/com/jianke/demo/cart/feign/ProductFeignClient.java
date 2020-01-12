package com.jianke.demo.cart.feign;

import com.jianke.demo.vo.MallProductVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:23
 **/

@FeignClient(value = "mall-product-service")
public interface ProductFeignClient {

    @GetMapping(value = "/svc/product")
    MallProductVo findBySkuCode(@RequestParam("skuCode") Long skuCode) throws Exception;
}
