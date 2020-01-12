package com.jianke.demo.product.resource;

import com.jianke.demo.product.service.MallProductService;
import com.jianke.demo.vo.MallProductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:23
 **/

@Api(value = "优惠券接口", description = "提供给管理前端的RESTful接口")
@RestController
@RequestMapping("/svc/product")
public class SvcProductController {

    @Autowired
    private MallProductService mallProductService;


    @ApiOperation(value = "优惠券查看接口")
    @GetMapping
    public ResponseEntity<MallProductVo> findBySkuCode(@RequestParam("skuCode") Long skuCode) throws Exception {
        MallProductVo productVo = mallProductService.findBySkuCode(skuCode);
        return new ResponseEntity<>(productVo, HttpStatus.OK);
    }
}
