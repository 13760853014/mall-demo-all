package com.jianke.demo.product.resource;

import com.jianke.demo.param.MallProductParam;
import com.jianke.demo.product.service.MallProductService;
import com.jianke.demo.vo.MallProductVo;
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

@Api(value = "优惠券接口", description = "提供给管理前端的RESTful接口")
@RestController
@RequestMapping("/mgmt/product")
public class MallProductController {

    @Autowired
    private MallProductService mallProductService;

    @ApiOperation(value = "优惠券删除接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        mallProductService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券新增接口")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MallProductVo> save(@RequestBody MallProductVo productVo) throws Exception {
        MallProductVo vo = mallProductService.save(productVo);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券更新接口")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<MallProductVo> update(@PathVariable("id") Long id,
                                                   @RequestBody MallProductVo productVo) throws Exception {
        productVo.setId(id);
        MallProductVo result = mallProductService.update(productVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券查看接口")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<MallProductVo> findOne(@PathVariable("id") long id) throws Exception {
        MallProductVo productVo = mallProductService.findById(id);
        return new ResponseEntity<>(productVo, HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券分页接口")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<MallProductVo>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String couponName,
        @RequestParam(required = false) String createBy) throws Exception {
        MallProductParam param = new MallProductParam();
        Page<MallProductVo> vos = mallProductService.findByPage(param, page, size);
        return new ResponseEntity<>(vos, HttpStatus.OK);
    }
}
