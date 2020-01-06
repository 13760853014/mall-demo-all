package com.jianke.demo.mapper;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.jianke.demo.product.MallProductServiceApplication;
import com.jianke.demo.product.config.MybatisPlusConfig;
import com.jianke.demo.product.entity.MallProduct;
import com.jianke.demo.product.mapper.MallProductMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-13 20:46
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {
        MallProductServiceApplication.class,
        DataSourceAutoConfiguration.class,
        MybatisPlusAutoConfiguration.class,
        MybatisPlusConfig.class
})
public class MallProductMapperTest {

    @Autowired
    private MallProductMapper mapper;

    @Test
    public void testFindBySettingId(){
        MallProduct activityCoupon = mapper.selectById(3);
        assertNotNull(activityCoupon);
    }
}
