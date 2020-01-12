package com.jianke.demo.auth.cache;

import com.jianke.demo.vo.MallAccountVo;

public interface DataCacheService {
    void setSession(String sessionId, MallAccountVo accountVo);

    MallAccountVo findBySessionId(String sessionId);
}
