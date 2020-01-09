package com.jianke.demo.account.resource;

import com.jianke.demo.account.service.MallAccountService;
import com.jianke.demo.param.MallAccountParam;
import com.jianke.demo.vo.MallAccountVo;
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

@Api(value = "用户接口", description = "提供给管理前端的RESTful接口")
@RestController
@RequestMapping("/api/account")
public class ApiAccountController {

    @Autowired
    private MallAccountService mallAccountService;



    @ApiOperation(value = "用户注册接口")
    @PostMapping(value = "/_register")
    public ResponseEntity<MallAccountVo> register(@RequestBody MallAccountVo AccountVo) throws Exception {
        MallAccountVo vo = mallAccountService.register(AccountVo);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }

    @ApiOperation(value = "用户登录接口")
    @PostMapping(value = "/_login")
    public ResponseEntity<MallAccountVo> login(@RequestBody MallAccountVo AccountVo) throws Exception {
        MallAccountVo vo = mallAccountService.login(AccountVo);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @ApiOperation(value = "用户分页接口")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<MallAccountVo>> findAll(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(required = false) String couponName,
        @RequestParam(required = false) String createBy) throws Exception {
        MallAccountParam param = new MallAccountParam();
        Page<MallAccountVo> vos = mallAccountService.findByPage(param, page, size);
        return new ResponseEntity<>(vos, HttpStatus.OK);
    }
}
