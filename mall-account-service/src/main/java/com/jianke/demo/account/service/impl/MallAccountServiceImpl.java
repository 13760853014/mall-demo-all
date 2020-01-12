package com.jianke.demo.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jianke.demo.account.cache.DataCacheService;
import com.jianke.demo.account.entity.MallAccount;
import com.jianke.demo.account.mapper.MallAccountMapper;
import com.jianke.demo.account.service.MallAccountService;
import com.jianke.demo.constant.Constant;
import com.jianke.demo.account.utils.CookieUtil;
import com.jianke.demo.exception.BaseException;
import com.jianke.demo.exception.ValidateException;
import com.jianke.demo.param.MallAccountParam;
import com.jianke.demo.service.BaseQueryServiceAdapter;
import com.jianke.demo.utils.BeanUtil;
import com.jianke.demo.utils.MD5Util;
import com.jianke.demo.utils.ValidatorUtil;
import com.jianke.demo.vo.MallAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:27
 **/
@Service
public class MallAccountServiceImpl extends BaseQueryServiceAdapter<MallAccountVo, MallAccountParam> implements MallAccountService {

    @Autowired
    private MallAccountMapper mallAccountMapper;

    @Autowired
    private DataCacheService dataCacheService;

    @Override
    public MallAccountVo findById(Long id) throws BaseException {
        MallAccount Account = mallAccountMapper.selectById(id);
        return BeanUtil.convert(Account, MallAccountVo.class);
    }


    @Override
    public MallAccountVo save(MallAccountVo vo) throws BaseException {
        MallAccount Account = BeanUtil.convert(vo, MallAccount.class);
        mallAccountMapper.insert(Account);
        return findById(Account.getId());
    }


    @Override
    public void deleteById(Long id) throws BaseException {
        MallAccount activityCoupon = mallAccountMapper.selectById(id);
        if (activityCoupon == null) {
            throw new BaseException("优惠券不存在");
        }
        mallAccountMapper.deleteById(id);
    }


    @Override
    public MallAccountVo update(MallAccountVo vo) throws BaseException {
        MallAccount oldAccount = mallAccountMapper.selectById(vo.getId());
        if (oldAccount == null) {
            throw new BaseException("0001","优惠券不存在");
        }
        MallAccount Account = BeanUtil.convert(vo, MallAccount.class);
        mallAccountMapper.updateById(Account);
        return findById(Account.getId());
    }


    @Override
    public Page<MallAccountVo> findByPage(MallAccountParam param, int pageIndex, int pageSize) throws BaseException {
        if (pageIndex < 0) {
            throw new ValidateException("页数不合法！");
        }
        if (pageSize <= 0) {
            throw new ValidateException("页面显示数据条数不合法！");
        }
        ValidatorUtil.validate(param);

        QueryWrapper<MallAccount> wrapper = new QueryWrapper<>();
//        if (param.getCouponName() != null) {
//            wrapper.eq("coupon_name", param.getCouponName());
//        }
//        if (param.getCreatedBy() != null) {
//            wrapper.eq("created_by", param.getCreatedBy());
//        }
        // pageIndex + 1 表示前端从第0页开始分页查询，PageRequest.of(pageIndex  不用减一
        IPage<MallAccount> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageIndex, pageSize).setDesc("id");
        IPage<MallAccount> activityCouponPage = mallAccountMapper.selectPage(page, wrapper);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC,"id"));
        return new PageImpl(BeanUtil.convert(activityCouponPage.getRecords(), MallAccountVo.class), pageRequest, page.getTotal());
    }

    @Override
    public MallAccountVo register(MallAccountVo accountVo) throws Exception {
        List<MallAccount> accounts = mallAccountMapper.selectList(new QueryWrapper<MallAccount>().eq("username", accountVo.getUsername()));
        if (!CollectionUtils.isEmpty(accounts)) {
            throw new BaseException("该用户[" + accountVo.getUsername() + "]已经存在");
        }

        MallAccount account = BeanUtil.convert(accountVo, MallAccount.class);
        account.setPassword(MD5Util.encrypt(accountVo.getPassword()));
        account.setAccountId(MD5Util.encrypt(accountVo.getUsername()));
        mallAccountMapper.insert(account);

        return BeanUtil.convert(account, MallAccountVo.class);
    }


    @Override
    public MallAccountVo login(MallAccountVo accountVo) throws Exception {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        MallAccount account = mallAccountMapper.selectOne(new QueryWrapper<MallAccount>().eq("username", accountVo.getUsername()));
        if (account == null) {
            throw new BaseException("用户[" + accountVo.getUsername() + "]不存在存在");
        }
        if (!account.getPassword().equals(MD5Util.encrypt(accountVo.getPassword()))) {
            throw new BaseException("输入的用户名密码不正确");
        }

        String sessionId = MD5Util.encrypt(accountVo.getUsername() + System.currentTimeMillis());
        dataCacheService.setSession(sessionId, BeanUtil.convert(account, MallAccountVo.class));
        CookieUtil.set(Constant.SESSION_IDENTITY_KEY, sessionId, response);
        return accountVo;
    }


}
