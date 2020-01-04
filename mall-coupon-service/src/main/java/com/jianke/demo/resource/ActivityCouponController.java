package com.jianke.demo.resource;

import com.jianke.demo.param.ActivityCouponParam;
import com.jianke.demo.service.ActivityCouponService;
import com.jianke.demo.vo.ActivityCouponVo;
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
@RequestMapping("/mgmt/demo")
public class ActivityCouponController {

    @Autowired
    private ActivityCouponService activityCouponService;

    @ApiOperation(value = "优惠券删除接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        activityCouponService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券新增接口")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ActivityCouponVo> save(@RequestBody ActivityCouponVo activityVo) throws Exception {
        ActivityCouponVo activityCouponVo = activityCouponService.save(activityVo);
        return new ResponseEntity<>(activityCouponVo, HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券更新接口")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<ActivityCouponVo> update(@PathVariable("id") Long id,
                                                   @RequestBody ActivityCouponVo activityCouponVo) throws Exception {
        activityCouponVo.setId(id);
        ActivityCouponVo result = activityCouponService.update(activityCouponVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券查看接口")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<ActivityCouponVo> findOne(@PathVariable("id") long id) throws Exception {
        ActivityCouponVo activityCouponVo = activityCouponService.findById(id);
        return new ResponseEntity<>(activityCouponVo, HttpStatus.OK);
    }


    @ApiOperation(value = "优惠券分页接口")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ActivityCouponVo>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String couponName,
        @RequestParam(required = false) String createBy) throws Exception {
        ActivityCouponParam param = new ActivityCouponParam();
        if (couponName != null) {
            param.setCouponName(couponName);
        }
        if (createBy != null) {
            param.setCreatedBy(createBy);
        }
        Page<ActivityCouponVo> vos = activityCouponService.findByPage(param, page, size);
        return new ResponseEntity<>(vos, HttpStatus.OK);
    }
}
