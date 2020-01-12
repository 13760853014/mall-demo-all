package com.jianke.demo.cart.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jianke.demo.cart.entity.MallCart;
import com.jianke.demo.cart.mapper.MallCartMapper;
import com.jianke.demo.cart.service.MallCartService;
import com.jianke.demo.exception.BaseException;
import com.jianke.demo.exception.ValidateException;
import com.jianke.demo.param.MallCartParam;
import com.jianke.demo.service.BaseQueryServiceAdapter;
import com.jianke.demo.utils.BeanUtil;
import com.jianke.demo.utils.ValidatorUtil;
import com.jianke.demo.vo.MallCartVo;
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
public class MallCartServiceImpl extends BaseQueryServiceAdapter<MallCartVo, MallCartParam> implements MallCartService {

    @Autowired
    private MallCartMapper mallCartMapper;

    @Override
    public MallCartVo findById(Long id) throws BaseException {
        MallCart Cart = mallCartMapper.selectById(id);
        return BeanUtil.convert(Cart, MallCartVo.class);
    }


    @Override
    public MallCartVo save(MallCartVo vo) throws BaseException {
        MallCart Cart = BeanUtil.convert(vo, MallCart.class);
        mallCartMapper.insert(Cart);
        return findById(Cart.getId());
    }


    @Override
    public void deleteById(Long id) throws BaseException {
        MallCart activityCoupon = mallCartMapper.selectById(id);
        if (activityCoupon == null) {
            throw new BaseException("购物车不存在");
        }
        mallCartMapper.deleteById(id);
    }


    @Override
    public MallCartVo update(MallCartVo vo) throws BaseException {
        MallCart oldCart = mallCartMapper.selectById(vo.getId());
        if (oldCart == null) {
            throw new BaseException("0001","购物车不存在");
        }
        MallCart Cart = BeanUtil.convert(vo, MallCart.class);
        mallCartMapper.updateById(Cart);
        return findById(Cart.getId());
    }


    @Override
    public Page<MallCartVo> findByPage(MallCartParam param, int pageIndex, int pageSize) throws BaseException {
        if (pageIndex < 0) {
            throw new ValidateException("页数不合法！");
        }
        if (pageSize <= 0) {
            throw new ValidateException("页面显示数据条数不合法！");
        }
        ValidatorUtil.validate(param);

        QueryWrapper<MallCart> wrapper = new QueryWrapper<>();
//        if (param.getCouponName() != null) {
//            wrapper.eq("coupon_name", param.getCouponName());
//        }
//        if (param.getCreatedBy() != null) {
//            wrapper.eq("created_by", param.getCreatedBy());
//        }
        // pageIndex + 1 表示前端从第0页开始分页查询，PageRequest.of(pageIndex  不用减一
        IPage<MallCart> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageIndex, pageSize).setDesc("id");
        IPage<MallCart> activityCouponPage = mallCartMapper.selectPage(page, wrapper);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC,"id"));
        return new PageImpl(BeanUtil.convert(activityCouponPage.getRecords(), MallCartVo.class), pageRequest, page.getTotal());
    }

}
