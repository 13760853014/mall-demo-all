package com.jianke.demo.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.classmap.RelationshipType;
import org.dozer.loader.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class BeanUtil {
    private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
    BeanMappingBuilder builder = new BeanMappingBuilder() {
        protected void configure() {
            this.mapping(Collection.class, Collection.class, new TypeMappingOption[]{TypeMappingOptions.oneWay(), TypeMappingOptions.mapId("A"), TypeMappingOptions.mapNull(true)}).exclude("excluded").fields("src", "dest", new FieldsMappingOption[]{FieldsMappingOptions.copyByReference(), FieldsMappingOptions.collectionStrategy(true, RelationshipType.NON_CUMULATIVE), FieldsMappingOptions.hintA(new Class[]{String.class}), FieldsMappingOptions.hintB(new Class[]{Integer.class}), FieldsMappingOptions.oneWay(), FieldsMappingOptions.useMapId("A"), FieldsMappingOptions.customConverterId("id")}).fields("src", "dest", new FieldsMappingOption[]{FieldsMappingOptions.customConverter("org.dozer.CustomConverter")});
        }
    };
    private static DozerBeanMapper beanMapper = new DozerBeanMapper();

    public BeanUtil() {
    }

    public static <S, T> void convert(S source, T target) {
        beanMapper.map(source, target);
    }

    public static <T> T convert(Object source, Class<T> destinationClass) {
        return source == null ? null : beanMapper.map(source, destinationClass);
    }

    public static <T> List<T> convert(List<?> sources, Class<T> destinationClass) {
        if (sources == null) {
            return null;
        }
        List<T> result = new ArrayList();
        Iterator var3 = sources.iterator();
        while(var3.hasNext()) {
            Object source = var3.next();
            result.add(convert(source, destinationClass));
        }
        return result;
    }
}
