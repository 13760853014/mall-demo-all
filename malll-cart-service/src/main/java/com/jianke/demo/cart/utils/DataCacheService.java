package com.jianke.demo.cart.utils;

import com.jianke.demo.vo.MallAccountVo;

public interface DataCacheService {
    void setSession(String sessionId, MallAccountVo accountVo);

    MallAccountVo findBySessionId(String sessionId);
}
