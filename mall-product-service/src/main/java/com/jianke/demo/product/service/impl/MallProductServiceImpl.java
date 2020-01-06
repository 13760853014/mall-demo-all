package com.jianke.demo.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jianke.demo.exception.BaseException;
import com.jianke.demo.exception.ValidateException;
import com.jianke.demo.param.MallProductParam;
import com.jianke.demo.product.entity.MallProduct;
import com.jianke.demo.product.mapper.MallProductMapper;
import com.jianke.demo.product.service.MallProductService;
import com.jianke.demo.service.BaseQueryServiceAdapter;
import com.jianke.demo.utils.BeanUtil;
import com.jianke.demo.utils.ValidatorUtil;
import com.jianke.demo.vo.MallProductVo;
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
public class MallProductServiceImpl extends BaseQueryServiceAdapter<MallProductVo, MallProductParam> implements MallProductService {

    @Autowired
    private MallProductMapper mallProductMapper;

    @Override
    public MallProductVo findById(Long id) throws BaseException {
        MallProduct product = mallProductMapper.selectById(id);
        return BeanUtil.convert(product, MallProductVo.class);
    }


    @Override
    public MallProductVo save(MallProductVo vo) throws BaseException {
        MallProduct product = BeanUtil.convert(vo, MallProduct.class);
        mallProductMapper.insert(product);
        return findById(product.getId());
    }


    @Override
    public void deleteById(Long id) throws BaseException {
        MallProduct activityCoupon = mallProductMapper.selectById(id);
        if (activityCoupon == null) {
            throw new BaseException("优惠券不存在");
        }
        mallProductMapper.deleteById(id);
    }


    @Override
    public MallProductVo update(MallProductVo vo) throws BaseException {
        MallProduct oldProduct = mallProductMapper.selectById(vo.getId());
        if (oldProduct == null) {
            throw new BaseException("0001","优惠券不存在");
        }
        MallProduct product = BeanUtil.convert(vo, MallProduct.class);
        mallProductMapper.updateById(product);
        return findById(product.getId());
    }


    @Override
    public Page<MallProductVo> findByPage(MallProductParam param, int pageIndex, int pageSize) throws BaseException {
        if (pageIndex < 0) {
            throw new ValidateException("页数不合法！");
        }
        if (pageSize <= 0) {
            throw new ValidateException("页面显示数据条数不合法！");
        }
        ValidatorUtil.validate(param);

        QueryWrapper<MallProduct> wrapper = new QueryWrapper<>();
//        if (param.getCouponName() != null) {
//            wrapper.eq("coupon_name", param.getCouponName());
//        }
//        if (param.getCreatedBy() != null) {
//            wrapper.eq("created_by", param.getCreatedBy());
//        }
        // pageIndex + 1 表示前端从第0页开始分页查询，PageRequest.of(pageIndex  不用减一
        IPage<MallProduct> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(pageIndex, pageSize).setDesc("id");
        IPage<MallProduct> activityCouponPage = mallProductMapper.selectPage(page, wrapper);
        PageRequest pageRequest = PageRequest.of(pageIndex - 1, pageSize, new Sort(Sort.Direction.DESC,"id"));
        return new PageImpl(BeanUtil.convert(activityCouponPage.getRecords(), MallProductVo.class), pageRequest, page.getTotal());
    }

}
