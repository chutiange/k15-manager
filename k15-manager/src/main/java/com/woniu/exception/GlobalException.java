package com.woniu.exception;

import com.woniu.util.ResponseResult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public ResponseResult<Void> noAuthorization(){
        return ResponseResult.FORBIDDEN;
    }
}