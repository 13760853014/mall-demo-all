package com.jianke.demo.cart.source;

import com.jianke.demo.api.ApiCartVo;
import com.jianke.demo.cart.service.ApiCartService;
import com.jianke.demo.cart.utils.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:23
 **/

@Api(value = "购物车接口", description = "提供给管理前端的RESTful接口")
@RestController
@RequestMapping("/api/cart")
public class ApiCartController {

    @Autowired
    private ApiCartService apiCartService;

    @ApiOperation(value = "购物车删除接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        apiCartService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "购物车新增接口")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<ApiCartVo>> addSkuToCart(@RequestBody ApiCartVo apiCartVo) throws Exception {
        List<ApiCartVo> cartVos = apiCartService.addSkuToCart(apiCartVo);
        return new ResponseEntity<>(cartVos, HttpStatus.OK);
    }

    @ApiOperation(value = "购物车删除接口")
    @GetMapping
    public ResponseEntity<List<ApiCartVo>> findByAccountId() throws Exception {
        String accountId = SecurityUtils.getAccountId();
        List<ApiCartVo> cartVos = apiCartService.findByAccountId(accountId);
        return new ResponseEntity(cartVos, HttpStatus.OK);
    }

}
