package com.jianke.demo.cart.source;

import com.jianke.demo.cart.service.MallCartService;
import com.jianke.demo.param.MallCartParam;
import com.jianke.demo.vo.MallCartVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:23
 **/

@Api(value = "购物车接口", description = "提供给管理前端的RESTful接口")
@RestController
@RequestMapping("/mgmt/cart")
public class MallCartController {

    @Autowired
    private MallCartService mallCartService;

    @ApiOperation(value = "购物车删除接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        mallCartService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "购物车新增接口")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MallCartVo> save(@RequestBody MallCartVo CartVo) throws Exception {
        MallCartVo vo = mallCartService.save(CartVo);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @ApiOperation(value = "购物车更新接口")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<MallCartVo> update(@PathVariable("id") Long id,
                                                   @RequestBody MallCartVo CartVo) throws Exception {
        CartVo.setId(id);
        MallCartVo result = mallCartService.update(CartVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "购物车查看接口")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<MallCartVo> findOne(@PathVariable("id") long id) throws Exception {
        MallCartVo CartVo = mallCartService.findById(id);
        return new ResponseEntity<>(CartVo, HttpStatus.OK);
    }


    @ApiOperation(value = "购物车分页接口")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<MallCartVo>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String couponName,
        @RequestParam(required = false) String createBy) throws Exception {
        MallCartParam param = new MallCartParam();
        Page<MallCartVo> vos = mallCartService.findByPage(param, page, size);
        return new ResponseEntity<>(vos, HttpStatus.OK);
    }
}
