package com.jianke.demo.account.cache;

import com.jianke.demo.vo.MallAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DataCacheServiceImpl implements DataCacheService{

    private static final String ACCOUNT_KEY = "account-service:accountId:";

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public void setSession(String sessionId, MallAccountVo accountVo) {
        redisTemplate.opsForValue().set(sessionId, accountVo, 30, TimeUnit.MINUTES);
    }

    @Override
    public MallAccountVo findBySessionId(String sessionId) {
        Object object = redisTemplate.opsForValue().get(sessionId);
        return (object == null) ? null : (MallAccountVo) object;
    }
}
