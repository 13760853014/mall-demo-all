package com.jianke.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jianke.demo.entity.ActivityCoupon;
import com.jianke.demo.exception.BaseException;
import com.jianke.demo.exception.ValidateException;
import com.jianke.demo.mapper.ActivityCouponMapper;
import com.jianke.demo.param.ActivityCouponParam;
import com.jianke.demo.service.ActivityCouponService;
import com.jianke.demo.service.BaseQueryServiceAdapter;
import com.jianke.demo.utils.BeanUtil;
import com.jianke.demo.utils.ValidatorUtil;
import com.jianke.demo.vo.ActivityCouponVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 20:27
 **/
@Service
public class ActivityCouponServiceImpl extends BaseQueryServiceAdapter<ActivityCouponVo, ActivityCouponParam> implements ActivityCouponService {

    @Autowired
    private ActivityCouponMapper activityCouponMapper;

    @Override
    public ActivityCouponVo findById(Long id) throws BaseException {
        ActivityCoupon activityCoupon = activityCouponMapper.selectById(id);
        return BeanUtil.convert(activityCoupon, ActivityCouponVo.class);
    }


    @Override
    public ActivityCouponVo save(ActivityCouponVo vo) throws BaseException {
        ActivityCoupon activityCoupon = BeanUtil.convert(vo, ActivityCoupon.class);
        activityCouponMapper.insert(activityCoupon);
        return findById(activityCoupon.getId());
    }


    @Override
    public void deleteById(Long id) throws BaseException {
        ActivityCoupon activityCoupon = activityCouponMapper.selectById(id);
        if (activityCoupon == null) {
            throw new BaseException("优惠券不存在");
        }
        activityCouponMapper.deleteById(id);
    }


    @Override
    public ActivityCouponVo update(ActivityCouponVo vo) throws BaseException {
        ActivityCoupon oldActivityCoupon = activityCouponMapper.selectById(vo.getId());
        if (oldActivityCoupon == null) {
            throw new BaseException("0001","优惠券不存在");
        }
        ActivityCoupon activityCoupon = BeanUtil.convert(vo, ActivityCoupon.class);
        activityCouponMapper.updateById(activityCoupon);
        return findById(activityCoupon.getId());
    }


    @Override
    public Page<ActivityCouponVo> findByPage(ActivityCouponParam param, int pageIndex, int pageSize) throws BaseException {
        if (pageIndex < 0) {
            throw new ValidateException("页数不合法！");
        }
        if (pageSize <= 0) {
            throw new ValidateException("页面显示数据条数不合法！");
        }
        ValidatorUtil.validate(param);

        QueryWrapper<ActivityCoupon> wrapper = new QueryWrapper<>();
        if (param.getCouponName() != null) {
            wrapper.eq("coupon_name", param.getCouponName());
        }
        if (param.getCreatedBy() != null) {
            wrapper.eq("created_by", param.getCreatedBy());
        }
        // pageIndex + 1 表示前端从第0页开始分页查询，PageRequest.of(pageIndex  不用减一
        IPage<ActivityCoupon> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageIndex, pageSize).setDesc("id");
        IPage<ActivityCoupon> activityCouponPage = activityCouponMapper.selectPage(page, wrapper);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC,"id"));
        return new PageImpl(BeanUtil.convert(activityCouponPage.getRecords(), ActivityCouponVo.class), pageRequest, page.getTotal());
    }

}
