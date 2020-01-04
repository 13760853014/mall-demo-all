package com.jianke.demo.utils;

import com.jianke.demo.exception.ValidateException;
import org.apache.commons.collections.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: mall-share
 * @description: ${description}
 * @author: chenguiquan
 * @create: 2019-08-12 21:45
 **/
public class ValidatorUtil {
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public static <T> List<String> validate(T t) throws ValidateException {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(t);
        List<String> messages = violations.stream().map(v -> v.getMessage()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(messages)) {
            throw new ValidateException(String.join(",",messages));
        }
        return messages;
    }

}

