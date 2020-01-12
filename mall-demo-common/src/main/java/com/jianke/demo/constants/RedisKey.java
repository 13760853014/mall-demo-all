package com.jianke.demo.constants;

public class RedisKey {

    public static final String PRODUCT_SERVICE = "product-service:";

    public static String getProductBySkuCodeKey(Long skuCode) {
        return new StringBuilder(PRODUCT_SERVICE).append(skuCode).toString().toLowerCase();
    }
}
