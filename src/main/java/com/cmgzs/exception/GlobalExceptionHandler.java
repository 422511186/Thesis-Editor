package com.cmgzs.exception;

import com.cmgzs.utils.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: hzy
 * @Date: 2022/4/28
 * @Description: 全局异常处理
 * 处理整个web controller的异常
 */

@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //处理校验异常，对于对象类型的数据的校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Object handler(MethodArgumentNotValidException e) {
        StringBuffer sb = new StringBuffer();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        allErrors.forEach(msg -> sb.append(msg.getDefaultMessage()).append(";"));
        return ResultGenerator.genFailResult(sb.toString());
    }

    @ExceptionHandler(UserNameExistedException.class)
    public Object UserNameExistedExceptionHandler(Exception e) {
        log.error("异常是:{}", e.getMessage());
        return ResultGenerator.genFailResult(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public Object handler(Exception e) {
        log.error("异常是:{}", e.toString());
        return ResultGenerator.genFailResult("出现未知异常啦!请联系管理员查看日志。");
    }


}
