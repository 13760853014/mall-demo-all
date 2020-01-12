package com.jianke.demo.cart.service.impl;

import com.jianke.demo.cart.feign.ProductHystrixClient;
import com.jianke.demo.cart.service.ProductService;
import com.jianke.demo.constants.RedisKey;
import com.jianke.demo.vo.MallProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductHystrixClient productHystrixClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Value("${cart.product.expire.time:600}")
    private Long expire;

    @Override
    public MallProductVo findBySkuCode(Long skuCode) {
        String key = RedisKey.getProductBySkuCodeKey(skuCode);
        Object obj = redisTemplate.opsForValue().get(key);
        if (obj != null) {
            return (MallProductVo) obj;
        }
        try {
            MallProductVo vo = productHystrixClient.findBySkuCode(skuCode);
            if (vo != null) {
                redisTemplate.opsForValue().set(key, vo, expire, TimeUnit.MINUTES);
                return vo;
            }
        } catch (Exception e) {
            log.error("根据skuCode={}获取产品信息失败{}", skuCode, e);
        }
        return null;
    }
}
