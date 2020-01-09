package com.jianke.demo.account.cache;

import com.jianke.demo.vo.MallAccountVo;

public interface DataCacheService {
    void setSession(String sessionId, MallAccountVo accountVo);

    MallAccountVo findBySessionId(String sessionId);
}
