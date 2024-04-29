package com.mxj.gulimall.product.exception;

import com.mxj.common.exception.BizCodeEnum;
import com.mxj.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.UnexpectedTypeException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: GulimallExceptionControllerAdvice
 * @Author mi xiu jin
 * @Package com.mxj.gulimall.product.exception
 * @Date 2024/4/30 1:45
 * @description: 统一异常处理
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.mxj.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}，异常类型:{}", e.getMessage(), e.getClass());
        Map<String, Object> errorMap = new HashMap<>();
        BindingResult bindingResult = e.getBindingResult();
        bindingResult.getFieldErrors().forEach(fieldError -> {
            String defaultMessage = fieldError.getDefaultMessage();
            String field = fieldError.getField();
            errorMap.put(field, defaultMessage);
        });
        return R.error(BizCodeEnum.VALID_EXCEPTION.getCode(), BizCodeEnum.VALID_EXCEPTION.getMsg()).put("data", errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public R handleException(Throwable e) {
        log.error("数据校验出现问题{}，异常类型:{}", e.getMessage(), e.getClass());
        return R.error(BizCodeEnum.UNKNOW_EXCEPTION.getCode(), BizCodeEnum.UNKNOW_EXCEPTION.getMsg());
    }
}
