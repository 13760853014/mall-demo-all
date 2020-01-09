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
@RequestMapping("/mgmt/account")
public class MallAccountController {

    @Autowired
    private MallAccountService mallAccountService;

    @ApiOperation(value = "用户删除接口")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") Long id) throws Exception {
        mallAccountService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }


    @ApiOperation(value = "用户新增接口")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<MallAccountVo> save(@RequestBody MallAccountVo AccountVo) throws Exception {
        MallAccountVo vo = mallAccountService.save(AccountVo);
        return new ResponseEntity<>(vo, HttpStatus.OK);
    }


    @ApiOperation(value = "用户更新接口")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<MallAccountVo> update(@PathVariable("id") Long id,
                                                   @RequestBody MallAccountVo AccountVo) throws Exception {
        AccountVo.setId(id);
        MallAccountVo result = mallAccountService.update(AccountVo);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @ApiOperation(value = "用户查看接口")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<MallAccountVo> findOne(@PathVariable("id") long id) throws Exception {
        MallAccountVo AccountVo = mallAccountService.findById(id);
        return new ResponseEntity<>(AccountVo, HttpStatus.OK);
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
