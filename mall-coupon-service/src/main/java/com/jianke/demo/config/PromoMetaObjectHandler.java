package com.jianke.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class PromoMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("deleteFlag", 0, metaObject);

        setFieldValByName("recordVersion", 0, metaObject);

        setFieldValByName("createdBy", "createdBy", metaObject);

        setFieldValByName("createdDate",  Date.from(Instant.now()),metaObject);

        setFieldValByName("lastModifiedBy", "lastModifiedBy", metaObject);

        setFieldValByName("lastModifiedDate", Date.from(Instant.now()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("lastModifiedBy", "lastModifiedBy1", metaObject);
        setFieldValByName("lastModifiedDate", Date.from(Instant.now()), metaObject);
    }
}
