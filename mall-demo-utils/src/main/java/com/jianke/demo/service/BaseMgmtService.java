package com.jianke.demo.service;

import com.jianke.demo.exception.BaseException;
import org.springframework.data.domain.Page;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 21:45
 **/
public interface BaseMgmtService<T, P> {

    T findById(Long id) throws BaseException;

    T save(T vo) throws BaseException;

    void deleteById(Long id) throws BaseException;

    T update(T vo) throws BaseException;

    Page<T> findByPage(P param, int pageIndex, int pageSize) throws BaseException;
}
